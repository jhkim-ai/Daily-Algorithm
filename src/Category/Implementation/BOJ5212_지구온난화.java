package Category.Implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ5212_지구온난화 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int R, C;
    private static int startY, startX, endY, endX;
    private static char[][] map;
    private static List<Point> list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        startY = Integer.MAX_VALUE;
        startX = Integer.MAX_VALUE;
        endY = Integer.MIN_VALUE;
        endX = Integer.MIN_VALUE;
        map = new char[R][C];
        for (int y = 0; y < R; y++) {
            map[y] = br.readLine().toCharArray();
        }
        list = new ArrayList<>();

        run();
        print(sb);
        System.out.println(sb);
    }

    public static void run(){
        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if(map[y][x] != 'X') continue;
                int count = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if(!isIn(ny, nx)){
                        ++count;
                        continue;
                    }
                    if(map[ny][nx] == 'X') continue;
                    count++;
                }
                if(count >= 3) {
                    list.add(new Point(y, x));
                    continue;
                }

                startY = Math.min(startY, y);
                startX = Math.min(startX, x);
                endY = Math.max(endY, y);
                endX = Math.max(endX, x);
            }
        }

        // 3면이상이 바다면, 섬은 바다에 잠긴다.
        for(Point p : list){
            map[p.y][p.x] = '.';
        }
    }

    // 출력
    public static void print(StringBuilder sb){
        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                sb.append(map[y][x]);
            }
            sb.append("\n");
        }
    }

    // 배열 유효 범위 검사 (범위를 벗어나면 바다)
    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < R && x < C;
    }

    static class Point{
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
