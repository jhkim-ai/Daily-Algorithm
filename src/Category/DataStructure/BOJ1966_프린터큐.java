package Category.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1966_프린터큐 {

    private static int N, M;
    private static List<Integer> rankList;
    private static Queue<Integer> qRank;
    private static Queue<Doc> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t<= T; ++t){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            rankList = new ArrayList<>();
            q = new LinkedList<>();
            qRank = new LinkedList<>();

            st = new StringTokenizer(br.readLine(), " ");
            for(int idx = 0; idx < N; ++idx){
                int rank = Integer.parseInt(st.nextToken());
                boolean selected = idx == M ? true : false;

                Doc doc = new Doc(idx, rank, selected);
                rankList.add(rank);
                q.offer(doc);
            }

            Collections.sort(rankList, Collections.reverseOrder());
            for(int i = 0; i < N; ++i){
                qRank.offer(rankList.get(i));
            }
            int ans = getTimes();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static int getTimes(){
        int times = 1;

        while(!q.isEmpty()){
            Doc now = q.poll();

            if(now.rank == qRank.peek() && now.selected){
                return times;
            }

            if(now.rank != qRank.peek()){
                q.offer(now);
                continue;
            }

            ++times;
            qRank.poll();

        }

        return -1;
    }

    public static class Doc{
        int idx;
        int rank;
        boolean selected;

        public Doc(int idx, int rank, boolean selected){
            this.idx = idx;
            this.rank = rank;
            this.selected = selected;
        }
    }
}
