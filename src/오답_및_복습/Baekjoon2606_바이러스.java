package 오답_및_복습;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.*;

// 최단 거리는 bfs
public class Baekjoon2606_바이러스 {

    static int N, M, ans = -1;      // 감염된 컴퓨터에서 1번 컴퓨터를 빼야함으로 초기값을 -1
    static List<Integer>[] list;    // 인접 리스트

    public static void main(String[] args) throws Exception{
        
        // ------ 데이터 입력 ------ // 
        
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(str));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 인접리스트 초기화
        list = new ArrayList[N+1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        // 인접리스트에 간선 추가
        // 주의! 간선이 주어졌음으로 양 node 에 추가해야한다
        // ex. (1 2)인 경우, 1의 리스트와 2의 리스트에 각각 서로 추가
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int next_node = Integer.parseInt(st.nextToken());
            list[idx].add(next_node);
            list[next_node].add(idx);
        }


        // ------ 알고리즘 시작 ------ //

        // dfs 혹은 bfs
        dfs(1, new boolean[N+1]);
        //bfs(new boolean[N+1]);
        System.out.println(ans);
    }

    static void dfs(int v, boolean[] visited){
        visited[v] = true;
        ans++;
        for (int i = 0; i < list[v].size(); i++) {
            if(!visited[list[v].get(i)])
                dfs(list[v].get(i), visited);
        }
    }

    // bfs
    // 위의 ans = 0 으로 바꿔야함
    static Queue<Integer> q = new LinkedList<>();

    static void bfs(boolean [] visited){

        q.offer(1);
        visited[1] = true;

        while(!q.isEmpty()){
            int cur_node = q.poll();
            for (int i = 0; i < list[cur_node].size(); i++) {
                if(!visited[list[cur_node].get(i)]) {
                    visited[list[cur_node].get(i)] = true;
                    q.offer(list[cur_node].get(i));
                    ans++;
                }
            }
        }
    }
    static String str = "7\n" +
            "6\n" +
            "1 2\n" +
            "2 3\n" +
            "1 5\n" +
            "5 2\n" +
            "5 6\n" +
            "4 7";
}
