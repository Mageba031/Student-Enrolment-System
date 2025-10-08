

package za.ac.cput.clientside;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JButton loginButton = new JButton("Login");
    private JLabel messageLabel = new JLabel("", SwingConstants.CENTER);
    private Client client;

    // Colors
    private final Color primaryColor = new Color(52, 152, 219); // Blue
    private final Color backgroundColor = new Color(245, 247, 250); // Light gray
    private final Color textColor = new Color(44, 62, 80); // Dark gray

    public LoginFrame() {
        super("Login");

        // Connect client once
        client = new Client();
        boolean connected = client.connect();
        if (!connected) {
            JOptionPane.showMessageDialog(this, "Server not available. Please start the server first.");
            System.exit(0);
        }

        // Base setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(backgroundColor);
        setLayout(new BorderLayout());

        // --- HEADER ---
        JLabel titleLabel = new JLabel("Welcome", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setForeground(primaryColor);
        titleLabel.setBorder(new EmptyBorder(20, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // --- FORM PANEL ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new CompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(20, 30, 20, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        styleField(usernameField);
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        styleField(passwordField);
        formPanel.add(passwordField, gbc);

        // Login button
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        styleButton(loginButton);
        formPanel.add(loginButton, gbc);

        // Message label
        gbc.gridy = 3;
        messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        messageLabel.setForeground(Color.RED);
        formPanel.add(messageLabel, gbc);

        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(this);
        setVisible(true);
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
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Simple hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(primaryColor.darker());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(primaryColor);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please enter username & password.");
            return;
        }

        String response = client.sendMessage("LOGIN," + username + "," + password);
        System.out.println("SERVER RESPONSE: " + response);

        if (response.equalsIgnoreCase("LOGIN_SUCCESS")) {
            messageLabel.setForeground(new Color(39, 174, 96));
            messageLabel.setText("Login successful!");
            dispose();

            if (username.equalsIgnoreCase("admin")) {
                new AdminFrame(client);
            } else {
                new StudentFrame(client, username);
            }
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Login failed. Try again.");
        }
    }

}
