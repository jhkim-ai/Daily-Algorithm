
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, M;
    static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 4방향 탐색: 상, 하, 좌, 우

    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로 길이
        M = Integer.parseInt(st.nextToken()); // 가로 길이

        boolean[][][] visited = new boolean[N][M][16];  // 방문 표시
        char[][] map = new char[N][M];                  // Map 정보
        Queue<Ssafy> queue = new LinkedList<Ssafy>();       // BFS 를 위한 Queue

        // ====== 입력 ====== //
        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m);
                if (map[n][m] == 'P') {
                    queue.offer(new Ssafy(n, m, 0));
                    visited[n][m][0] = true;
                    map[n][m] = '.';
                }
            }
        }

        // ====== 알고리즘 시작 ====== //
        int answer = -1;    // 정답
        int time = 0;       // 흘러간 시간

        // BFS 탐색 시작
        loop: while (!queue.isEmpty()) {
            int size = queue.size();
            time++;

            while (size-- > 0) {
                Ssafy now = queue.poll();

                for (int i = 0; i < 4; i++) {     // 4방향 탐색
                    int nX = now.x + delta[i][0]; // 새 좌표
                    int nY = now.y + delta[i][1];
                    int nVisit = now.visit;       // 현재 방문 도시 상황

                    // 배열 범위 밖 or 빙산이 아닌 갈 수 있는 길이라면, 계속 전진
                    while (isIn(nX, nY) && map[nX][nY] != '#') {
                        if (map[nX][nY] != '.') {   // 도시에 도착했다면
                            int loc = 0;
                            // 도시 정보를 숫자로 변환
                            switch (map[nX][nY]) {
                                case 'S':
                                    loc = 0;
                                    break;
                                case 'D':
                                    loc = 1;
                                    break;
                                case 'G':
                                    loc = 2;
                                    break;
                                case 'M':
                                    loc = 3;
                                    break;
                            }

                            nVisit = nVisit | 1 << loc; // 방문 표시
                        }
                        nX += delta[i][0];  // 좌표 갱신
                        nY += delta[i][1];
                    }

                    nX -= delta[i][0]; // 배열 범위 밖 or 빙산의 위치이기에 뒤로 한 칸 물러간다.
                    nY -= delta[i][1];

                    if (nVisit == 15) { // 모든 도시를 방문했다면, 종료
                        answer = time;
                        break loop;
                    }

                    // 도시를 방문한 적이 없다면, visited 에 방문 체크 & 다음 경로를 Queue 에 삽입
                    if (!visited[nX][nY][nVisit]) {
                        visited[nX][nY][nVisit] = true;
                        queue.offer(new Ssafy(nX, nY, nVisit));
                    }
                }
            }
        }

        System.out.println(answer); // 정답 출력
    }

    // '김싸피(P)'의 위치를 저장하기 위한 변수
    static private class Ssafy {
        int x, y, visit;

        public Ssafy(int x, int y, int visit) {
            this.x = x;
            this.y = y;
            this.visit = visit;
        }

    }

    // 배열 범위 유효성 검사
    static boolean isIn(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M) {
            return true;
        }
        return false;
    }
}