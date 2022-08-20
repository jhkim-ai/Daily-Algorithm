package SamsungSW_A.삼성_21년_하반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3190_뱀 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, K, L, d;

    private static int headY, headX;
    private static int tailY, tailX;
    private static int headVal;

    private static char[] times;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine()); // map 크기
        K = Integer.parseInt(br.readLine()); // 사과 개수
        times = new char[10001];
        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            Arrays.fill(map[y], -1);
        }

        // 사과 위치 등록
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1 ;
            map[y][x] = -2;
        }

        // 방향 정보 등록
        L = Integer.parseInt(br.readLine());
        while (L-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            times[time] = dir;
        }

        d = 1; // 오른쪽부터 시작
        headY = 0;
        headX = 0;
        headVal = 0;

        tailY = 0;
        tailX = 0;
        map[headY][headX] = 0;

        for (int i = 1; i <= 10000; i++) {
            if(!move()){
                System.out.println(i);
                break;
            }
            if(times[i] == 'L'){
                d = (d + 3) % 4;
            } else if (times[i] == 'D'){
                d = (d + 1) % 4;
            }
        }
    }

    public static boolean move(){
        int ny = headY + dy[d];
        int nx = headX + dx[d];

        // 벽 or 자기 자신
        if(!isIn(ny, nx) || map[ny][nx] > -1 ){
            return false;
        }

        headVal++;
        headY = ny;
        headX = nx;

        // 사과가 있을 때
        if(map[ny][nx] == -2){
            map[ny][nx] = headVal; // 머리 전진
            return true;
        }

        // 머리 전진
        map[ny][nx] = headVal;

        // 꼬리 전진
       for (int dir = 0; dir < 4; dir++) {
            int ty = tailY + dy[dir];
            int tx = tailX + dx[dir];
            if(!isIn(ty, tx) || map[ty][tx] == -1){
                continue;
            }
            if(map[ty][tx] == map[tailY][tailX] + 1){
                map[tailY][tailX] = -1;
                tailY = ty;
                tailX = tx;
                break;
            }
        }
        return true;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}
