package leetcode4.voting_system;

import java.util.HashMap;

// Rank Teams by Votes
// In a special ranking system, each voter gives a rank from highest to lowest
// to all teams participating in the competition.
//
// The ordering of teams is decided by who received the most position-one votes
// - If two or more teams tie in the first position,
//   we consider the second position to resolve the conflict,
// - if they tie again,
//   we continue this process until the ties are resolved.
// - If two or more teams are still tied after considering all positions,
//   we rank them alphabetically based on their team letter.
//
// You are given an array of strings votes
// which is the votes of all voters in the ranking systems.
// Sort all teams according to the ranking system described above.
//
// Return a string of all teams sorted by the ranking system.
//
// 1 <= votes.length <= 1000
// 1 <= votes[i].length <= 26
// votes[i].length == votes[j].length for 0 <= i, j < votes.length.
// votes[i][j] is an English uppercase letter.
// All characters of votes[i] are unique.
// All the characters that occur in votes[0]
// also occur in votes[j] where 1 <= j < votes.length.
public class RankTeamsByVotes {

    // TODO. 先对票数进行预统计结果，然后执行三层排序规则，每次取排名Top1
    // 二维数组统计的票数结果满足数独的要求
    // votes = ["ABC","ACB","ABC","ACB","ACB"] -> "ACB"
    //    1  2  3
    // A: 5  0  0
    // B: 0  2  3
    // C: 0  3  2
    //
    // votes = ["WXYZ","XYZW"] -> "XWYZ"
    //    1  2  3  4
    // W: 1  0  0  1
    // X: 1  1  0  0
    // Y: 0  1  1  0
    // Z: 0  0  1  1
    //
    // votes = ["ZMNAGUEDSJYLBOPHRQICWFXTVK"] -> "ZMNAGUEDSJYLBOPHRQICWFXTVK"
    //
    // O(L*N + 26*L*N) L is count of voters, N is number of teams
    // O(N*N)          N <= 26 有限数量的队伍数量
    public String rankTeams(String[] votes) {
        int rankLength = votes[0].length();
        HashMap<Character, int[]> teamRanksMap = new HashMap<>();
        for (String vote: votes) {
            for (int index = 0; index < rankLength; index++) {
                char team = vote.charAt(index);
                int[] ranks = teamRanksMap.getOrDefault(team, new int[rankLength]);
                ranks[index]++;
                teamRanksMap.put(team, ranks);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        while (teamRanksMap.size() > 0) {
            char topTeam = ' ';
            for (char team: teamRanksMap.keySet()) {
                if (topTeam == ' ') {
                    topTeam = team;
                    continue;
                }
                int compareResult = compareRanks(teamRanksMap.get(topTeam), teamRanksMap.get(team));
                if (compareResult < 0) {
                    topTeam = team;
                } else if (compareResult == 0) {
                    // Rank them alphabetically based on their team letter
                    if (topTeam > team) {
                        topTeam = team;
                    }
                }
            }
            stringBuilder.append(topTeam);
            teamRanksMap.remove(topTeam);
        }
        return stringBuilder.toString();
    }

    // TODO. 使用Arrays.sort() API来实现排序
    // return 1 if ranks1 > ranks2
    // return 0 if ranks1 = ranks2
    // return -1 if ranks1 < ranks2
    private int compareRanks(int[] ranks1, int[] ranks2) {
        for (int index = 0; index < ranks1.length; index++) {
            if (ranks1[index] > ranks2[index]) {
                return 1;
            } else if (ranks1[index] == ranks2[index]) {
                continue;
            } else {
                return -1;
            }
        }
        return 0;
    }
}
