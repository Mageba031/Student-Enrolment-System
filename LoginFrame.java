package za.ac.cput.clientside;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    private JTextField usernameField = new JTextField(15);
    private JPasswordField passwordField = new JPasswordField(15);
    private JButton loginButton = new JButton("Login");
    private JLabel messageLabel = new JLabel("");
    private Client client;

 public LoginFrame() {
    super("Login");

    client = new Client();
    boolean connected = client.connect(); // connect ONCE
    if (!connected) {
        JOptionPane.showMessageDialog(this, "Server not available. Please start the server first.");
        System.exit(0);
    }

    setLayout(new GridLayout(4, 2, 5, 5));
    add(new JLabel("Username:"));
    add(usernameField);
    add(new JLabel("Password:"));
    add(passwordField);
    add(new JLabel(""));
    add(loginButton);
    add(messageLabel);

    loginButton.addActionListener(this);

    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
}


    @Override
public void actionPerformed(ActionEvent e) {
    String username = usernameField.getText().trim();
    String password = new String(passwordField.getPassword()).trim();

    if (username.isEmpty() || password.isEmpty()) {
        messageLabel.setText("Enter username & password.");
        return;
    }

    String response = client.sendMessage("LOGIN," + username + "," + password);
    System.out.println("SERVER RESPONSE: " + response);

    if (response.equalsIgnoreCase("LOGIN_SUCCESS")) {
        messageLabel.setText("Login successful!");
        this.dispose(); // close login frame

        if (username.equalsIgnoreCase("admin")) {
            new AdminFrame(client);
        } else {
            new StudentFrame(client, username);
        }
    } else {
        messageLabel.setText("Login failed. Try again.");
    }
}
}

