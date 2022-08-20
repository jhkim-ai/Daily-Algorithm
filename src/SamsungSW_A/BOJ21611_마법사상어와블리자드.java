package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21611_마법사상어와블리자드 {

    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {-1, 0, 1, 0};

    private static int N, M, D, S, score;
    private static Point shark;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        shark = new Point(N / 2, N / 2);

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령어만큼 반복
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            D = Integer.parseInt(st.nextToken()) - 1;
            if (D == 0) {
                D = 3;
            } else if (D == 2) {
                D = 0;
            } else if (D == 3) {
                D = 2;
            }
            S = Integer.parseInt(st.nextToken());

            destroy(); // Step 1. 구슬 파괴
            move();    // Step 2. 구슬 이동

            // 반복 : 폭발 + 이동
            while(findConnectedBeads()) { // Step 3. 구슬 폭발 (폭발이 없다면, 이동할 필요가 없다)
                move();                   // Step 4. 폭발 후 이동
            }

            change();  // Step 5. 구슬 변화
        }

        System.out.println(score);
    }

    // Step 1. 구슬 파괴
    public static void destroy() {
        for (int len = 1; len <= S; len++) {
            int ny = shark.y + dy[D] * len;
            int nx = shark.x + dx[D] * len;
            map[ny][nx] = 0;
        }
    }

    // Step 2. 구슬 이동
    public static void move() {
        Queue<Integer> tmpQ = save(); // 2-1. 빈 칸(0) 을 제외한 구슬을 저장 (순서대로 저장이 된다)
        map = new int[N][N];          // 2-2. map 초기화
        if(!tmpQ.isEmpty()) {
            load(tmpQ);               // 2-3. 구슬을 순서대로 뿌린다.(비어있다면, 진행할 필요 X)
        }
    }

    // 2-1. 빈 칸(0) 을 제외한 구슬을 저장 (순서대로 저장이 된다)
    public static Queue<Integer> save() {
        Queue<Integer> tmpQ = new LinkedList<>();
        int y = shark.y;
        int x = shark.x;
        int len = 1;
        int d = 0;
        int ny;
        int nx;

        // 달팽이 문제 참조
        outer:
        while (true) {
            for (int l = 0; l < len; l++) {
                ny = y + dy[d];
                nx = x + dx[d];
                if (!isIn(ny, nx)) {
                    break outer;
                }

                if (map[ny][nx] == 0) {
                    y = ny;
                    x = nx;
                    continue;
                }

                tmpQ.offer(map[ny][nx]);
                y = ny;
                x = nx;
            }

            d = (d + 1) % 4;

            for (int l = 0; l < len; l++) {
                ny = y + dy[d];
                nx = x + dx[d];

                if (map[ny][nx] == 0) {
                    y = ny;
                    x = nx;
                    continue;
                }

                tmpQ.offer(map[ny][nx]);
                y = ny;
                x = nx;
            }

            d = (d + 1) % 4;
            ++len;
        }

        return tmpQ;
    }

    // 2-3. 구슬을 순서대로 뿌린다.
    public static void load(Queue<Integer> tmpQ) {
        int y = shark.y;
        int x = shark.x;
        int len = 1;
        int d = 0;
        int ny;
        int nx;

        outer:
        while (true) {
            for (int l = 0; l < len; l++) {
                ny = y + dy[d];
                nx = x + dx[d];

                if(!isIn(ny, nx)){
                    break outer;
                }

                map[ny][nx] = tmpQ.poll();
                if (tmpQ.isEmpty()) {
                    break outer;
                }
                y = ny;
                x = nx;
            }

            d = (d + 1) % 4;

            for (int l = 0; l < len; l++) {
                ny = y + dy[d];
                nx = x + dx[d];

                map[ny][nx] = tmpQ.poll();
                if (tmpQ.isEmpty()) {
                    break outer;
                }
                y = ny;
                x = nx;
            }

            d = (d + 1) % 4;
            ++len;
        }
    }

    // Step 3. 구슬 폭발 (폭발이 없다면, 이동할 필요가 없다)
    public static boolean findConnectedBeads() {
        Queue<Point> q = new LinkedList<>();
        boolean result = false;
        int y = shark.y;
        int x = shark.x;
        int len = 1;
        int d = 0;
        int bead = -1;
        int count = 0;
        int ny;
        int nx;

        outer:
        while (true) {
            for (int l = 0; l < len; l++) {
                ny = y + dy[d];
                nx = x + dx[d];
                if (!isIn(ny, nx) || map[ny][nx] == 0) {
                    break outer;
                }

                if (bead != map[ny][nx]) { // 다른 색깔의 구슬을 만날 때(구슬 교체)
                    if (count >= 4) { // 같은 색의 구슬이 4개 이상일 때 => 폭발
                        bomb(q, bead);
                        result = true;
                    }

                    bead = map[ny][nx]; // 구슬 색 교체
                    count = 1;
                    q.clear();
                } else { // 같은 색깔의 구슬을 만날 때
                    count++;
                }
                q.offer(new Point(ny, nx));

                y = ny;
                x = nx;
            }

            d = (d + 1) % 4;

            for (int l = 0; l < len; l++) {
                ny = y + dy[d];
                nx = x + dx[d];
                if (!isIn(ny, nx) || map[ny][nx] == 0) {
                    y = ny;
                    x = nx;
                    continue;
                }

                if (bead != map[ny][nx]) { // 다른 색깔의 구슬을 만날 때(구슬 교체)
                    if (count >= 4) { // 같은 색의 구슬이 4개 이상일 때
                        bomb(q, bead);
                        result = true;
                    }
                    // 같은 색의 구슬이 4개 미만일 때 => 구슬 색 교체
                    bead = map[ny][nx];     // 구슬 색 교체
                    count = 1;              // 구슬 수 초기화
                    q.clear();              // Queue 초기화
                } else { // 같은 색깔의 구슬을 만날 때
                    count++;
                }
                q.offer(new Point(ny, nx));

                y = ny;
                x = nx;
            }

            d = (d + 1) % 4;
            ++len;
        }

        // Queue에 남은 구슬이 4개 이상일 경우
        // 0 0 2
        // 2 0 2
        // 2 2 2
        if(q.size() >= 4){
            bomb(q, bead);
            result = true;
        }

        return result;
    }

    // 폭발과 동시에 Score(정답) 계산
    public static void bomb(Queue<Point> q, int bead) {
        score += bead * q.size();
        while (!q.isEmpty()) {
            Point now = q.poll();
            map[now.y][now.x] = 0;
        }
    }

    // Step 5. 구슬 변화
    public static void change(){
        Queue<Integer> beforeChangeQ = save();
        Queue<Integer> afterChangeQ = new LinkedList<>();
        int bead = -1;
        int count = 0;

        // 모두 다 빈칸(0)일 경우
        if(beforeChangeQ.isEmpty()){
            return;
        }

        // 순서대로 구슬 변화 과정을 진행
        while(!beforeChangeQ.isEmpty()){
            int now = beforeChangeQ.poll();

            if (bead != now) { // 다른 색깔의 구슬을 만날 때 => 변화 시작
                if(count > 0){
                    afterChangeQ.offer(count); // 순서대로 "개수", "구슬 색"을 저장
                    afterChangeQ.offer(bead);
                }
                bead = now;
                count = 1;
            } else { // 같은 색깔의 구슬을 만날 때
                count++;
            }
        }

        // 마지막 잔여 구슬 변화
        afterChangeQ.offer(count);
        afterChangeQ.offer(bead);

        map = new int[N][N]; // map 초기화
        if(!afterChangeQ.isEmpty()) {
            load(afterChangeQ);  // 구슬 뿌리기 (구슬이 없다면 진행할 필요 X)
        }
    }

    // 배열 범위 확인
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    // 위치 저장 Class
    public static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Point{" +
                "y=" + y +
                ", x=" + x +
                '}';
        }
    }

    // debugging 용 출력 함수
    public static void print() {
        System.out.println("====================");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(String.format("%4d ", map[y][x]));
            }
            System.out.println();
        }
        System.out.println("====================");
    }
}
