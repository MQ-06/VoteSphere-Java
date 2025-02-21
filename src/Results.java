import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Results extends JDialog {

    public Results(Window parent, List<Candidate> sortedCandidates) {
        super((JDialog) parent, "Election Results", true);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        // Header for the results
        JLabel header = new JLabel("Election Results");
        header.setFont(new Font("Arial", Font.BOLD, 30));
        header.setForeground(Color.GREEN);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(header);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        if (!sortedCandidates.isEmpty()) {
            System.out.println("Winner: " + sortedCandidates.get(0).getName());  // Debugging
            Candidate winner = sortedCandidates.get(0);
            JLabel winnerLabel = new JLabel("Winner: " + winner.getName() + " - Votes: " + winner.getVotes());
            winnerLabel.setFont(new Font("Arial", Font.PLAIN, 20));
            winnerLabel.setForeground(Color.YELLOW);
            winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(winnerLabel);

            panel.add(Box.createRigidArea(new Dimension(0, 18)));
        }

        if (sortedCandidates.size() > 1) {
            JLabel otherCandidatesHeader = new JLabel("Other Candidate Positions:");
            otherCandidatesHeader.setFont(new Font("Arial", Font.BOLD, 18));
            otherCandidatesHeader.setForeground(Color.GREEN);
            otherCandidatesHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(otherCandidatesHeader);

            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        for (int i = 1; i < sortedCandidates.size(); i++) {
            Candidate candidate = sortedCandidates.get(i);
            System.out.println((i) + ". " + candidate.getName() + " - Votes: " + candidate.getVotes());
            JLabel label = new JLabel((i) + ". " + candidate.getName() + " - Votes: " + candidate.getVotes());
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel footer = new JLabel("Thank you for participating!");
        footer.setFont(new Font("Arial", Font.ITALIC, 14));
        footer.setForeground(Color.GREEN);
        footer.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(footer);

        add(panel, BorderLayout.CENTER);

        setSize(400, 350);
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
