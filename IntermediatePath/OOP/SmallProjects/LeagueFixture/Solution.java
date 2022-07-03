package LeagueFixture;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;

public class Solution {
    public static void main(String[] args) {
        // If we have 7 teams, we will add a new team called Pass. So we will have 8 teams.
        int length = args.length % 2 == 1 ? args.length + 1 : args.length;

        if (length <= 1) {
            System.out.println("There cannot be any fixture with less than 2 teams");
            System.exit(1);
        }

        // Fill teams table with command line arguments
        Team[] teams = new Team[length];
        fillTeams(length, teams, args);

        // This array holds the matches but in reverse order
        // E.g. Round 1 : Galatasaray vs Fenerbahce - Besiktas vs Trabzonspor
        // matchesReversed[1] = Fenerbahce vs Galatasaray - Trabzonspor vs Besiktas
        StringBuilder[] matchesReversed = new StringBuilder[length];
        for (int i = 0; i < length; i++)
            matchesReversed[i] = new StringBuilder();

        // Printing the matches done
        printFirstHalf(length, teams, matchesReversed);
        printSecondHalf(length, matchesReversed);

        // For testing
        // printMatchCounts(teams);
    }

    public static void fillTeams(int length, Team[] teams, String[] args) {
        for (int i = 0; i < length; i++) {
            try  {
                teams[i] = new Team(args[i]);
            } catch (Exception ignored) {}
        }

        // If length is increment due to odd numbered teams, args[i] will give exception at i = length --- (length = 8, args.length = 7)
        // Stopped that with try-catch, now teams[length - 1] will be empty, put the Pass here.
        if (teams[length - 1] == null)
            teams[length - 1] = new Team("Pass");
    }

    public static void printFirstHalf(int length, Team[] teams, StringBuilder[] matchesReversed) {
        for (int round = 1; round < length; round++) {
            StringBuilder sb = new StringBuilder();
            String roundString = "Round " + round;

            // A team can play only 1 match in a round, so this hashset checks that
            HashSet<Integer> teamsRound = new HashSet<>();

            for (int i = 0; i < length; i++) {
                // If the team has played a match in this round, skip that
                if (teamsRound.contains(i)) continue;

                for (int j = 0; j < length; j++) {
                    // If the team has played a match in this round or i == j (same team), skip that
                    if (i == j || teamsRound.contains(j)) continue;

                    if (!teams[i].getMatches().contains(teams[j].getId())) {
                        teams[i].addMatch(teams[j].getId());
                        teams[j].addMatch(teams[i].getId());

                        teamsRound.add(i);
                        teamsRound.add(j);

                        sb.append(teams[i].getName()).append(" vs ").append(teams[j].getName()).append("\n");
                        matchesReversed[round].append(reverseMatch(teams, i, j)).append("\n");
                        break;
                    }
                }
            }

            // This if condition checks is the match count is correct.
            // Because sometimes due to greedy selecting fixture algorithm, there will be teams that cannot play in that round
            // To fix that, shuffled the list and restart the round until the if condition meets.
            if (teamsRound.size() != length) {
                // And also delete the reversed matches, because they are cancelled.
                matchesReversed[round].setLength(0);

                round--;
                Collections.shuffle(Arrays.asList(teams));
            }

            // If the condition meets, print the fixture of that round
            else {
                System.out.println(roundString + "\n" + sb);
            }
        }
    }

    // Generates a match name in reversed order -> E.g Galatasaray vs Besiktas -> Besiktas vs Galatasaray
    public static String reverseMatch (Team[] teams, int i, int j) {
        return teams[j].getName() + " vs " + teams[i].getName();
    }

    // Print all the matches in the matchesReversed array
    public static void printSecondHalf(int length, StringBuilder[] matchesReversed) {
        for (int round = length; round < length * 2 - 1; round++) {
            System.out.println("Round " + round);
            System.out.println(matchesReversed[round - length + 1]);
        }
    }

    // Only for testing, not necessary
    public static void printMatchCounts(Team[] teams) {
        for (Team team : teams) {
            if (Objects.equals(team.getName(), "Pass")) continue;

            int count = (Objects.equals(teams[teams.length - 1].getName(), "Pass")) ? team.getTotalMatchCount() - 1 : team.getTotalMatchCount();
            System.out.println(team.getName() + " --> " + count + " matches");
        }
    }
}
