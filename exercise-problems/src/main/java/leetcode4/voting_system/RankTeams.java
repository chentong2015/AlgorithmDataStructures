package leetcode4.voting_system;

import java.util.Arrays;

public class RankTeams {

    public static void main(String[] args) {
        RankTeams instance = new RankTeams();
        String[] votes = {"WXYZ", "XYZW"};
        System.out.println(instance.rankTeams(votes));
    }

    public String rankTeams(String[] votes) {
        int rankLength = votes[0].length();
        int[][] map = new int[26][rankLength + 1];
        for(int i = 0; i < 26; i++) {
            map[i][rankLength] = i;
        }

        for (String vote : votes) {
            for (int j = 0; j < rankLength; j++) {
                map[vote.charAt(j) - 'A'][j]++;
            }
        }
        Arrays.sort(map, (a, b) ->{
            for(int i = 0; i < rankLength; i++){
                if(a[i] < b[i]) {
                    return 1;
                }
                if(a[i] > b[i]) {
                    return -1;
                }
            }
            return 0;
        });

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rankLength; i++){
            result.append((char)('A' + map[i][rankLength]));
        }
        return result.toString();
    }
}
