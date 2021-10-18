package StepByStep._2021.day211016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2469_사다리타기 {

    private static int K, N, H;
    private static char[] resList;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine()); // 명수
        N = Integer.parseInt(br.readLine()); // 가로 막대기 수
        resList = br.readLine().toCharArray();

        map = new char[N][];
        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
            if (map[y][0] == '?') {
                H = y;
                map[y] = new char[K - 1];
                continue;
            }
        }

        // 완전 탐색(중복 순열)
        duplicatedCombination(K - 1, new int[K - 1]);

        // 답이 없다면 "xx...xx" 로 표현
        StringBuilder sb = new StringBuilder();
        while (K-- > 1) {
            sb.append('x');
        }
        System.out.println(sb);
    }

    // 완전 탐색(중복 순열)
    public static void duplicatedCombination(int cnt, int[] selected) {
        if (cnt == 0) {
            // H 행을 사다리 기호로 변환
            for (int x = 0; x < selected.length; x++) {
                if (selected[x] == 0) {
                    map[H][x] = '*';
                } else {
                    map[H][x] = '-';
                }

                if(x == 0){
                    continue;
                }

                // 유효성 검사 전, '-''-'인 경우를 사전에 거른다.(사다리 게임 특성상 일어날 수 없음)
                if(map[H][x] == '-' && map[H][x-1] == map[H][x]){
                    return;
                }
            }

            // 각 경우의 수마다 유효성 검사
            if (validCheck()) {
                // if 문을 통과할 시, 정답 출력 및 종료.
                StringBuilder sb = new StringBuilder();
                for (char c : map[H]) {
                    sb.append(c);
                }
                System.out.println(sb);
                System.exit(0);
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            selected[selected.length - cnt] = i;
            duplicatedCombination(cnt - 1, selected);
        }
    }

    // 유효성 검사
    // Idea. 열 번호만 +,- 를 진행한다.
    // 설명: 사다리타기는 각 행(n)을 진행할 때마다 '-' 유무에만 영향을 받는다.
    //       따라서, '-' 를 만났을 때 열의 위치를 Control 하면 된다고 생각했다.
    public static boolean validCheck() {
        for (int idx = 0; idx < K; idx++) {
            int nextIdx = idx;
            for (int n = 0; n < N; ++n) {
                if (nextIdx == 0) { // 맨 왼쪽일 경우, 오른쪽으로만 이동 가능.
                    if (map[n][nextIdx] == '-') {
                        nextIdx++;
                    } else {
                        continue;
                    }
                } else if (nextIdx == K - 1) { // 맨 오른쪽일 경우, 왼쪽으로만 이동 가능.
                    if (map[n][nextIdx - 1] == '-') {
                        nextIdx--;
                    } else {
                        continue;
                    }
                } else {
                    if (map[n][nextIdx - 1] == '-' && map[n][nextIdx] == '*') {
                        nextIdx--;
                    } else if (map[n][nextIdx - 1] == '*' && map[n][nextIdx] == '-') {
                        nextIdx++;
                    } else {
                        continue;
                    }
                }
            }

            // 맨 아래로 도착했을 때, 순서가 맞지 않으면 바로 종료.
            char curChar = (char) ('A' + idx);
            if (curChar != resList[nextIdx]) {
                return false;
            }
        }

        return true;
    }
}
