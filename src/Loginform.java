import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Loginform extends JDialog {
    private JPanel mainFormPanel;
    private JLabel image_pak;
    private JTextField VOTEID;
    private JPasswordField PASS;
    private JButton PROCEEDTOVOTEButton;
    private JButton CANCELButton;
    private JPanel INFOfill;
    private JLabel ID;
    private JPanel BUTTONS;
    private JPanel HEADER;
    private JLabel PAK;
    private JLabel ELECTION;
    private JLabel DATE;


    public Loginform(JFrame parent) {
        super(parent, "Login", true);
        setContentPane(mainFormPanel);
        setMinimumSize(new Dimension(480, 450));
        setLocationRelativeTo(parent);

        PROCEEDTOVOTEButton.addActionListener(e -> verifyLogin());
        CANCELButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void verifyLogin() {
        String voterid = VOTEID.getText().trim();
        String pass = new String(PASS.getPassword()).trim();
        boolean isAuthenticated = false;
        String filePath = "C:\\Users\\PMLS\\Desktop\\E-VOTING SYSTEM\\src\\FILES\\VOTERS.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                if (details.length == 4) {
                    if (details[0].trim().equals(voterid) && details[3].trim().equals(pass)) {
                        isAuthenticated = true;
                        break;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading from the file", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        if (isAuthenticated) {
            JOptionPane.showMessageDialog(this, "Login successful! Proceeding to vote...");
            SwingUtilities.invokeLater(() -> {
                JFrame ballotform = new JFrame("Balloting Interface");
                ballotform.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ballotform.setSize(590, 650);

                JPanel contentPane = new JPanel();
                contentPane.setBackground(Color.BLACK);

                ballotform.setContentPane(contentPane);

                new Balloting(ballotform);

//                ballotform.setVisible(true);
            });


            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid voter ID or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
