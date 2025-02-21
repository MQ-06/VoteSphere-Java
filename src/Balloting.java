import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List; // Correct import


public class Balloting extends JDialog {

    private JPanel MainPanel;
    private JButton CASTVOTEButton;
    private JButton VIEWCANDIDATESButton;
    private JButton ADDVOTERButton;
    private JPanel secPanel;
    private JPanel UpperPanel;
    private JPanel ResultPanel;
    private JLabel header;
    private JLabel voteicon;
    private JPanel VOTE;
    private JLabel Candidateicon;
    private JPanel viewcand;
    private JPanel addvoter;
    private JLabel votericon;
    private JLabel addcand;
    private JButton ADDCANDIDATEButton;
    private JLabel removevoter;
    private JButton REMOVEVOTERButton;
    private JLabel viewresult;
    private JButton VIEWRESULTSButton1;
    private CandidateManager candidateManager;

    public Balloting(JFrame parent) {
        super(parent, "Balloting Interface", true);
        setContentPane(MainPanel);
        setMinimumSize(new Dimension(500, 600));

        setLocationRelativeTo(parent);
        candidateManager = new CandidateManager();


        CASTVOTEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCastVoteWindow();
            }
        });

        VIEWCANDIDATESButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewCandidatesWindow();
            }
        });

        ADDVOTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               openAddVoterWindow();
            }
        });

        VIEWRESULTSButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openResultsWindow();

            }
        });

        ADDCANDIDATEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCandidate();
            }
        });

        REMOVEVOTERButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRemoveVoterWindow();
            }
        });
        setVisible(true);

    }

    private void openCastVoteWindow() {
        new CastVote(this);

    }

    private void openViewCandidatesWindow() {
       new Candidates(this);
    }

    private void openAddVoterWindow() {
new RegisterForm(this);

    }

    private void openResultsWindow() {
        List<Candidate> sortedCandidates = candidateManager.getSortedCandidates();
        new Results(this, sortedCandidates);
    }


private void addCandidate(){
    new ADD_CANDIDATES(this);

}
    private void openRemoveVoterWindow() {
        new RemoveVoterDialog(this);
    }

}
