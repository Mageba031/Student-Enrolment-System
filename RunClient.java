package za.ac.cput.clientside;

import javax.swing.*;

public class RunClient {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}


