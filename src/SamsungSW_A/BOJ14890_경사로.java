package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890_경사로 {

    private static int N, L, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        // 5가지의 경우의 수 ( 같은 높이, 위&위, 위&아래, 아래&위, 아래&아래 )
        // 아래&위 의 경우를 주의.
        for (int i = 0; i < N; ++i) {
            if (rowCheck(i)) { // Row 체크
                ++ans;
            }
            if (colCheck(i)) { // Column 체크
                ++ans;
            }
        }
        System.out.println(ans);
    }

    public static boolean rowCheck(int y) {
        int start = map[y][0];
        int count = 1;
        boolean goDown = false;

        for (int x = 1; x < N; ++x) {
            if (start == map[y][x]) { // 높이가 같을 때
                ++count;
                continue;
            }

            if (start + 1 == map[y][x]) { // 올라갈 때
                if (!isValid(count, L)) {
                    return false;
                }
                // 내려갔다 올라갔을 경우. 경사로가 겹치면 안되기에 L*2 만큼 있어야 한다.
                if (goDown && !isValid(count, L * 2)) {
                    return false;
                }
                goDown = false;
            } else if (start - 1 == map[y][x]) { // 내려갈 때,
                if (goDown && !isValid(count, L)) {  // 내려간 후 또 내려갈 때 이전 층에서 경사로를 설치할 수 있는지 확인
                    return false;
                }
                goDown = true;
            } else { // 2칸 이상 높거나 낮다면 pass
                return false;
            }
            start = map[y][x];
            count = 1;
        }

        if (goDown && !isValid(count, L)) { // 내려간 상태로 끝난 경우엔 check 한 후 함수 종료
            return false;
        } else {
            return true;
        }
    }

    public static boolean colCheck(int x) {
        int start = map[0][x];
        int count = 1;
        boolean goDown = false;

        for (int y = 1; y < N; ++y) {
            if (start == map[y][x]) { // 높이가 같을 때
                ++count;
                continue;
            }

            if (start + 1 == map[y][x]) { // 올라갈 때
                if (!isValid(count, L)) {
                    return false;
                }
                // 내려갔다 올라갔을 경우. 경사로가 겹치면 안되기에 L*2 만큼 있어야 한다.
                if (goDown && !isValid(count, L * 2)) {
                    return false;
                }
                goDown = false;
            } else if (start - 1 == map[y][x]) { // 내려갈 때,
                if (goDown && !isValid(count, L)) {  // 내려간 후 또 내려갈 때 이전 층에서 경사로를 설치할 수 있는지 확인
                    return false;
                }
                goDown = true;
            } else { // 2칸 이상 높거나 낮다면 pass
                return false;
            }
            start = map[y][x];
            count = 1;
        }

        if (goDown && !isValid(count, L)) { // 내려간 상태로 끝난 경우엔 check 한 후 함수 종료
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValid(int count, int minValue) {
        return count >= minValue;
    }
}
//row
//6 2
//3 2 2 1 1 1
//3 2 2 2 2 1
//3 2 2 3 3 3
//3 2 2 2 3 3
//3 2 2 2 2 3
//3 3 3 3 2 2

//col
//6 2
//3 3 3 3 3 3
//3 2 2 2 2 2
//3 2 2 2 2 2
//3 2 2 3 2 1
//3 2 3 3 2 1
//3 3 3 3 1 1