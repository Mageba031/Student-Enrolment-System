package za.ac.cput.clientside;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

public boolean connect() {
    if (socket != null && socket.isConnected()) {
        return true;
    }
    try {
        socket = new Socket("localhost", 12346);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
        System.out.println("Connected to server.");
        return true;
    } catch (IOException e) {
        System.out.println("Error connecting: " + e.getMessage());
        return false;
    }
}


    public String sendMessage(String message) {
        try {
            out.writeObject(message);
            out.flush();
            Object response = in.readObject();
            return (String) response;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error sending message: " + e.getMessage());
            return "ERROR";
        }
    }

    public void close() {
        try {
            if (out != null) out.close();
            if (in != null) in.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.out.println("Error closing client: " + e.getMessage());
        }
    }
}

