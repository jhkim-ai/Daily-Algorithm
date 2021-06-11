package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ20056_마법사상어와파이어볼 {

    public static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    public static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static int N, M, K;
    public static int[] countFireBall;
    public static Queue<FireBall> fireBalls;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());    // Map 의 크기
        M = Integer.parseInt(st.nextToken());    // 파이어볼의 개수
        K = Integer.parseInt(st.nextToken());    // 명령어 개수

        countFireBall = new int[N * N];          // 해당 위치의 FireBall 개수
        fireBalls = new LinkedList<>();          // FireBall 을 Queue 에 저장 (삭제, 삽입 빈번)

        // FireBall 정보 입력
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int index = y * N + x;

            countFireBall[index]++;
            fireBalls.offer(new FireBall(index, y, x, m, s, d));
        }

        while(K-- > 0){
            move();
            sumFireBall();
        }

        System.out.println(getTotalMass());
    }

    // 파이어볼 이동
    public static void move() {
        for (int idx = 0; idx < fireBalls.size(); ++idx) {
            FireBall now = fireBalls.poll();
            countFireBall[now.index]--; // 움직일 것이기 때문에, 기존 위치에서의 FireBall 개수 - 1

            // d 방향으로 s 칸 움직이기
            now.y = ( now.y + (dy[now.d] * now.s) ) % N; // 1번 행과 N번 행은 연결
            now.x = ( now.x + (dx[now.d] * now.s) ) % N; // 1번 열과 N번 열은 연결

            if(now.y < 0) now.y += N;
            if(now.x < 0) now.x += N;

            // index 변경
            now.index = now.y * N + now.x;
            countFireBall[now.index]++;

            fireBalls.offer(new FireBall(now.index, now.y, now.x, now.m, now.s, now.d));
        }
    }

    // 파이어볼 합치기
    public static void sumFireBall(){
        int ny, nx;
        List<Integer> listIdx = new ArrayList<>(); // 2개 이상이 위치한 FireBall 의 index 저장

        // countFireBall 에서 2개 이상인 장소를 listIdx 에 저장
        for(int i = 0; i < countFireBall.length; ++i){
            if(countFireBall[i] == 0 || countFireBall[i] == 1) continue;
            listIdx.add(i);
        }

        // 2개 이상 위치한 FireBall 이 없다면
        if(listIdx.size() == 0) return;

        // listIdx 에서 index 가 같은 것들을 뽑는다.
        for (int index : listIdx) {

            ny = index / N;
            nx = index % N;

            int count = 0;      // 합쳐진 FireBall 의 개수
            int sumMass = 0;    // FireBalls 의 질량 합
            int sumSpeed = 0;   // FireBalls 의 속력 합
            List<Integer> getDir = new ArrayList<>();  // FireBalls 의 방향들을 저장

            // fireBalls 를 돌아보며 같은 index 에 위치한 FireBall 을 찾는다.
            for(int j = 0; j <= fireBalls.size(); ++j){
                if(fireBalls.isEmpty()) break;
                FireBall now = fireBalls.poll();
                // index 가 다르다면 Queue 의 맨 뒤로 다시 삽입
                if(now.index != index) {
                    fireBalls.offer(now);
                    continue;
                }

                // index 가 같다면 질량, 속도를 합하고, 방향을 저장
                sumMass += now.m;
                sumSpeed += now.s;
                getDir.add(now.d);
                ++count;
            }

            int setMass = sumMass / 5;        // 질량 설정
            if(setMass == 0) continue;        // 규칙2-4. 질량이 0이면 소멸한다.
            int setSpeed = sumSpeed / count;  // 속도 설정

            // 방향이 모두 같은지 확인
            boolean checkDir = true;               // 방향이 모두 같다면 true, 아니면 false
            boolean flag = getDir.get(0) % 2 == 1; // 홀수면 true, 짝수면 false
            for(int dir : getDir){
                boolean tmp = dir % 2 == 1;
                if(flag != tmp){
                    checkDir = false;
                    break;
                }
            }

            // checkDir 에 따른 방향 설정
            int[] setDir;
            if(checkDir) setDir = new int[]{0, 2, 4, 6};
            else setDir = new int[]{1, 3, 5, 7};

            for(int i = 0; i < 4; ++i){
                fireBalls.offer(new FireBall(index, ny, nx, setMass, setSpeed, setDir[i]));
            }

            countFireBall[index] = 4;
        }
    }

    public static int getTotalMass(){
        int sum = 0;
        for(FireBall fb : fireBalls){
            sum += fb.m;
        }

        return sum;
    }

    static class FireBall {
        int index;
        int y;
        int x;
        int m;
        int s;
        int d;

        public FireBall(int index, int y, int x, int m, int s, int d) {
            this.index = index;
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }

        @Override
        public String toString() {
            return "FireBall{" +
                    "index=" + index +
                    ", y=" + y +
                    ", x=" + x +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }
    }
}
