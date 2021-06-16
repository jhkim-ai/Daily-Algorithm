package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ5014_스타트링크 {

    public static int F, S, G, U, D;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken()); // 전체 층
        S = Integer.parseInt(st.nextToken()); // 강호가 있는 층
        G = Integer.parseInt(st.nextToken()); // 사무실 층
        U = Integer.parseInt(st.nextToken()); // 위로 U층 이동
        D = Integer.parseInt(st.nextToken()); // 아래로 D층 이동

        System.out.println(bfs());
    }

    public static String bfs(){
        Queue<Integer> q = new LinkedList<>();
        int[] visited = new int[F+1];

        Arrays.fill(visited, -1); // 시작 층 초기화
        visited[S] = 0;
        q.offer(S); // S 층에서 시작

        while(!q.isEmpty()){
            int now = q.poll(); // 현재 층

            if(now == G) return String.valueOf(visited[G]); // 사무실 도착

            int next = now + U; // 위 층으로 이동
            if(isIn(next) && visited[next] == -1) { // 방문했던 층이 아니라면 이동
                visited[next] = visited[now] + 1;
                q.offer(next);
            }

            next = now - D; // 아래 층으로 이동
            if(isIn(next) && visited[next] == -1) { // 방문했던 층이 아니라면 이동
                visited[next] = visited[now] + 1;
                q.offer(next);
            }
        }

        return "use the stairs";
    }

    public static boolean isIn(int now){
        return now >= 1 && now <= F;
    }
}
