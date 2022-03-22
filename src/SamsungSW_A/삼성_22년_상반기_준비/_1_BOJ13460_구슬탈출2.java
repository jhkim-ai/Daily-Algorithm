package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class _1_BOJ13460_구슬탈출2 {

    public static final int[] dy = {-1, 0, 1, 0};
    public static final int[] dx = {0, 1, 0, -1};

    private static int N, M, ans;
    private static int exitY, exitX;
    private static int startRedY, startRedX;
    private static int startBlueY, startBlueX;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        // map 초기화
        for (int y = 0; y < N; ++y) {
            map[y] = br.readLine().toCharArray();
            for (int x = 0; x < M; ++x) {
                if (map[y][x] == 'O') {
                    exitY = y;
                    exitX = x;
                    map[y][x] = '.';
                }

                if (map[y][x] == 'R') {
                    startRedY = y;
                    startRedX = x;
                    map[y][x] = '.';
                }

                if (map[y][x] == 'B') {
                    startBlueY = y;
                    startBlueX = x;
                    map[y][x] = '.';
                }
            }
        }

        Point start = new Point(startRedY, startRedX, startBlueY, startBlueX);

        ans = bfs(start); // bfs 시작

        System.out.println(ans); // 정답 출력
    }

    public static int bfs(Point start){
        int ans = 0;
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        while(!q.isEmpty()){
            ans++;
            if(ans > 10) return -1; // 10번 초과 시 끝

            int size = q.size();
            for(int s = 0; s < size; ++s){
                Point now = q.poll();

                for(int d = 0; d < 4; ++d){
                    int order = setOrder(d, now);     // 공의 움직일 순서 구하기
                    Point newP = move(d, order, now); // 공 움직이기

                    if(!newP.isPossible) continue;    // 파란 공이 'O'에 들어갔다면 불가
                    if(isIn(newP)){                   // 빨간 공만 'O'에 들어갔다면 성공
                        return ans;
                    }
                    q.offer(newP);                    // q에 다음 순서 삽입
                }
            }
        }

        return -1;
    }

    // 공의 움직일 순서 구하기
    // order == 0 : 빨강 먼저, order == 1 : 파랑 먼저
    public static int setOrder(int d, Point p) {
        int order = 0;

        if ((d == 0 || d == 2) && p.redX == p.blueX) {
            if (d == 0) { // 상
                if (p.redY > p.blueY) {
                    order = 1;
                } else if (p.redY < p.blueY) {
                    order = 0;
                }
            } else if (d == 2) {
                if (p.redY > p.blueY) {
                    order = 0;
                } else if (p.redY < p.blueY) {
                    order = 1;
                }
            }
        } else if ((d == 1 || d == 3) && p.redY == p.blueY) {
            if (d == 3) { // 좌
                if (p.redX > p.blueX) {
                    order = 1;
                } else if (p.redX < p.blueX) {
                    order = 0;
                }
            } else if (d == 1) { // 우
                if (p.redX > p.blueX) {
                    order = 0;
                } else if (p.redX < p.blueX) {
                    order = 1;
                }
            }
        }

        return order;
    }

    // 공 움직이기
    public static Point move(int d, int order, Point p) {
        Point newP = new Point(p.redY, p.redX, p.blueY, p.blueX);
        Queue<Character> q = new LinkedList<>(); // Queue를 이용하여 순서대로 움직이기
        if (order == 0) { // order == 0 : 빨강 먼저
            q.offer('R');
            q.offer('B');
        } else {          // order == 1 : 파랑 먼저
            q.offer('B');
            q.offer('R');
        }

        while (!q.isEmpty()) {
            char c = q.poll();
            if (c == 'R') { // 빨간 공 움직이기
                int ny = newP.redY;
                int nx = newP.redX;
                while (true) {
                    ny += dy[d];
                    nx += dx[d];

                    // 벽인가? or 파란공('B')을 만났나? -> 탈출인가? 순으로 check
                    // 파란공과의 checking 차이는 '탈출'과 '다른 공을 만났을 때'의 check 순서만 다르다.

                    // check 과정을 파란공과 똑같이 해도 되지만
                    // 파란공이 먼저 '탈출'하고 빨간 공이 탈출구 옆에 붙은 상태가 문제라 생각될 텐데
                    // 78번 Line의 isIn() 함수에서 걸러지기 때문에 상관없다.
                    if (map[ny][nx] == '#' || (ny == newP.blueY && nx == newP.blueX)) {
                        break;
                    }

                    newP.redY = ny;
                    newP.redX = nx;

                    if(ny == exitY && nx == exitX){
                        break;
                    }
                }
            } else if (c == 'B') { // 파란 공 움직이기
                int ny = newP.blueY;
                int nx = newP.blueX;
                while (true) {
                    ny += dy[d];
                    nx += dx[d];

                    // 벽인가? -> 탈출('O')인가? -> 빨간공('R')을 만났나? 순으로 check
                    if (map[ny][nx] == '#') { // 벽인가?
                        break;
                    }

                    if(ny == exitY && nx == exitX){ // 탈출구인가?
                        newP.blueY = ny;
                        newP.blueX = nx;
                        newP.isPossible = false;
                        break;
                    }

                    if(ny == newP.redY && nx == newP.redX){ // 빨간공인가?
                        break;
                    }

                    newP.blueY = ny;
                    newP.blueX = nx;
                }
            }
        }

        return newP;
    }

    public static boolean isIn(Point p){
        return p.redY == exitY && p.redX == exitX && (p.blueY != exitY || p.blueX != exitX);
    }

    public static class Point {
        int redY;
        int redX;
        int blueY;
        int blueX;
        boolean isPossible = true;

        public Point(int redY, int redX, int blueY, int blueX){
            this.redY = redY;
            this.redX = redX;
            this.blueY = blueY;
            this.blueX = blueX;
        }
    }
}
