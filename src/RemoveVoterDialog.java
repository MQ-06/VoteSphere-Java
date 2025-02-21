import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveVoterDialog extends JDialog {

    private JTextField idField;
    private JPasswordField passwordField;
    private JButton removeButton;
    private JButton cancelButton;

    private static final String FILE_NAME = "C:\\Users\\PMLS\\Desktop\\E-VOTING SYSTEM\\src\\FILES\\VOTERS.txt";

    public RemoveVoterDialog(Balloting parent) {
        super(parent, "Remove Voter", true);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Enter your ID and password to remove your voter account:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE); // Set text color
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        formPanel.setBackground(Color.BLACK); // Set background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding around components
        gbc.anchor = GridBagConstraints.WEST;

        JLabel idLabel = new JLabel("ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 16));
        idLabel.setForeground(Color.WHITE); // Set text color
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(idLabel, gbc);

        idField = new JTextField(20); // Set preferred width
        idField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        formPanel.add(idField, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.WHITE); // Set text color
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(20); // Set preferred width
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.BLACK); // Set background color

        removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14)); // Reduced font size
        removeButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size
        buttonPanel.add(removeButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14)); // Reduced font size
        cancelButton.setPreferredSize(new Dimension(100, 30)); // Set preferred size
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Set dialog properties
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeVoter();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setSize(450, 250); // Adjusted size for better fit
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    private void removeVoter() {
        String id = idField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        List<String> voters = readVotersFromFile();
        boolean found = false;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String voter : voters) {
                String[] parts = voter.split(",");
                if (parts[0].equals(id) && parts[3].equals(password)) {
                    found = true;
                    continue;
                }
                writer.write(voter);
                writer.newLine();
            }

            if (found) {
                JOptionPane.showMessageDialog(this, "Voter removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No matching voter found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error removing voter: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        dispose();
    }

    private List<String> readVotersFromFile() {
        List<String> voters = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                voters.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading voters file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return voters;
    }
}
