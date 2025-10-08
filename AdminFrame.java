
package za.ac.cput.clientside;


import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class AdminFrame extends JFrame implements ActionListener {
    private Client client;

    // Sidebar buttons
    private JButton coursesBtn = new JButton("Manage Courses");
    private JButton studentsBtn = new JButton("Manage Students");
    private JButton logsBtn = new JButton("View Logs");
    private JButton exitBtn = new JButton("Logout");

    // Panels for content (pages)
    private JPanel cardsPanel = new JPanel(new CardLayout());
    private JPanel coursesPanel = new JPanel();
    private JPanel studentsPanel = new JPanel();
    private JPanel logsPanel = new JPanel();

    // Course components
    private JTextField courseIdField = new JTextField(15);
    private JButton addCourseBtn = new JButton("Add Course");
    private JTextArea courseArea = new JTextArea(10, 30);

    // Student components
    private JTextField studentIdField = new JTextField(15);
    private JButton addStudentBtn = new JButton("Add Student");
    private JTextArea studentArea = new JTextArea(10, 30);

    // Log components
    private JTextArea logArea = new JTextArea(15, 35);

    // Colors
    private final Color primaryColor = new Color(52, 152, 219);
    private final Color sidebarColor = new Color(44, 62, 80);
    private final Color backgroundColor = new Color(245, 247, 250);

    public AdminFrame(Client client) {
        super("Admin Dashboard");
        this.client = client;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // --- SIDEBAR ---
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(6, 1, 0, 10));
        sidebar.setBackground(sidebarColor);
        sidebar.setBorder(new EmptyBorder(30, 10, 30, 10));

        JLabel title = new JLabel("Admin Menu", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sidebar.add(title);

        styleSidebarButton(coursesBtn);
        styleSidebarButton(studentsBtn);
        styleSidebarButton(logsBtn);
        styleSidebarButton(exitBtn);

        sidebar.add(coursesBtn);
        sidebar.add(studentsBtn);
        sidebar.add(logsBtn);
        sidebar.add(exitBtn);

        add(sidebar, BorderLayout.WEST);

        // --- MAIN PANEL (cards) ---
        cardsPanel.setBackground(backgroundColor);

        setupCoursesPanel();
        setupStudentsPanel();
        setupLogsPanel();

        cardsPanel.add(coursesPanel, "COURSES");
        cardsPanel.add(studentsPanel, "STUDENTS");
        cardsPanel.add(logsPanel, "LOGS");

        add(cardsPanel, BorderLayout.CENTER);

        // Default view
        showCard("COURSES");

        // Add listeners
        coursesBtn.addActionListener(this);
        studentsBtn.addActionListener(this);
        logsBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setVisible(true);
    }

    // Sidebar button style
    private void styleSidebarButton(JButton btn) {
        btn.setBackground(sidebarColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { btn.setBackground(primaryColor); }
            public void mouseExited(MouseEvent e) { btn.setBackground(sidebarColor); }
        });
    }

    // Courses panel setup
    private void setupCoursesPanel() {
        coursesPanel.setBackground(Color.WHITE);
        coursesPanel.setLayout(new BorderLayout(10, 10));
        coursesPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Manage Courses", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setForeground(primaryColor);
        coursesPanel.add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        form.add(new JLabel("Course ID:"));
        styleField(courseIdField);
        form.add(courseIdField);
        styleButton(addCourseBtn);
        form.add(addCourseBtn);

        coursesPanel.add(form, BorderLayout.CENTER);

        courseArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        courseArea.setBorder(BorderFactory.createTitledBorder("Server Responses"));
        courseArea.setBackground(new Color(250, 250, 250));
        coursesPanel.add(new JScrollPane(courseArea), BorderLayout.SOUTH);

        addCourseBtn.addActionListener(this);
    }

    // Students panel setup
    private void setupStudentsPanel() {
        studentsPanel.setBackground(Color.WHITE);
        studentsPanel.setLayout(new BorderLayout(10, 10));
        studentsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Manage Students", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setForeground(primaryColor);
        studentsPanel.add(heading, BorderLayout.NORTH);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        form.add(new JLabel("Student ID:"));
        styleField(studentIdField);
        form.add(studentIdField);
        styleButton(addStudentBtn);
        form.add(addStudentBtn);

        studentsPanel.add(form, BorderLayout.CENTER);

        studentArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        studentArea.setBorder(BorderFactory.createTitledBorder("Server Responses"));
        studentArea.setBackground(new Color(250, 250, 250));
        studentsPanel.add(new JScrollPane(studentArea), BorderLayout.SOUTH);

        addStudentBtn.addActionListener(this);
    }

    // Logs panel setup
    private void setupLogsPanel() {
        logsPanel.setBackground(Color.WHITE);
        logsPanel.setLayout(new BorderLayout(10, 10));
        logsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Server Logs", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setForeground(primaryColor);
        logsPanel.add(heading, BorderLayout.NORTH);

        logArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        logArea.setBorder(BorderFactory.createTitledBorder("Activity Log"));
        logArea.setBackground(new Color(250, 250, 250));
        logsPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(5, 8, 5, 8)
        ));
    }

    private void styleButton(JButton button) {
        button.setBackground(primaryColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(primaryColor.darker(), 1, true));
        button.setFont(new Font("Segoe UI", Font.BOLD, 13));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { button.setBackground(primaryColor.darker()); }
            public void mouseExited(MouseEvent e) { button.setBackground(primaryColor); }
        });
    }

    private void showCard(String name) {
        CardLayout cl = (CardLayout) (cardsPanel.getLayout());
        cl.show(cardsPanel, name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == coursesBtn) showCard("COURSES");
        else if (src == studentsBtn) showCard("STUDENTS");
        else if (src == logsBtn) showCard("LOGS");
   else if (src == exitBtn) {
    try {
        client.sendMessage("EXIT");
        client.close();
    } catch (Exception ex) {
        System.err.println("Error closing client: " + ex.getMessage());
    }
    dispose();
    SwingUtilities.invokeLater(() -> new LoginFrame());
}

        else if (src == addCourseBtn) {
            String courseId = courseIdField.getText().trim();
            if (!courseId.isEmpty()) {
                String response = client.sendMessage("ADD_COURSE," + courseId);
                courseArea.append(response + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Course ID.");
            }
        } 
        else if (src == addStudentBtn) {
            String studentId = studentIdField.getText().trim();
            if (!studentId.isEmpty()) {
                String response = client.sendMessage("ADD_STUDENT," + studentId);
                studentArea.append(response + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Student ID.");
            }
        }
    }
}

