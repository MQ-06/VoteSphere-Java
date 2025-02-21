import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ADD_CANDIDATES extends JDialog {
    private JPanel MAINPANEL;
    private JTextField c_name_text;
    private JTextField c_party_text;
    private JTextField c_votes_count;
    private JButton Submit_button;
    private JPanel TWOAPNEL;
    private JPanel InfoPanel;
    private JPanel TextPanel;
    private JLabel c_name;
    private JLabel c_party;
    private JLabel c_votes;
    private JLabel label_c;

    public ADD_CANDIDATES(Balloting parent) {
        super(parent, "Add Candidates", true);
        setContentPane(MAINPANEL);
        setMinimumSize(new Dimension(400, 360));
        setLocationRelativeTo(parent);
        Submit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCandidate();
            }
        });
        setVisible(true);
    }

    private void addCandidate() {
        String name = c_name_text.getText().trim();
        String party = c_party_text.getText().trim();
        String votesText = c_votes_count.getText().trim();

        if (name.isEmpty() || party.isEmpty() || votesText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int votes = Integer.parseInt(votesText);
            if (votes != 0) {
                JOptionPane.showMessageDialog(this, "Votes count must be exactly 0.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String filePath = "C:\\Users\\PMLS\\Desktop\\E-VOTING SYSTEM\\src\\FILES\\CANDIDATES.txt";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                writer.write(name + "," + party + "," + votes);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Candidate Registered Successfully!");

                c_name_text.setText("");
                c_party_text.setText("");
                c_votes_count.setText("");
            } catch (IOException io) {
                JOptionPane.showMessageDialog(this, "Error writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Votes count must be a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}