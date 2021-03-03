package SSAFY_Youtube;

import java.io.*;
import java.util.*;

// 빵집
public class Baekjoon3109 {

    static int Y, X, cnt = 0;
    static char[][] map;
    static boolean[][] visited; // 파이프가 놓여졌거나, 이미 전 단계에서 탐색 중인지 확인하는 배열

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new char[Y][];
        visited = new boolean[Y][X];

        for (int i = 0; i < Y; i++) {
            map[i] = br.readLine().toCharArray();
        }

        makePipe();
        System.out.println(cnt);
    }

    private static void makePipe() {
        // 윗행부터 시도
        for (int i = 0; i < Y; i++) {
            visited[i][0] = true;
            dfs(i,0);
        }
    }

    // 무조건 옆에 열로 가기 때문에, 행에 관련된 delta 값만 만들면 된다
    static int[] dy = {-1, 0, 1};

    // 매개변수로 현재 탐색 위치를 받아야 한다.
    private static boolean dfs(int y, int x) {

        // 기저 조건
        if(x == X-1){
            cnt++;
            return true;
        }

        int ny, nx = x + 1;
        for (int d = 0; d < 3; d++) {
            ny = y + dy[d];
            if (ny < 0 || ny >= Y || map[ny][nx] == 'X' || visited[ny][nx]) continue;

            visited[ny][nx] = true;
            if(dfs(ny,nx)) return true;

            // 흔히 하는 실수. 실패했던 흔적을 되돌리는 것. (그럼 똑같은 과정이 반복됨)
            // visited[ny][nx] = false;
            // 실패했던 흔적 되돌리지 않기 : 뒤의 선택지의 방향은 현재보다 유리하지 않은 상황이고
            // 결국 같은 상황이 되풀이 된다.

        }
        // for 문의 3방향을 다 돌았는데도 놓을 수 없다면, 파이프를 놓을 수 없는 곳이다.
        return false;
    }

    static String input = "5 5\n" +
            ".XX..\n" +
            "..X..\n" +
            ".....\n" +
            "...X.\n" +
            "...X.";
}
