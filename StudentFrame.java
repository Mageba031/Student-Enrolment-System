
package za.ac.cput.clientside;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentFrame extends JFrame implements ActionListener {
    private Client client;
    private String studentId;

    // Sidebar buttons
    private JButton coursesBtn = new JButton("Available Courses");
    private JButton enrolledBtn = new JButton("My Enrollments");
    private JButton exitBtn = new JButton("Logout");

    // Main card panel
    private JPanel cardsPanel = new JPanel(new CardLayout());
    private JPanel availableCoursesPanel = new JPanel();
    private JPanel enrollmentsPanel = new JPanel();

    // Display areas
    private JTextArea enrolledArea = new JTextArea(10, 30);

    // Colors
    private final Color primaryColor = new Color(52, 152, 219);
    private final Color sidebarColor = new Color(44, 62, 80);
    private final Color backgroundColor = new Color(245, 247, 250);

    // Data
    private final Map<String, String[]> courses = new LinkedHashMap<>();

    public StudentFrame(Client client, String studentId) {
        super("Student Dashboard");
        this.client = client;
        this.studentId = studentId;

        // --- Setup Course Data ---
        courses.put("Undergraduate", new String[]{
            "Bachelor of Engineering Technology — Civil Engineering",
            "Bachelor of Education — Foundation Phase Teaching",
            "Bachelor of Health Science in Medical Laboratory Science",
            "Bachelor of Agriculture in Agricultural Management",
            "Bachelor of Commerce in Marketing"
        });

        courses.put("Postgraduate", new String[]{
            "Master of Agriculture",
            "Master of Applied Sciences in Chemistry",
            "Master of Consumer Science in Food and Nutrition",
            "Master of Environmental Management",
            "Master of Nursing"
        });

        // --- Base Setup ---
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel(new GridLayout(6, 1, 0, 10));
        sidebar.setBackground(sidebarColor);
        sidebar.setBorder(new EmptyBorder(30, 10, 30, 10));

        JLabel title = new JLabel("Student Menu", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        sidebar.add(title);

        styleSidebarButton(coursesBtn);
        styleSidebarButton(enrolledBtn);
        styleSidebarButton(exitBtn);

        sidebar.add(coursesBtn);
        sidebar.add(enrolledBtn);
        sidebar.add(exitBtn);

        add(sidebar, BorderLayout.WEST);

        // Cards
        setupAvailableCoursesPanel();
        setupEnrollmentsPanel();

        cardsPanel.add(availableCoursesPanel, "COURSES");
        cardsPanel.add(enrollmentsPanel, "ENROLLMENTS");
        add(cardsPanel, BorderLayout.CENTER);

        // Listeners
        coursesBtn.addActionListener(this);
        enrolledBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        showCard("COURSES");
        setVisible(true);
    }

    // Sidebar button styling
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

    // Create "Available Courses" page
    private void setupAvailableCoursesPanel() {
        availableCoursesPanel.setLayout(new BorderLayout(10, 10));
        availableCoursesPanel.setBackground(Color.WHITE);
        availableCoursesPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("Available Courses", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setForeground(primaryColor);
        availableCoursesPanel.add(heading, BorderLayout.NORTH);

        JPanel courseListPanel = new JPanel();
        courseListPanel.setLayout(new BoxLayout(courseListPanel, BoxLayout.Y_AXIS));
        courseListPanel.setBackground(Color.WHITE);

        for (Map.Entry<String, String[]> entry : courses.entrySet()) {
            String level = entry.getKey();
            String[] list = entry.getValue();

            JLabel levelLabel = new JLabel(level);
            levelLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
            levelLabel.setForeground(new Color(41, 128, 185));
            levelLabel.setBorder(new EmptyBorder(10, 0, 5, 0));
            courseListPanel.add(levelLabel);

            for (String course : list) {
                JPanel row = new JPanel(new BorderLayout());
                row.setBackground(new Color(250, 250, 250));
                row.setBorder(new CompoundBorder(
                        new LineBorder(new Color(220, 220, 220), 1, true),
                        new EmptyBorder(8, 10, 8, 10)
                ));

                JLabel courseLabel = new JLabel(course);
                courseLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                JButton enrollButton = new JButton("Enroll");
                styleButton(enrollButton);

                enrollButton.addActionListener(e -> {
                    String response = client.sendMessage("ENROLL," + studentId + "," + course);
                    JOptionPane.showMessageDialog(this, response, "Enrollment", JOptionPane.INFORMATION_MESSAGE);
                });

                row.add(courseLabel, BorderLayout.CENTER);
                row.add(enrollButton, BorderLayout.EAST);
                courseListPanel.add(row);
                courseListPanel.add(Box.createVerticalStrut(5));
            }

            courseListPanel.add(Box.createVerticalStrut(15));
        }

        JScrollPane scrollPane = new JScrollPane(courseListPanel);
        scrollPane.setBorder(null);
        availableCoursesPanel.add(scrollPane, BorderLayout.CENTER);
    }

    // Create "My Enrollments" page
    private void setupEnrollmentsPanel() {
        enrollmentsPanel.setLayout(new BorderLayout(10, 10));
        enrollmentsPanel.setBackground(Color.WHITE);
        enrollmentsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel heading = new JLabel("My Enrollments", JLabel.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 20));
        heading.setForeground(primaryColor);
        enrollmentsPanel.add(heading, BorderLayout.NORTH);

        enrolledArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        enrolledArea.setEditable(false);
        enrolledArea.setBackground(new Color(250, 250, 250));
        enrolledArea.setBorder(BorderFactory.createTitledBorder("Enrolled Courses"));
        enrollmentsPanel.add(new JScrollPane(enrolledArea), BorderLayout.CENTER);

        JButton refreshBtn = new JButton("Refresh List");
        styleButton(refreshBtn);
        refreshBtn.addActionListener(e -> {
            String response = client.sendMessage("GET_ENROLLMENTS," + studentId);
            enrolledArea.setText(response);
        });
        enrollmentsPanel.add(refreshBtn, BorderLayout.SOUTH);
    }

    // Button and text field styles
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
        else if (src == enrolledBtn) showCard("ENROLLMENTS");
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


    }
}
