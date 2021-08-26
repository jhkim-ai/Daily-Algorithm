package Test.Toss_2021;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution2 {

    private static int servers;
    private static boolean sticky;
    private static int[] requests;

    public static void main(String[] args) {
        servers = 2;
        sticky = true;
        requests = new int[]{1, 1, 2, 2};
        int[][] a = new int[3][];

        int idx = 0;
        HashMap<Integer, Integer> hm = new HashMap<>();
        List<Integer>[] lists = new List[servers];
        for (int i = 0; i < servers; ++i) {
            lists[i] = new ArrayList<>();
        }

        for (int req : requests) {
            // 이미 저장 되었다면
            if (sticky && hm.containsKey(req)) {
                idx = hm.get(req);
                lists[idx].add(req);
            } else {
                lists[idx].add(req);
                if (sticky) hm.put(req, idx);
            }
            idx = (idx + 1) % servers;
        }

        int[][] answer = new int[servers][];
        for(int i = 0; i < servers; ++i){
            answer[i] = new int[lists[i].size()];
            for(int j = 0; j < lists[i].size(); ++j){
                answer[i][j] = lists[i].get(j);
            }
        }
    }
}
