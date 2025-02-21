import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class RegisterForm extends JDialog {
    private JPanel mainpanel;
    private JPanel imagepanel;
    private JLabel image;
    private JTextField Voteriffield;
    private JTextField namefield;
    private JTextField emailfield;
    private JPasswordField passwordField;
    private JPasswordField confirmpassword;
    private JPanel infopanel;
    private JPanel heading;
    private JLabel voteridlabel;
    private JLabel NameLabel;
    private JLabel EmailLabel;
    private JLabel PasswordLabel;
    private JLabel ConfirmPassword;
    private JPanel ElectionComission;
    private JLabel EC;
    private JButton SUBMITButton;
    private JPanel ButtonPanel;
    private JButton LOGINButton;

    public RegisterForm(JFrame parent) {
        super(parent, "Create a new account", true);
        setContentPane(mainpanel);
        setMinimumSize(new Dimension(450, 500));
        setLocationRelativeTo(parent);
        SUBMITButton.addActionListener(e -> registerUser());
        LOGINButton.addActionListener(e -> {
            loginVoter();
            dispose();
        });
        setVisible(true);
    }

    public RegisterForm(Balloting parent) {
        super(parent, "Create a new account", true);
        setContentPane(mainpanel);
        setMinimumSize(new Dimension(450, 500));
        setLocationRelativeTo(parent);
        SUBMITButton.addActionListener(e -> registerUser());
        setVisible(true);
    }

    private void registerUser() {
        String name = namefield.getText().trim();
        String mail = emailfield.getText().trim();
        String id = Voteriffield.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String confirmpass = new String(confirmpassword.getPassword()).trim();

        if (name.isEmpty() || mail.isEmpty() || id.isEmpty() || password.isEmpty() || confirmpass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Name should not contain numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!id.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Voter ID should contain only numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!mail.contains("@") || !mail.matches(".*\\.[a-z]+")) {
            JOptionPane.showMessageDialog(this, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!password.equals(confirmpass)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String filePath = "C:\\Users\\PMLS\\Desktop\\E-VOTING SYSTEM\\src\\FILES\\VOTERS.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(id + "," + name + "," + mail + "," + password);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Voter Registered Successfully!");
            Voteriffield.setText("");
            namefield.setText("");
            emailfield.setText("");
            passwordField.setText("");
            confirmpassword.setText("");
        } catch (IOException io) {
            JOptionPane.showMessageDialog(this, "Error writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loginVoter() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.setSize(495, 350);
        new Loginform(loginFrame);
    }
}
