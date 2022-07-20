import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.*;

class Vote {
    char team;
    int[] ranks;
    Vote(char team, int teamCount){
        this.team = team;
        this.ranks = new int[teamCount];
    }
}

class RankComparator implements Comparator<Vote> {
    @Override
    public int compare(Vote v1, Vote v2) {
        int index = 0;
        while(index < v1.ranks.length) {
            if(v1.ranks[index] == v2.ranks[index]) {
                index++;
            } else {
                return v2.ranks[index] - v1.ranks[index];
            }
        }
        return v1.team - v2.team;
    }
}


public class Voting {


    public static void main1(String[] args) {
        String[] votes = {"ABC","ACB","ABC","ACB","ACB"};
        String[] votes2 = {"WXYZ","XYZW"};
        String[] votes3 = {"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
        System.out.println(rankTeams(votes3));
    }
    public static String rankTeams(String[] votes) {
        if(votes.length == 1) {
            return votes[0];
        }
        Map<Character, Vote> voteMap = new HashMap<>();
        int length = votes[0].length();
        for(String vote: votes) {
            for(int i=0;i<length;i++) {
                char team = vote.charAt(i);
                Vote record = voteMap.getOrDefault(team, new Vote(team, length));
                record.ranks[i]++;
                voteMap.put(team, record);
            }
        }
        List<Vote> voteMasterList = new ArrayList<>(voteMap.values());
        Collections.sort(voteMasterList, new RankComparator());
        StringBuilder answer = new StringBuilder();
        for(Vote vote: voteMasterList) {
            answer.append(vote.team);
        }
        return answer.toString();
    }

}
