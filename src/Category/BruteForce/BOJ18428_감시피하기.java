package Category.BruteForce;

import java.util.*;
import java.io.*;

public class BOJ18428_감시피하기 {

    public static final int[] dy = {-1, 1, 0, 0};   // 상, 하, 좌, 우
    public static final int[] dx = {0, 0, -1, 1};

    public static int N;                // Map 크기
    public static char[][] map;         // 초기 map

    public static List<Point> teacher;  // 선생님 위치 정보

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        teacher = new ArrayList<>();

        // ========== 데이터 입력 ========== //
        for (int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; ++x) {
                map[y][x] = st.nextToken().charAt(0);
                // 선생님 위치 정보 저장
                if (map[y][x] == 'T') teacher.add(new Point(y, x));
            }
        }

        // ========== 알고리즘 시작 ========== //
        // Idea. 완전 탐색(조합) : 전체 X 중, 3개를 뽑는다.
        combination(3, new int[3], 0);
        System.out.println("NO");
    }

    // 장애물 조합
    public static void combination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {

            // 조합된 장애물 위치를 map 에 갱신
            for (int idx = 0; idx < selected.length; ++idx) {
                // 장애물이 놓일 위치가 빈 곳('X')가 아니라면, 원래 상태로 돌린 후 다음 조합으로 넘어감.
                if (map[selected[idx] / N][selected[idx] % N] != 'X') {
                    init(selected);
                    return;
                }
                // 장애물 등록
                map[selected[idx] / N][selected[idx] % N] = 'O';
            }

            // 감시를 피할 수 있다면, 바로 종료
            if(isPossible()){
                System.out.println("YES");
                System.exit(0);
            }

            init(selected); // 감시를 피하지 못했다면(NO), 다음 탐색을 위해 Map 초기화
            return;
        }

        for (int i = startIdx; i < N * N; ++i) {
            selected[selected.length - cnt] = i;
            combination(cnt - 1, selected, i + 1);
        }
    }

    // 학생이 감시를 받는지 확인하는 함수
    public static boolean isPossible() {

        // 선생님 기준으로 학생들을 탐색
        for (Point nowTeacher : teacher) {

            for (int d = 0; d < 4; ++d) {             // 4방향 탐색
                int y = nowTeacher.y;                 // 현 좌표 저장
                int x = nowTeacher.x;
                while (true) {                        // 한 방향을 배열 범위 끝까지 탐색
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    // 배열 index 범위 밖 or 장애물 만날 시 방향 전환
                    if(!isIn(ny, nx) || map[ny][nx] == 'O') break;
                    // 학생을 만날 시, 탐색 끝
                    if(map[ny][nx] == 'S') return false;

                    // 좌표 갱신
                    y = ny;
                    x = nx;
                }
            }
        }
        return true;
    }

    // Map 최기화
    public static void init(int[] selected){
        for (int idx = 0; idx < selected.length; ++idx) {
            if(map[selected[idx] / N][selected[idx] % N] == 'O'){
                map[selected[idx] / N][selected[idx] % N] = 'X';
            }
        }
    }

    // 배열 index 범위 검사
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    // debugging 용
    public static void print() {
        System.out.println("==============");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
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
