package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1707_프로세서연결하기 {

    public static final int[] dy = {-1, 1, 0, 0};   // 상, 하, 좌, 우
    public static final int[] dx = {0, 0, -1, 1};

    public static final int CORE = 1;
    public static final int WIRE = 3;

    public static int T, N, ans, maxCell;
    public static int[][] map;
    public static List<Point> cores;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());        // 테스트케이스 수
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());    // map 가로, 세로 길이
            map = new int[N][N];                    // 프로세서 상태
            cores = new ArrayList<>();              // core 의 위치 정보 모음
            ans = Integer.MAX_VALUE;
            maxCell = Integer.MIN_VALUE;

            // map 입력
            for (int y = 0; y < N; ++y) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; ++x) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    if (map[y][x] == CORE && isEdge(y, x)) continue;    // 가장 자리에 있는 core (제외)
                    if (map[y][x] == CORE) cores.add(new Point(y, x));  // 가장 자리를 제외한 core 정보 저장
                }
            }

            dfs(0, 0);
            sb.append(String.format("#%d %d\n", t, ans));
        }

        System.out.println(sb);
    }

    public static void dfs(int cnt, int cells) {
        if (cnt == cores.size()) {
            int count = getLength();
            if(maxCell == cells) ans = Math.min(ans, count); // wire 개수 갱신
            else if(maxCell < cells) { // cell 최대 개수가 갱신이
                maxCell = cells;
                ans = count;
            }
            return;
        }

        for (int dir = 0; dir < 5; ++dir) {           // wire 를 그릴 수 있는 4방향 탐색
            if(dir == 4) dfs(cnt + 1, cells);
            else {
                List<Point> list = new ArrayList<>();     // 새로 그린 wire 위치 저장
                int flag = drawMap(cores.get(cnt), dir, list); // wire 그리기

                if (flag == 0) {
                    deleteMap(list);                      // wire 를 연결할 수 없다면, 그렸던 것을 다시 지우자
                    continue;
                }
                dfs(cnt + 1, cells+flag);       // 다른 core 로 넘어감
                deleteMap(list);                          // 그렸던 것을 지움
            }
        }
    }

    // wire 그리기
    public static int drawMap(Point core, int dir, List<Point> list) {
        // if(isEdge(core)) return 1; // 가장 자리에 위치하면, 연결된 상태임으로 pass

        // 새 좌표 (한 칸 이동)
        int ny = core.y + dy[dir];
        int nx = core.x + dx[dir];

        // 새 좌표가 유효하다면 계속 진행
        while(isIn(ny, nx)){
            if(map[ny][nx] == CORE) return 0; // 코어가 위치한다면 pass
            if(map[ny][nx] == WIRE) return 0; // 전선이 위치한다면 pass

            // 전선이 설치 가능하다면,
            map[ny][nx] = WIRE;             // 전선 설치
            list.add(new Point(ny, nx));    // 지우기 위해 그렸던 좌표를 저장
            ny = ny + dy[dir];              // 좌표 갱신
            nx = nx + dx[dir];
        }

        // 가장자리(끝)까지 갔다는 의미
        return 1;
    }

    // 다음에 map 을 재활용하기 위해서 그렸던 것을 지운다.
    public static void deleteMap(List<Point> list){
        for(Point p : list)
            map[p.y][p.x] = 0;
    }

    // 전선(wire)의 개수 세기
    public static int getLength(){
        int count = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(map[y][x] == WIRE) ++count;
            }
        }
        return count;
    }

    // 코어가 가장자리에 위치한가?
    public static boolean isEdge(int y, int x) {
        return y == 0 || y == N - 1 || x == 0 || x == N - 1;
    }

    // 배열 범위안에 있는가?
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
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
