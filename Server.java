package za.ac.cput.serverside;

import java.io.*;
import java.net.*;
import java.sql.Date;
import java.util.List;

public class Server {
    private ServerSocket listener;
    private Socket client;

  public Server() {
    try {
        listener = new ServerSocket(12346, 1); // new port
        System.out.println("Server started. Waiting for client...");
    } catch (IOException ioe) {
        System.out.println("Port is busy or unavailable. Try another port.");
    }
}


    public void listen() {
        try {
            client = listener.accept();
            System.out.println("Client connected: " + client.getInetAddress());
            processClient();
        } catch (IOException ioe) {
            System.out.println("IOException: " + ioe.getMessage());
        } finally {
            try {
                if (listener != null && !listener.isClosed()) listener.close();
            } catch (IOException e) {
                System.out.println("Error closing listener: " + e.getMessage());
            }
        }
    }

    private void processClient() {
        // Use try-with-resources so streams close automatically
        try (ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(client.getInputStream())) {

            String message = null;
            EnrollmentDao dao = new EnrollmentDao();

            do {
                Object obj = in.readObject();
                if (!(obj instanceof String)) {
                    out.writeObject("INVALID_PAYLOAD");
                    out.flush();
                    continue;
                }

                message = ((String) obj).trim();
                System.out.println("CLIENT>> " + message);

                String[] parts = message.split(",");
                String command = parts[0].trim().toUpperCase();
                String response = "";

                switch (command) {
                    case "LOGIN":
                        if (parts.length < 3) {
                            response = "LOGIN_FAIL:INVALID_PAYLOAD";
                        } else {
                            String username = parts[1].trim();
                            String password = parts[2].trim();
                            boolean valid = authenticateUser(username, password);
                            response = valid ? "LOGIN_SUCCESS" : "LOGIN_FAIL";
                        }
                        break;

                    case "ENROLL":
                        if (parts.length < 3) {
                            response = "ENROLL_FAIL:INVALID_PAYLOAD";
                        } else {
                            String studentId = parts[1].trim();
                            String courseId = parts[2].trim();
                            dao.addEnrollment(studentId, courseId, new Date(System.currentTimeMillis()));
                            response = "ENROLLMENT_SUCCESS";
                        }
                        break;

                    case "GET_ENROLLMENTS":
                        if (parts.length < 2) {
                            response = "GET_ENROLLMENTS_FAIL:INVALID_PAYLOAD";
                        } else {
                            String studId = parts[1].trim();
                            List<String> courses = dao.getEnrollmentsByStudent(studId);
                            response = "COURSES:" + String.join(",", courses);
                        }
                        break;

                    case "EXIT":
                        response = "GOODBYE";
                        out.writeObject(response);
                        out.flush();
                        break;

                    default:
                        response = "INVALID_COMMAND";
                }

                // send response (if not already sent for EXIT)
                if (!"EXIT".equalsIgnoreCase(message)) {
                    out.writeObject(response);
                    out.flush();
                }

            } while (!"EXIT".equalsIgnoreCase(message));

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        } finally {
            try {
                if (client != null && !client.isClosed()) client.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }

    // temporary hard-coded authentication (replace later with UserDAO)
    private boolean authenticateUser(String username, String password) {
        return (username.equals("admin") && password.equals("admin123")) ||
               (username.equals("student") && password.equals("pass1"));
    }
}

