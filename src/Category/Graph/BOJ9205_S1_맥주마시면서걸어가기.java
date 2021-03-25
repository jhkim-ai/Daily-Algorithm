package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ9205_S1_맥주마시면서걸어가기 {

    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            // --------- 데이터 입력 --------- //
            int n = Integer.parseInt(br.readLine());
            Point[] points = new Point[n + 2];      // 집, 편의점, 페스티벌 좌표


            for (int i = 0; i < n + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                points[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            // --------- 알고리즘 시작 --------- //
            bfs(points, n);
        }
        System.out.println(sb);
    }

    static void bfs(Point[] points, int n) {
        Queue<Point> q = new LinkedList<>();
        boolean isEnd = false;
        boolean[] visited = new boolean[n + 2];   // 방문 체크용
        Point start = points[0];                // 시작 위치
        Point end = points[points.length - 1];    // 도착 위치
        q.offer(start);                         // 시작점 선정

        // bfs 탐색
        while (!q.isEmpty()) {
            Point now = q.poll();

            // 도착 시, 종료
            if (now.equals(end)) {
                isEnd = true;
                break;
            }

            // 모든 방문할 장소 중 거리가 1,000 이하인 장소만 Queue에 넣는다.
            // n <= 100 이기에 가능
            for (int i = 1; i < n + 2; i++) {
                if (!visited[i] && Math.abs(points[i].x - now.x) + Math.abs(points[i].y - now.y) <= 1000){
                    q.add(points[i]);
                    visited[i] = true;
                }
            }
        }

        // 출력
        if(isEnd)
            sb.append("happy");
        else
            sb.append("sad");
        sb.append("\n");
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static String input = "2\n" +
            "2\n" +
            "0 0\n" +
            "1000 0\n" +
            "1000 1000\n" +
            "2000 1000\n" +
            "2\n" +
            "0 0\n" +
            "1000 0\n" +
            "2000 1000\n" +
            "2000 2000";
}
