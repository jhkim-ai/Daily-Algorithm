package StepByStep.day210410;

import java.util.*;
import java.io.*;

public class BOJ18428_감시피하기 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N;
    static char[][] map;
<<<<<<< HEAD
    static List<Point> teacherList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teacherList = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = st.nextToken().charAt(0);
                if (map[y][x] == 'T')
                    teacherList.add(new Point(y, x));
            }
        }

        combination(3, new Point[3], 0, 0);
        System.out.println("NO");
    }

    // 조합
    static void combination(int cnt, Point[] selected, int startY, int startX) {
        if (cnt == 0) {
            for (Point teacher : teacherList) {
                for (int d = 0; d < 4; d++) {
                    int ny = teacher.y + dy[d];
                    int nx = teacher.x + dx[d];
                    while (true) {
                        ny += dy[d];
                        nx += dx[d];
                        if (!isIn(ny, nx)) break;
                        if (isObstacle(ny, nx)) break;
                        if (isStudent(ny, nx)) {
                            for(Point obstacle : selected)
                                map[obstacle.y][obstacle.x] = 'X';
                            return;
                        }
                    }
                }
            }
=======
    static List<Point> teachers;    // 선생님('T')의 좌표 모음
    static List<Point> obstacles;   // 장애물('O')의 좌표 모음

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teachers = new ArrayList<>();
        obstacles = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = st.nextToken().charAt(0);
                // 선생님('T')일 경우, (List)teachers 에 추가
                // 선생님의 위치를 기준으로 학생을 찾기 위해 미리 location을 저장한다.
                if (map[y][x] == 'T')
                    teachers.add(new Point(y, x));
                else if (map[y][x] == 'X') {
                    obstacles.add(new Point(y, x));
                }
            }
        }

        // ------- 알고리즘 시작 ------- //
        // Idea. Combination
        // 조합을 이용하여 O (장애물)을 선택 -> 각 장애물이 설치된 경우를 따져 답 도출

        // (1). 장애물을 놓을 수 있는('X')의 좌표를 Obstacles 에 담는다. (선형 자료구조로 보관)
        // (2). 조합공식을 이용하여 (List)Obstacles 의 index 3가지를 뽑는다.
        // (3). 조합식에 대하여 선생님('T')가 학생('S')를 만나는지 Check

        combination(3, new int[3], 0);
        System.out.println("NO");
    }

    // combination
    static void combination(int cnt, int[] selected, int startIdx) {
        // 기저조건 : 장애물이 3개 채워지면, 학생이 있는지 탐색
        if (cnt == 0) {
            // 선생님의 수만큼 반복
            for(Point teacher : teachers){
                // 한 선생님의 4방 탐색
                for (int d = 0; d < 4; d++) {
                    int ny = teacher.y + dy[d];
                    int nx = teacher.x + dx[d];

                    // 먼저 한 방향에 대하여 직선 탐색
                    while(isIn(ny, nx)){
                        if(isStudent(ny, nx)) return;       // 학생이 존재하면 답:X
                        else if(isObstacle(ny, nx)) break;  // 장애물을 만나면 직선 탐색 종료. 방향 전환

                        ny = ny + dy[d];                    // 한 칸 전진
                        nx = nx + dx[d];
                    }
                }
            }

            // 한 번도 학생을 못만났다면, "YES"와 함께 프로그램 종료
>>>>>>> 4c2f75d7ea1ceb27922a15cea1d357eefde6148d
            System.out.println("YES");
            System.exit(0);
            return;
        }

<<<<<<< HEAD
        if (startX == N) {
            startX = 0;
            startY++;
        }

        for (int y = startY; y < N; y++) {
            for (int x = startX; x < N; x++) {
                if (map[y][x] == 'S' || map[y][x] == 'T')
                    continue;
                selected[selected.length - cnt] = new Point(y, x);
                map[y][x] = 'O';
            }
        }
    }

=======

        for (int i = startIdx; i < obstacles.size(); i++) {
            selected[selected.length - cnt] = i;
            Point o = obstacles.get(i);
            map[o.y][o.x] = 'O';
            combination(cnt-1, selected, i+1);
            map[o.y][o.x] = 'X';
        }
    }

    // check the element that is student('S')
>>>>>>> 4c2f75d7ea1ceb27922a15cea1d357eefde6148d
    static boolean isStudent(int y, int x) {
        if (map[y][x] == 'S')
            return true;
        return false;
    }

<<<<<<< HEAD
=======
    // check the element that is Obstacle('O')
>>>>>>> 4c2f75d7ea1ceb27922a15cea1d357eefde6148d
    static boolean isObstacle(int y, int x) {
        if (map[y][x] == 'O')
            return true;
        return false;
    }

<<<<<<< HEAD
    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static void print(int[][] arr) {
=======
    // print for debugging
    static void print(char[][] arr) {
        System.out.println("================");
>>>>>>> 4c2f75d7ea1ceb27922a15cea1d357eefde6148d
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(arr[y][x]);
            }
            System.out.println();
        }
    }

<<<<<<< HEAD
=======
    // check the valid range
    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    // save for location
>>>>>>> 4c2f75d7ea1ceb27922a15cea1d357eefde6148d
    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
