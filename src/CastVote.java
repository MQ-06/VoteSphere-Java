import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class CastVote extends JDialog {
    private CandidateManager candidateManager;
    private JCheckBox[] candidateCheckboxes;
    private JButton SUBMITVOTEButton;
    private JPanel MAINBALLOTING;
    private JCheckBox VOTECheckBox;
    private JCheckBox VOTECheckBox1;
    private JCheckBox VOTECheckBox2;
    private JCheckBox VOTECheckBox3;
    private JCheckBox VOTECheckBox4;
    private JCheckBox VOTECheckBox5;
    private JCheckBox VOTECheckBox6;
    private JCheckBox VOTECheckBox7;
    private JPanel FORM;
    private JPanel SUBMIT;
    private JPanel PARTIES;
    private JPanel HEADINGS;
    private JLabel one;
    private JLabel two;
    private JLabel three;
    private JLabel four;
    private JLabel five;
    private JLabel six;
    private JLabel seven;
    private JLabel eight;
    private JLabel IK;
    private JLabel BB;
    private JLabel MN;
    private JLabel AA;
    private JLabel FR;
    private JLabel JT;
    private JLabel SS;
    private JLabel SR;
    private JLabel PTI;
    private JLabel PPP;
    private JLabel PMLN;
    private JLabel MQM;
    private JLabel PMLNT;
    private JLabel PTIT;

    public CastVote(JDialog parent) {
        super(parent, "Vote Candidates", true);
        setContentPane(MAINBALLOTING);
        setMinimumSize(new Dimension(600, 500));
        setLocationRelativeTo(parent);

        candidateManager = new CandidateManager();

        candidateCheckboxes = new JCheckBox[]{
                VOTECheckBox, VOTECheckBox1, VOTECheckBox2, VOTECheckBox3,
                VOTECheckBox4, VOTECheckBox5, VOTECheckBox6, VOTECheckBox7
        };

        ButtonGroup group = new ButtonGroup();
        for (JCheckBox checkbox : candidateCheckboxes) {
            group.add(checkbox);
        }

        List<String> candidateNames = candidateManager.getCandidateNames();
        int numCandidates = Math.min(candidateNames.size(), candidateCheckboxes.length);

        for (int i = 0; i < numCandidates; i++) {
            candidateCheckboxes[i].setText(candidateNames.get(i));
        }

        for (int i = numCandidates; i < candidateCheckboxes.length; i++) {
            candidateCheckboxes[i].setVisible(true);
        }

        SUBMITVOTEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitVote();
            }
        });

        setVisible(true);
    }

    private void submitVote() {
        boolean isVoted = false;

        for (JCheckBox checkbox : candidateCheckboxes) {
            if (checkbox.isSelected()) {
                String candidateName = checkbox.getText();
                try {
                    candidateManager.updateVote(candidateName);

                    JOptionPane.showMessageDialog(this, "Vote submitted for " + candidateName);

                    isVoted = true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error submitting vote: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            }
        }

        if (!isVoted) {
            JOptionPane.showMessageDialog(this, "Please select a candidate.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            dispose();
        }
    }
}