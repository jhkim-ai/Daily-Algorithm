package SamsungSW_A.삼성_21년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ20056_마법사상어와파이어볼 {

    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    private static int N, M, K;
    private static Queue<Point>[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Queue[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                map[y][x] = new LinkedList<>();
            }
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].offer(new Point(r, c, m, s, d));
        }

        // 알고리즘 시작
        // 명령의 개수 K만큼 실행
        while(K-- > 0){
            move(); // Step 1. d 방향으로 s 만큼 이동하기
            findFireballsOverOne();
        }

        // 남은 파이어볼의 질량 총합 구하기
        int ans = getWeightOfAllFireballs();
        System.out.println(ans);
    }

    // Step 1. d방향으로 s만큼 이동하기 (아마 1개씩만 존재할 것이다)
    public static void move(){
        Queue<Point> tmp = new LinkedList<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(!map[y][x].isEmpty()){
                    while(!map[y][x].isEmpty()){
                        tmp.offer(map[y][x].poll());
                    }
                }
            }
        }

        while(!tmp.isEmpty()){
            Point fireball = tmp.poll();
            int ny = (fireball.y + dy[fireball.d] * fireball.s) % N;
            int nx = (fireball.x + dx[fireball.d] * fireball.s) % N;

            if(ny < 0) {
                ny += N;
            }
            if(nx < 0){
                nx += N;
            }
            map[ny][nx].offer(new Point(ny, nx, fireball.m, fireball.s, fireball.d));
        }
    }

    // Step 2. 2개 이상의 Fireball 찾기
    public static void findFireballsOverOne(){
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(map[y][x].size() > 1){
                    makeNewFireball(y, x, map[y][x]); // Step 3. 2개 이상이 위치 한 곳에서 분할 진행
                }
            }
        }
    }

    // Step 3. 2개 이상이 위치 한 곳에서 분할 진행
    public static void makeNewFireball(int y, int x, Queue<Point> fireBalls){
        int sumM = 0; // 합쳐진 파이어볼 질량 합
        int sumS = 0; // 합쳐진 파이어볼 속력 합
        int total = fireBalls.size(); // 합쳐진 파이어볼의 수
        List<Integer> dirList = new ArrayList<>(); // 합쳐진 파이어볼들의 방향 저장

        // Step 2-1. 같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
        while(!fireBalls.isEmpty()){
            Point fireBall = fireBalls.poll();
            sumM += fireBall.m;
            sumS += fireBall.s;
            dirList.add(fireBall.d);
        }

        // Step 2-4. 질량이 0이면 소멸된다.
        int divideM = sumM / 5;
        if(divideM == 0){
            return;
        }

        // Step 2-3. 합쳐진 파이어볼의 방향이 모두 (홀수 or 짝수)인지 확인
        int divideS = sumS / total;
        int flag = -1; // 홀,짝의 기준 값
        boolean isAllEvenOrOdd = true;

        for(int idx = 0; idx < dirList.size(); ++idx){
            int dir = dirList.get(idx);
            if(idx == 0){ // 첫 방향에서 홀수 인지 짝수인지 기준값을 구한다.
                flag = dir % 2;
                continue;
            }

            if(dir % 2 != flag){ // 하나라도 첫 방향과 다르다면 break;
                isAllEvenOrOdd = false;
                break;
            }
        }

        int startDir = 0;
        if(!isAllEvenOrOdd){
          startDir = 1;
        }

        // Step 2-2. 파이어볼은 4개의 파이어볼로 나누어진다.
        for (int i = 0; i < 4; i++) {
            map[y][x].offer(new Point(y, x, divideM, divideS, startDir));
            startDir += 2;
        }
    }

    public static int getWeightOfAllFireballs(){
        int sum = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(!map[y][x].isEmpty()){
                    while(!map[y][x].isEmpty()){
                        Point fireBall = map[y][x].poll();
                        sum += fireBall.m;
                    }
                }
            }
        }
        return sum;
    }

    public static class Point {

        int y;
        int x;
        int m; // 질량
        int s; // 속력
        int d; // 방향

        public Point(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
