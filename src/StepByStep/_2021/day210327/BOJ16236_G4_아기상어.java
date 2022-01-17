package StepByStep.day210327;

import java.io.*;
import java.util.*;

public class BOJ16236_G4_아기상어 {

    static final int INF = Integer.MAX_VALUE;

    static int N, ans;
    static int sharkSize = 2;
    static int eat = 0;
    static int[][] map;
    static int[][] dist;    // 방문 여부와 거리를 동시에 check
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static Point shark;
    static int minX, minY, minDist;  // 거리가 가장 가까운 물고기 좌표 + 거리


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // ------ 입력 ------ //
        N = Integer.parseInt(br.readLine());
        ans = 0;
        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 9) {
                    shark = new Point(y, x);
                    map[y][x] = 0;  // 상어가 처음에 있던 자리로 올 수도 있기 때문에 0 으로 바꾸자
                }
            }
        }

        // ------ 알고리즘 ------ //
        int t = 0;
        while (true) {
            // 거리 + 우선순위 물고기 위치 초기화
            dist = new int[N][N];
            minY = INF;
            minX = INF;
            minDist = INF;

            bfs();
            // minY 와 minX 의 좌표가 바뀌었다는 것은 먹을 수 있다는 물고기가 존재 및 먹은 물고기의 위치로 이동
            // 물고기를 먹으면 먹은 물고기(eat)을 + 1
            if (minY != INF && minX != INF) {
                eat++;
                map[minY][minX] = 0;    // 먹은 물고기의 위치도 다시 올 수 있으니 "0"으로 바꿔줌
                shark.y = minY;         // 상어 위치 갱신
                shark.x = minX;
                ans += dist[minY][minX]; // 이동 거리 갱신

                // 먹이를 먹은 수와 상어의 크기가 같다면
                if (eat == sharkSize) {
                    sharkSize++;    // 상어 크기 "+ 1"
                    eat = 0;        // 먹은 물고기 "0"으로 초기화
                }
            }
            // minY & minX 가 MAX_VALUE 라는 것은, 먹이를 찾을 수 없다는 뜻이므로 "종료"
            else
                break;
        }

        System.out.println(ans);

    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(shark.y, shark.x));

        while (!q.isEmpty()) {
            Point now = q.poll(); // Queue 에서 꺼낸 현재값
            // 4방 탐색
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                // 유효성 검사 : (1) Map 의 범위, (2) 이동 가능, (3) 방문 여부 (dist[][] == 0 이면 방문 x)
                if (isIn(ny, nx) && isAbleToMove(ny, nx) && dist[ny][nx] == 0 && (ny != shark.y || nx != shark.x)) {
                    // 1st. 이동
                    dist[ny][nx] = dist[now.y][now.x] + 1;

                    // 2nd. 만약, 이동한 곳에 먹을 수 있는 물고기가 있다면?
                    // 우선순위에 맞는 물고기를 선택
                    if (isEdible(ny, nx)) {
                        // 먹이 1순위. 가장 가까운 물고기
                        if (minDist > dist[ny][nx]) {
                            minDist = dist[ny][nx];
                            setEdibleFishPosition(ny, nx); // 우선순위 물고기 좌표 갱신
                        }
                        // 먹이 2순위. 거리가 같다면 가장 위(Y가 작은)의 물고기
                        else if (minDist == dist[ny][nx]) {
                            // 먹이 3순위. 같은 행에 위치한다면, 가장 왼쪽(X가 작은)의 물고기
                            if (minY == ny) {
                                if (minX > nx) {
                                    setEdibleFishPosition(ny, nx); // 우선순위 물고기 좌표 갱신
                                }
                            }
                            else if (minY > ny) {
                                setEdibleFishPosition(ny, nx); // 우선순위 물고기 좌표 갱신
                            }

                        }
                    }

                    // 이동했던 좌표로 이동
                    q.offer(new Point(ny, nx));
                }
            }
        }
    }

    // 가장 우선순위 물고기의 좌표 설정
    static void setEdibleFishPosition(int y, int x) {
        minY = y;
        minX = x;
    }

    // (1) 범위 check : map 안에 있는지 확인
    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    // (2) 이동 check : sharkSize 이하만 가능 (0도 포함하기에 편하게 사용 가능)
    static boolean isAbleToMove(int y, int x) {
        return map[y][x] <= sharkSize;
    }

    // (3) 먹이 check : 먹을 수 있는 물고기 size 인지 확인
    static boolean isEdible(int y, int x) {
        return map[y][x] != 0 && map[y][x] < sharkSize;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
