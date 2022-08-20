package SamsungSW_A.삼성_21년_하반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21608_상어초등학교 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N;
    private static boolean[][] info;
    private static int[][] map;
    private static Queue<Point> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        List<Integer> orderList = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        info = new boolean[N * N + 1][N * N + 1];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int studentIdx = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                int likeable = Integer.parseInt(st.nextToken());
                info[studentIdx][likeable] = true;
            }
            orderList.add(studentIdx);
        }

        // 학생(입력) 순서대로 자리 정하기 시작
        for (int student : orderList) {
            if(step1(student)){
                continue;
            }
            if(step2(student)){
                continue;
            }
            step3(student);
        }

        // 점수 구하기
        int score = getScore();
        System.out.println(score);
    }

    // Step 1. 비어있는 칸 중, 좋아하는 학생이 가장 많이 인접한 자리
    public static boolean step1(int student) {
        q = new LinkedList<>();
        int count = -1;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] != 0) { // 빈 자리가 아니라면 pass
                    continue;
                }
                int tmpCount = 0; // 인접한 좋아하는 학생 수
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (!isIn(ny, nx) || map[ny][nx] == 0) { // 범위 밖이거나 빈 자리면 pass
                        continue;
                    }

                    int likeable = map[ny][nx];
                    if(info[student][likeable]){ // 좋아하는 학생이라면 count+1
                        tmpCount++;
                    }
                }

                if (count < tmpCount) { // 가장 큰 count 로 갱신
                    count = tmpCount;
                    q.clear();
                    q.offer(new Point(y, x));
                } else if(count == tmpCount){ // count 가 같다면 Step 2. 로 넘어갈 수 있기에 Queue에 저장.
                    q.offer(new Point(y, x));
                }
            }
        }

        // 자리가 여러개라면 다음 Step 진행
        if(q.size() > 1){
            return false;
        }
        // 자리가 한 자리라면 그 자리 착석
        Point seat = q.poll();
        map[seat.y][seat.x] = student;
        return true;
    }

    // Step 2. 인접한 칸 중 비어있는 칸이 가장 많은 칸으로 자리 정하기
    public static boolean step2(int student){
        Queue<Point> tmpQ = new LinkedList<>();
        int count = -1;

        while(!q.isEmpty()){
            Point now = q.poll();
            int tmpCount = 0;
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if(!isIn(ny, nx) || map[ny][nx] != 0){ // 범위 밖이거나 자리에 누군가 있다면 pass
                    continue;
                }

                // 비어있다면 +1
                tmpCount++;
            }

            if(count < tmpCount){ // 가장 큰 count 자리로 갱신
                tmpQ.clear();
                count = tmpCount;
                tmpQ.offer(new Point(now.y, now.x));
            } else if(count == tmpCount){ // count 가 같다면 Step 3. 로 넘어갈 수 있기에 Queue에 저장.
                tmpQ.offer(new Point(now.y, now.x));
            }
        }

        // 자리가 여러개라면 다음 Step 진행
        if(tmpQ.size() > 1){
            q = tmpQ;
            return false;
        }
        // 자리가 한 자리라면 그 자리 착석
        Point seat = tmpQ.poll();
        map[seat.y][seat.x] = student;
        return true;
    }

    // Step 3. 행의 번호가 가장 작고, 열의 번호가 가장 작은 자리로 선택
    public static void step3(int student){
        Point base = q.poll();

        // 위치 비교
        while(!q.isEmpty()){
            Point next = q.poll();
            if(base.y == next.y) { // 같은 행일 때, 열끼리 비교
                if(base.x > next.x){
                    base = next;
                }
            } else if(base.y > next.y){ // 다른 행일 때
                base = next;
            }
        }

        map[base.y][base.x] = student;
    }

    // 마지막 만족도 구하기
    public static int getScore(){
        int sum = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int student = map[y][x];
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if(!isIn(ny, nx)){
                        continue;
                    }
                    int next = map[ny][nx];
                    if(info[student][next]){
                        count++;
                    }
                }
                if(count > 0){
                    sum += Math.pow(10, count-1);
                }
            }
        }
        return sum;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point{

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
