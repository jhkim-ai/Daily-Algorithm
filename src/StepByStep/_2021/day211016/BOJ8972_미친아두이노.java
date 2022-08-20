package StepByStep._2021.day211016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ8972_미친아두이노 {

    private static final int[] dy = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
    private static final int[] dx = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};

    private static int N, M;
    private static int cnt, cntArduino;
    private static int[] commands;
    private static char[][] map;
    private static Point jongSU;
    private static Queue<Point> arduinoes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        arduinoes = new LinkedList<>();

        for (int y = 0; y < N; y++) {
            String tmp = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = tmp.charAt(x);
                if(map[y][x] == 'I'){
                    jongSU = new Point(0, y, x);
                } else if (map[y][x] == 'R'){
                    ++cntArduino;
                    arduinoes.offer(new Point(cntArduino, y, x));
                }
            }
        }

        String input = br.readLine();
        commands = new int[input.length()];
        for (int i = 0; i < commands.length; i++) {
            commands[i] = input.charAt(i) - '0';
        }

        if(!run()){
            sb.append("kraj ").append(cnt);
        } else {
            for(int y = 0; y < N; ++y){
                for (int x = 0; x < M; x++) {
                    sb.append(map[y][x]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean run(){
        cnt = 0;
        for (int idx = 0; idx < commands.length; idx++) {
            ++cnt;
            int dir = commands[idx];
            if(!moveJongSu(dir)) { // step 1. && step 2.
                return false;
            }
            if(!moveArduino()) { // step 3. && step 4.
                return false;
            }
            checkArduino();

//            StringBuilder sb = new StringBuilder();
//            for(int y = 0; y < N; ++y){
//                for (int x = 0; x < M; x++) {
//                    sb.append(map[y][x]);
//                }
//                sb.append("\n");
//            }
//            System.out.println(sb);
        }

        return true;
    }

    // step 1. && step 2.
    public static boolean moveJongSu(int dir){
        int nowY = jongSU.y;
        int nowX = jongSU.x;
        map[nowY][nowX] = '.';

        // step 1. 종수가 이동할 다음 좌표를 먼저 구한다. (아직 이동 X)
        nowY = nowY + dy[dir];
        nowX = nowX + dx[dir];

        // step 2. Arduino 검사 진행
        if(map[nowY][nowX] == 'R'){
            return false; // 2-1. 이동할 좌표에 Arduino 가 있을 시, 게임 종료
        }

        jongSU.y = nowY;
        jongSU.x = nowX;
        map[nowY][nowX] = 'I'; // 2-2. Arduino 가 없을 시, 이동 완료
        return true;
    }

    // step 3. && step 4.
    public static boolean moveArduino(){
        int size = arduinoes.size();
        while(size-- > 0){
            Point arduino = arduinoes.poll();
            int minDis = Integer.MAX_VALUE;
            int minDir = -1;
            int nowY = arduino.y;
            int nowX = arduino.x;

            // 방향 선택
            for(int d = 1; d <= 9; ++d){
                if(d == 5){
                    continue;
                }
                int newY = nowY + dy[d];
                int newX = nowX + dx[d];
                int dis = Math.abs(jongSU.x - newX) + Math.abs(jongSU.y - newY);
                if(minDis > dis){
                    minDis = dis;
                    minDir = d;
                }
            }

            map[arduino.y][arduino.x] = '.'; // 이전 좌표 빈 곳으로 처리
            arduino.y += dy[minDir]; // Step 3. 새 좌표 이동
            arduino.x += dx[minDir];

            if(map[arduino.y][arduino.x] == 'I'){ // step 4. 종수가 있다면, 게임 종료
                return false;
            }

            arduinoes.offer(arduino);
        }
        return true;
    }

    public static void checkArduino(){
        Map<Integer, Integer> mapArduino = new HashMap<>();
        Set<Integer> setDeleteArduinoes = new HashSet<>();
        Iterator<Point> iterator = arduinoes.iterator();

        while(iterator.hasNext()){
            Point arduino = iterator.next();
            int locIdx = arduino.y * N + arduino.x;

            if(mapArduino.containsKey(locIdx)){
                setDeleteArduinoes.add(arduino.idx);
                setDeleteArduinoes.add(mapArduino.get(locIdx));
                continue;
            }
            mapArduino.put(locIdx, arduino.idx);
        }
        int size = arduinoes.size();
        while(size-- > 0){
            Point arduino = arduinoes.poll();
            if(!setDeleteArduinoes.contains(arduino.idx)){
                map[arduino.y][arduino.x] = 'R';
                arduinoes.offer(arduino);
            }
        }
    }

    public static class Point {
        int idx;
        int y;
        int x;

        public Point(int idx, int y, int x) {
            this.idx = idx;
            this.y = y;
            this.x = x;
        }
    }
}
