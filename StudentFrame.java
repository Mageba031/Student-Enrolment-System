package za.ac.cput.clientside;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentFrame extends JFrame implements ActionListener {
    private Client client;
    private String studentId;
    private JTextField courseField = new JTextField(10);
    private JButton enrollBtn = new JButton("Enroll");
    private JButton viewCoursesBtn = new JButton("View My Courses");
    private JButton exitBtn = new JButton("Exit");
    private JTextArea displayArea = new JTextArea(10, 30);

    public StudentFrame(Client client, String studentId) {
        super("Student Dashboard");
        this.client = client;
        this.studentId = studentId;

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Course ID:"));
        topPanel.add(courseField);
        topPanel.add(enrollBtn);
        topPanel.add(viewCoursesBtn);
        topPanel.add(exitBtn);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        enrollBtn.addActionListener(this);
        viewCoursesBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == enrollBtn) {
            String courseId = courseField.getText().trim();
            if (!courseId.isEmpty()) {
                String response = client.sendMessage("ENROLL," + studentId + "," + courseId);
                displayArea.append(response + "\n");
            }
        } else if (e.getSource() == viewCoursesBtn) {
            String response = client.sendMessage("GET_ENROLLMENTS," + studentId);
            displayArea.append(response + "\n");
        } else if (e.getSource() == exitBtn) {
            client.sendMessage("EXIT");
            client.close();
            System.exit(0);
        }
    }
}

