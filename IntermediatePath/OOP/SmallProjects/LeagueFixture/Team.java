package LeagueFixture;

import java.util.HashSet;

public class Team {
    public static int numberOfTeams = 0;
    private HashSet<Integer> matches;
    private int id;
    private String name;
    private int totalMatchCount;

    public Team(String name) {
        this.id = numberOfTeams;
        this.matches = new HashSet<>();
        this.name = name;
        numberOfTeams++;
    }

    public void addMatch(int id) {
        this.matches.add(id);
        this.totalMatchCount++;
    }

    public int getTotalMatchCount() {
        return totalMatchCount;
    }


    public HashSet<Integer> getMatches() {
        return matches;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
