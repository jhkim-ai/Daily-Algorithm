package Category.Topological_Sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2252_줄세우기 {

    private static int N, M;
    private static int[] cntDegree;
    private static List<List<Integer>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cntDegree = new int[N+1];

        graph = new ArrayList<>();
        for(int i = 0; i <= N; ++i) {
            graph.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int small = Integer.parseInt(st.nextToken());
            int tall = Integer.parseInt(st.nextToken());

            graph.get(small).add(tall);
            cntDegree[tall]++;
        }

        String ans = topologicalSorting();
        System.out.println(ans);
    }

    public static String topologicalSorting(){
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();

        for(int idx = 1; idx <= N; ++idx) {
            if(cntDegree[idx] == 0) {
                q.offer(idx);
            }
        }

        while(!q.isEmpty()) {
            int now = q.poll();
            sb.append(String.format("%d ", now));

            for(int next : graph.get(now)) {
                cntDegree[next]--;
                if(cntDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }
}
