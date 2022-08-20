package SamsungSW_A.삼성_21년_하반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14890_경사로 {

    private static int N, L;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        // 입력
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        run();
    }

    //  ㅡ ㅡ    \     /   ㅡ ㅡ /   \
    // /    \     ㅡ ㅡ   /           ㅡ ㅡ \
    public static void run() {
        int sum = 0;
        sum += findRow();
        sum += findCol();
        System.out.println(sum);
    }

    public static int findRow() {
        int cnt = 0;
        for (int y = 0; y < N; y++) {
            int dir = 0;
            int nowHeight = 0;
            int cntSameHeight = 1;
            boolean isRight = true;

            for (int x = 0; x < N; x++) {
                // 첫 열 시작(초기화)
                if (x == 0) {
                    nowHeight = map[y][x];
                    continue;
                }

                int nextHeight = map[y][x];
                if (nowHeight == nextHeight) { // 같은 높이
                    cntSameHeight++;
                    continue;
                }

                if (nowHeight + 1 == nextHeight) { // 오르막 길
                    if (dir == -1) { // 내리막에서 오르막
                        dir = 1;
                        if (cntSameHeight >= 2 * L) { // 설치 가능
                            cntSameHeight = 1;
                            nowHeight = nextHeight;
                            continue;
                        } else { // 설치 불가
                            isRight = false;
                            break;
                        }
                    } else {
                        dir = 1; // 오르막으로 방향 설정
                        if (!isValid(cntSameHeight)) { // L개 미만이면 오르막 설치 불가
                            isRight = false;
                            break;
                        }
                        cntSameHeight = 1;
                        nowHeight = nextHeight;
                        continue; // 오르막 경사로 설치
                    }
                } else if (nowHeight == nextHeight + 1) { // 내리막 길
                    if (dir == -1) { // 내리막에서 내리막
                        if (!isValid(cntSameHeight)) { // L개 미만이면 오르막 설치 불가
                            isRight = false;
                            break;
                        }
                    }
                    // 오르막에서 내리막
                    dir = -1;
                    cntSameHeight = 1;
                    nowHeight = nextHeight;
                } else { // 높이 차가 1이 아닐 때
                    isRight = false;
                    break;
                }
            }

            if (dir == -1) {
                if (!isValid(cntSameHeight)) {
                    isRight = false;
                }
            }

            if (isRight) {
                cnt++;
            }
        }
        return cnt;
    }

    public static int findCol() {
        int cnt = 0;
        for (int x = 0; x < N; x++) {
            int dir = 0;
            int nowHeight = 0;
            int cntSameHeight = 1;
            boolean isRight = true;

            for (int y = 0; y < N; y++) {
                // 첫 열 시작(초기화)
                if (y == 0) {
                    nowHeight = map[y][x];
                    continue;
                }

                int nextHeight = map[y][x];
                if (nowHeight == nextHeight) { // 같은 높이
                    cntSameHeight++;
                    continue;
                }

                if (nowHeight + 1 == nextHeight) { // 오르막 길
                    if (dir == -1) { // 내리막에서 오르막
                        dir = 1;
                        if (cntSameHeight >= 2 * L) { // 설치 가능
                            cntSameHeight = 1;
                            nowHeight = nextHeight;
                            continue;
                        } else { // 설치 불가
                            isRight = false;
                            break;
                        }
                    } else {
                        dir = 1; // 오르막으로 방향 설정
                        if (!isValid(cntSameHeight)) { // L개 미만이면 오르막 설치 불가
                            isRight = false;
                            break;
                        }
                        cntSameHeight = 1;
                        nowHeight = nextHeight;
                        continue; // 오르막 경사로 설치
                    }
                } else if (nowHeight == nextHeight + 1) { // 내리막 길
                    if (dir == -1) { // 내리막에서 내리막
                        if (!isValid(cntSameHeight)) { // L개 미만이면 오르막 설치 불가
                            isRight = false;
                            break;
                        }
                    }
                    // 오르막에서 내리막
                    dir = -1;
                    cntSameHeight = 1;
                    nowHeight = nextHeight;
                } else { // 높이 차가 1이 아닐 때
                    isRight = false;
                    break;
                }
            }

            if (dir == -1) {
                if (!isValid(cntSameHeight)) {
                    isRight = false;
                }
            }

            if (isRight) {
                cnt++;
            }
        }
        return cnt;
    }

    public static boolean isValid(int cntSameHeight) {
        return cntSameHeight >= L;
    }
}
//6 2
//3 3 3 3 3 3
//2 2 3 2 2 2
//1 1 2 2 3 4
//3 2 2 2 2 3
//4 4 3 3 2 2
//4 3 3 2 1 1