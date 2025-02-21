import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class CandidateManager {
    private List<Candidate> candidates;
    private String fileName = "C:\\Users\\PMLS\\Desktop\\E-VOTING SYSTEM\\src\\FILES\\CANDIDATES.txt";

    public CandidateManager() {
        candidates = new ArrayList<>();
        loadCandidatesFromFile();
    }

    public List<String> getCandidateNames() {
        List<String> candidateNames = new ArrayList<>();
        for (Candidate candidate : candidates) {
            candidateNames.add(candidate.getName());
        }
        return candidateNames;
    }

    public void updateVote(String candidateName) {
        candidateName = candidateName.trim(); 
        boolean candidateFound = false;

        for (Candidate candidate : candidates) {
            if (candidate.getName().equalsIgnoreCase(candidateName)) {
                candidate.incrementVote();
                candidateFound = true;
                break;
            }
        }

        if (candidateFound) {
            saveCandidatesToFile();
            System.out.println("Vote updated for candidate: " + candidateName);
        } else {
            System.err.println("Candidate not found: " + candidateName);
        }
    }

    private void loadCandidatesFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            System.err.println("File does not exist: " + fileName);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 2) {
                    System.err.println("Invalid line format: " + line);
                    continue;
                }

                String name = parts[0].trim();
                int votes = 0;
                try {
                    votes = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number format for votes: " + parts[1]);
                    continue;
                }

                Candidate candidate = new Candidate(name, votes);
                candidates.add(candidate);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error loading candidates from file: " + e.getMessage());
        }
    }

    private void saveCandidatesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Candidate candidate : candidates) {
                writer.write(candidate.getName() + "," + candidate.getVotes());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving candidates to file: " + e.getMessage());
        }
    }

    public List<Candidate> getSortedCandidates() {
        List<Candidate> sortedCandidates = new ArrayList<>(candidates);
        sortedCandidates.sort(Comparator.comparingInt(Candidate::getVotes).reversed());
        return sortedCandidates;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }
}
