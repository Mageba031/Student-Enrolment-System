package za.ac.cput.clientside;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminFrame extends JFrame implements ActionListener {
    private Client client;
    private JTextField courseIdField = new JTextField(10);
    private JTextField studentIdField = new JTextField(10);
    private JButton addCourseBtn = new JButton("Add Course");
    private JButton addStudentBtn = new JButton("Add Student");
    private JButton exitBtn = new JButton("Exit");
    private JTextArea displayArea = new JTextArea(10, 30);

    public AdminFrame(Client client) {
        super("Admin Dashboard");
        this.client = client;

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("Course ID:"));
        panel.add(courseIdField);
        panel.add(addCourseBtn);
        panel.add(new JLabel(""));

        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);
        panel.add(addStudentBtn);
        panel.add(new JLabel(""));

        panel.add(exitBtn);
        panel.add(new JLabel(""));

        addCourseBtn.addActionListener(this);
        addStudentBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCourseBtn) {
            String courseId = courseIdField.getText().trim();
            if (!courseId.isEmpty()) {
                String response = client.sendMessage("ADD_COURSE," + courseId);
                displayArea.append(response + "\n");
            }
        } else if (e.getSource() == addStudentBtn) {
            String studentId = studentIdField.getText().trim();
            if (!studentId.isEmpty()) {
                String response = client.sendMessage("ADD_STUDENT," + studentId);
                displayArea.append(response + "\n");
            }
        } else if (e.getSource() == exitBtn) {
            client.sendMessage("EXIT");
            client.close();
            System.exit(0);
        }
    }
}

