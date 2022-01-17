// https://www.acmicpc.net/problem/1600
// 말이 되고픈 원숭이
// bfs
package StepByStep.day210312;

import sun.net.sdp.SdpSupport;

import java.io.*;
import java.util.*;

public class BOJ_1600_말이되고픈원숭이_만기 {

    static class INFO {
        int x, y, horse, dis;

        INFO(int x, int y, int horse, int dis) {
            this.x = x;
            this.y = y;
            this.horse = horse;
            this.dis = dis;
        }
    }

    static final int[] dx = {0, 0, 1, -1, -1, -2, -2, -1, 1, 2, 2, 1};
    static final int[] dy = {1, -1, 0, 0, -2, -1, 1, 2, 2, 1, -1, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][] map = new int[H][W];
        for (int i = 0; i < H; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // --------- 알고리즘 시작 --------- //
        // Idea. visit 은 3차원 배열로 선언
        // 3차원 배열의 원소들은 말처럼 이동했을 때의 경우의 수를 저장한다.
        // 만약, 2차원으로만 진행한다면? -> 다른 경우의 수도 존재하는데 그것을 무시하는 경우가 발생한다.

        boolean[][][] visit = new boolean[H][W][K + 1];
        Queue<INFO> q = new LinkedList<>();

        // 초기화
        Arrays.fill(visit[0][0], true);    // 시작점 방문 표시
        q.offer(new INFO(0, 0, 0, 0)); // 시작점 q에 insert
        // x,y : 위치 c:말 이동횟수, dis: 동작 수

        // bfs
        while (!q.isEmpty()) {
            // q의 첫 번째 원소 가져오기
            int x = q.peek().x;    // x 좌표
            int y = q.peek().y;    // y 좌표
            int horse = q.peek().horse;    // c : 말처럼 이동한 횟수
            int dis = q.poll().dis;    // dis : 이동거리

            // 도착(결과)
            if (x == H - 1 && y == W - 1) {
                System.out.println(dis);
                return;
            }

            // 4방 탐색(상,하,좌,우)
            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 유효성 검사 (범위, 장애물, 방문한 곳)
                if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 1 || visit[nx][ny][horse]) continue;

                // 유효성 검사 통과 시,
                visit[nx][ny][horse] = true; // 현재 점 방문 표시
                q.offer(new INFO(nx, ny, horse, dis + 1)); //
            }

            // 말처럼 움직이는 횟수를 다 사용했을 시, Pass
            if (horse == K) continue;

            // 말처럼 움직인 횟수가 K번이 안될 때
            // "말처럼 움직이기"
            for (int i = 4; i < 12; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 유효성 검사 (범위, 장애물, c+1 번째에 말처럼 움직인 적이 있는 지)
                if (nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny] == 1 || visit[nx][ny][horse + 1]) continue;
                // c+1번째로 말을 움직임을 등록
                visit[nx][ny][horse + 1] = true;
                // 큐에 삽입
                q.offer(new INFO(nx, ny, horse + 1, dis + 1));
            }
        }
        // 종점에 도착 못할 시, -1 출력
        System.out.println(-1);
    }

}