package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ20056_마법사상어와파이어볼 {

    public static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static int N, M, K;
    public static List<FireBall>[][][] fireBall;
    public static Queue<FireBall> moveQ;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());    // Map 의 크기
        M = Integer.parseInt(st.nextToken());    // 파이어볼의 개수
        K = Integer.parseInt(st.nextToken());    // 명령어 개수

        fireBall = new ArrayList[N][N][1];       // FireBall 을 3차원 arrayList 에 저장
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                fireBall[y][x][0] = new ArrayList<>();
            }
        }

        // FireBall 정보 입력
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            fireBall[y][x][0].add(new FireBall(y, x, m, s, d));
        }

        // ======== 알고리즘 시작 ======== //
        // "주의"  1. Queue 를 for 문으로 탐색할 때 주의하자.
        //        2. 첫 풀이에서 모든 것을 Queue 하나로 관리하였더니 시간 초과가 났다.

        // K 번의 명령 실행
        while (K-- > 0) {
            move();
            sumFireBall();
        }

        // 남아있는 FireBall 의 질량의 합
        System.out.println(getTotalMass());
    }

    // 1. 파이어볼 이동
    public static void move() {
        moveQ = new LinkedList<>();

        // 1-1. 현재 위치한 모든 FireBall 을 Queue 에 저장
        // [Queue 에 저장하는 이유] => 이미 움직인 FireBall 을 또 움직이지 않기 위해서
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int size = fireBall[y][x][0].size();
                if (size == 0) continue;             // FireBall 이 없다면 pass

                while (size-- > 0) {                 // 위치한 FireBall 을 Queue 에 저장
                    moveQ.offer(fireBall[y][x][0].get(size));
                }
                fireBall[y][x][0].clear();
            }
        }

        // 1-2. FireBall 을 조건에 맞게 이동
        while (!moveQ.isEmpty()) {
            FireBall fb = moveQ.poll();

            // d 방향으로 s 칸 움직이기
            fb.y = (fb.y + (dy[fb.d] * fb.s)) % N; // 1번 행과 N번 행은 연결
            fb.x = (fb.x + (dx[fb.d] * fb.s)) % N; // 1번 열과 N번 열은 연결

            if (fb.y < 0) fb.y += N;
            if (fb.x < 0) fb.x += N;

            fireBall[fb.y][fb.x][0].add(fb);
        }
    }

    // 2. 파이어볼 합치기
    public static void sumFireBall() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (fireBall[y][x][0].size() <= 1) continue; // 같은 위치에 존재하는 FireBall 이 1개 이하면 pass

                // 2-1. 같은 칸에 위치한 FireBall 하나로 합치기
                int count = fireBall[y][x][0].size();        // 같은 위치에 있는 FireBall 의 개수
                int sumMass = 0;                             // FireBalls 의 질량 합
                int sumSpeed = 0;                            // FireBalls 의 속력 합
                List<Integer> getDir = new ArrayList<>();    // FireBalls 의 방향들을 저장
                for (FireBall fb : fireBall[y][x][0]) {
                    sumMass += fb.m;
                    sumSpeed += fb.s;
                    getDir.add(fb.d);
                }

                // 2-2. 새로운 FireBall 4개를 저장하기 위해 "합쳐진 FireBall 제거"
                fireBall[y][x][0].clear();        // ArrayList 초기화

                int setMass = sumMass / 5;        // 질량 설정
                if (setMass == 0) continue;       // 규칙2-4. 질량이 0이면 소멸한다.
                int setSpeed = sumSpeed / count;  // 속도 설정

                // 2-3. 방향이 모두 (홀수이거나 짝수) 혹은 (그렇지 않은 것)을 판별
                boolean checkDir = true;               // 방향이 모두 같다면 true, 아니면 false
                boolean flag = getDir.get(0) % 2 == 1; // 홀수면 true, 짝수면 false
                for (int dir : getDir) {
                    boolean tmp = dir % 2 == 1;
                    if (flag != tmp) {
                        checkDir = false;
                        break;
                    }
                }

                // checkDir 에 따른 방향 설정
                int[] setDir;
                if (checkDir) setDir = new int[]{0, 2, 4, 6};
                else setDir = new int[]{1, 3, 5, 7};

                // 4개의 새로운 FireBall 생성
                for (int i = 0; i < 4; ++i) {
                    fireBall[y][x][0].add(new FireBall(y, x, setMass, setSpeed, setDir[i]));
                }
            }
        }
    }

    // 남아있는 FireBall 의 질량 합 구하기
    public static int getTotalMass() {
        int sum = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                for (FireBall fb : fireBall[y][x][0])
                    sum += fb.m;
            }
        }
        return sum;
    }

    static class FireBall {
        int y;
        int x;
        int m;
        int s;
        int d;

        public FireBall(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
