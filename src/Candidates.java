import javax.swing.*;
import java.awt.*;

public class Candidates extends JDialog {
    private JPanel MAINPAGE;
    private JPanel SIDEBAR;
    private JLabel LOGO;
    private JLabel VOTE;
    private JLabel NOW;
    private JPanel CANDIDATES;
    private JLabel SR;
    private JLabel FR;
    private JLabel SS;
    private JLabel FT;
    private JPanel PICS;
    private JPanel PARTIES;
    private JPanel NAMES;
    private JLabel IK;
    private JLabel AA;
    private JLabel BB;
    private JLabel MN;
    private JLabel IKP;
    private JLabel BBP;
    private JLabel MNP;
    private JLabel AAP;
    private JLabel FRP;
    private JLabel JTP;
    private JLabel SSP;
    private JLabel SRP;

    public Candidates(JDialog parent) {
        super(parent, "View Candidates", true);
        setContentPane(MAINPAGE);
        setMinimumSize(new Dimension(650, 500));
        setLocationRelativeTo(parent);
        setVisible(true);
    }

}
