import java.util.*;
import java.io.*;

public class exercise {

    static int N, M, ans, cnt;
    static ArrayList[] tall;
    static ArrayList[] small;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(br.readLine());    // 학생 수
            M = Integer.parseInt(br.readLine());    // 비교 횟수
            ans = 0;
            tall = new ArrayList[N+1];              // 나보다 큰 사람 목록
            small = new ArrayList[N+1];             // 나보다 작은 사람 목록
            for (int i = 0; i < N+1; ++i) {
                tall[i] = new ArrayList<Integer>();
                small[i] = new ArrayList<Integer>();
            }

            for (int i = 0; i < M; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                int sMan = Integer.parseInt(st.nextToken());
                int tMan = Integer.parseInt(st.nextToken());

                tall[sMan].add(tMan);
                small[tMan].add(sMan);
            }

//            for(int i = 1; i < small.length; ++i){
//                System.out.print(i+"번째: ");
//                for(int j = 0; j<small[i].size(); ++j){
//                    System.out.print(small[i].get(j) + " ");
//                }
//                System.out.println();
//            }

            // ---------- 알고리즘 시작 ---------- //

            // Idea. 내가 몇 번째인지를 알려면, 나를 기준으로
            //       (내 위의 명 수 + 내 아래의 명 수)가 N-1명이면 된다.

            for(int i = 1 ; i < N+1; ++i){
//                System.out.println("==========");
//                System.out.println(i+"번째: ");
                cnt = 0;
                dfs(tall, i, new boolean[N+1]);
                dfs(small, i, new boolean[N+1]);
                if(cnt == N-1) {
//                    System.out.println("###"+i);
                    ans++;
                }
//                System.out.println();
            }

            sb.append(String.format("#%d %d\n", t, ans));
        }
        System.out.println(sb);
    }

    static void dfs(ArrayList[] list, int num, boolean[] visited){

        visited[num] = true;

        // 나(num)보다 큰(작은) 사람들의 목록
        List<Integer> men = list[num];

        // 나(num)보다 큰(작은) 사람이 없다면
        if(men.size() == 0){
            return;
        }

//        System.out.print("("+cnt+")"+"->");
        for(int i = 0 ; i < men.size(); ++i){
            int next = men.get(i);
            if(visited[next]) continue;
            cnt++;
            dfs(list, next, visited);
        }
    }

    static String input = "1\n" +
            "6\n" +
            "6\n" +
            "1 5\n" +
            "3 4\n" +
            "5 4\n" +
            "4 2\n" +
            "4 6\n" +
            "5 2";
}
