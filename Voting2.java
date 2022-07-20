import java.util.*;

class Vote2 {
    String team;
    int[] ranks;
    Vote2(String team, int teamCount){
        this.team = team;
        this.ranks = new int[teamCount];
    }
}

class RankComparator2 implements Comparator<Vote2> {
    @Override
    public int compare(Vote2 v1, Vote2 v2) {
        int index = 0;
        while(index < v1.ranks.length) {
            if(v1.ranks[index] == v2.ranks[index]) {
                index++;
            } else {
                return v2.ranks[index] - v1.ranks[index];
            }
        }
        return v1.team.compareTo(v2.team);
    }
}


public class Voting2 {


    public static void main(String[] args) {
        List<String> vote1 = new ArrayList<>(Arrays.asList("A","B","C"));
        List<String> vote2 = new ArrayList<>(Arrays.asList("A","C","B"));
        List<String> vote3 = new ArrayList<>(Arrays.asList("A","B","C"));
        List<String> vote4 = new ArrayList<>(Arrays.asList("A","C","B"));
        List<String> vote5 = new ArrayList<>(Arrays.asList("A","C","B"));
        List<List<String>> input = new ArrayList<>(Arrays.asList(vote1,vote2,vote3,vote4,vote5));

        List<String> vote00 = new ArrayList<>(Arrays.asList("W","X","Y","Z"));
        List<String> vote01 = new ArrayList<>(Arrays.asList("X","Y","Z","W"));
        List<List<String>> input2 = new ArrayList<>(Arrays.asList(vote00,vote01));
        System.out.println(rankTeams(input2));
    }
    public static List<String> rankTeams(List<List<String>> votes) {
        if(votes.size() == 1) {
            return votes.get(0);
        }
        Map<String, Vote2> voteMap = new HashMap<>();
        int length = votes.get(0).size();
        for(List<String> vote: votes) {
            for(int i=0;i<length;i++) {
                String team = vote.get(i);
                Vote2 record = voteMap.getOrDefault(team, new Vote2(team, length));
                record.ranks[i]++;
                voteMap.put(team, record);
            }
        }
        List<Vote2> voteMasterList = new ArrayList<>(voteMap.values());
        Collections.sort(voteMasterList, new RankComparator2());
        List<String> answer = new LinkedList<>();
        for(Vote2 vote: voteMasterList) {
            answer.add(vote.team);
        }
        return answer;
    }

}
