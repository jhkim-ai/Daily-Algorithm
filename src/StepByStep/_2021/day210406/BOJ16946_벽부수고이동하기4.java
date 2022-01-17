package StepByStep.day210406;

import java.util.*;
import java.io.*;

public class BOJ16946_벽부수고이동하기4 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N, M;
    static int[][] map;
    static int[][] ans;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];


        // ---- 알고리즘 시작 ---- //
        // Idea. 2가지
        // (1). Union-Find 와 비슷한 개념 ("아파트 단지 붙이기" 문제처럼)
        // 1-1. 이웃한 0을 "아파트 단지 번호 붙이기"처럼 집합화 시킨다.
        // 1-2. 집합들을 List 에 저장하여 이동횟수를 저장한다.
        // 1-3. 그리고 map 을 돌며 -1(벽)의 사방탐색을 진행하여 집합 번호를 Set에 넣는다.
        // 1-4. Set 에 들어있는 집합 번호를 이용하여 List 에 접근하고 이동 가능한 횟수(답)를 더한다.

        // (2). 역발상 : -1(벽)에서 0을 찾는 것이 아닌 0에서 -1(벽)을 찾는다. -> 두 개의 Queue 이용
        // 2-1. 인접한 0의 개수를 세고, 0 주변의 -1(벽)을 탐색한다.
        // 2-2. 탐색된 -1(벽)에 2-1에서 찾은 인접한 0의 개수를 더한다.

        // solution1();
        solution2();
    }

    // ****************** 첫 번째 방법 ****************** //

    static int setNum = 1;          // 집합 번호
    static List<Integer> setList;   // 집합을 저장할 장소

    // (1). Union-Find 와 비슷한 방법 (집합으로 푸는 법)
    static void solution1() throws Exception {

        for (int y = 0; y < N; y++) {
            String str = br.readLine();
            for (int x = 0; x < M; x++) {
                int num = str.charAt(x) - '0';
                if (num == 1)
                    map[y][x] = -1;  // 벽을 -1로 표시
                else
                    map[y][x] = 0;   // 길
            }
        }

        setList = new ArrayList<>();
        setList.add(0);             // 집합 번호를 1부터 찾기 위해 dummy 값 추가

        // (1)-1. 이웃한 0을 "아파트 단지 번호 붙이기"처럼 집합화 시킨다.
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0)
                    bfs_1(y, x);      // 주변 bfs를 이용한 "0" 탐색
            }
        }

        // (1)-3. map 을 돌며 -1(벽)의 사방탐색을 통해 인접한 "집합 번호"를 Set에 넣는다.
        Set<Integer> s = new HashSet<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                // (1)-4. Set 에 들어있는 집합 번호를 이용하여
                // List 에 접근하고 이동 가능한 횟수(답)를 더한다.
                if (map[y][x] == -1) {
                    s.clear();
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if (!isIn(ny, nx) || map[ny][nx] == -1) continue;
                        s.add(map[ny][nx]);
                    }
                    int sum = 1;
                    for (int num : s) {
                        sum += setList.get(num);
                    }
                    ans[y][x] = sum;
                }
            }
        }

        // 출력
        print(ans);
    }

    static void bfs_1(int y, int x) {
        int cnt = 1;                // 이웃한 0의 개수
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        map[y][x] = setNum;

        // bfs 를 이용한 "0" 탐색
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (!isIn(ny, nx) || map[ny][nx] != 0) continue;
                map[ny][nx] = setNum;
                q.offer(new Point(ny, nx));
                cnt++;
            }
        }
        setList.add(cnt);   // (1)-2. n번째 집합의 원소의 개수를 List에 n번째 index로 저장
        setNum++;           // 집합 번호 증가
    }


    // ****************** 두 번째 방법 ****************** //

    static boolean[][] visited;

    // (2). 역발상 : 이동 가능한 0 개수를 세고, "0" 주변(4방)에 위치한 벽(1)에 "0의 개수"를 더한다.
    static void solution2() throws Exception {
        visited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            String str = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = str.charAt(x) - '0';
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0 && !visited[y][x]) {
                    bfs_2(y, x);
                }
            }
        }

        print(map);
    }

    static void bfs_2(int y, int x) {
        int countZero = 1;                        // 0의 개수
        Queue<Point> q_0 = new LinkedList<>();    // 0(길) 탐색용
        Queue<Point> q_1 = new LinkedList<>();    // 1(벽) 탐색용
        visited[y][x] = true;
        q_0.offer(new Point(y, x));

        while (!q_0.isEmpty()) {
            Point now = q_0.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (!isIn(ny, nx) || visited[ny][nx]) continue;

                // (2)-1. 인접한 0의 개수를 세고, 0 주변의 -1(벽)을 탐색한다.
                if (map[ny][nx] == 0) {               // 새 좌표가 0일 경우, 0의 개수 증가
                    q_0.offer(new Point(ny, nx));
                    countZero++;
                }
                // (2)-2. 탐색된 -1(벽)에 2-1에서 찾은 인접한 0의 개수를 더한다.
                else{                                  // 새 좌표가 1일 경우, 0에 이웃한 벽이므로
                    q_1.offer(new Point(ny, nx));      // q_1에 추가한다.
                }

                visited[ny][nx] = true;                // (중복 방지)
            }
        }

        // 0의 개수 탐색이 끝난 후, 이웃한 1(벽)에 이동 가능한 횟수를 더한다.
        while(!q_1.isEmpty()) {
            Point now = q_1.poll();
            map[now.y][now.x] += countZero;
            visited[now.y][now.x] = false;
            // map[wall.y][wall.x] %= 10;
        }
    }


    // 범위 Check
    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                sb.append(arr[y][x] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    // 좌표 등록
    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        // Set에 넣기 위한 비교
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return y == point.y && x == point.x;
        }

        // Set에 넣기 위한 비교
        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}