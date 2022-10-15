package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOj20327_배열돌리기6 {

    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {1, 0, -1, 0};

    private static int N, M, R;
    private static int[][] map;
    private static int[][] tmpMap;
    private static List<Integer> tmpList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = (int) Math.pow(2, N);
        R = Integer.parseInt(st.nextToken());
        map = new int[M][M];
        tmpMap = new int[M][M];
        tmpList = new ArrayList<>();

        for (int y = 0; y < M; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while (R-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int k = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            tmpMap = new int[M][M];
            int len = (int) Math.pow(2, l);

            if (k >= 5) {
                if (k == 5) rotate5(len);
                if (k == 6) rotate6(len);
                if (k == 7) rotate7(len);
                if (k == 8) rotate8(len);
            } else {
                divide(M, 0, 0, k, len);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < M; ++y) {
            for (int x = 0; x < M; ++x) {
                sb.append(map[y][x]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void divide(int len, int y, int x, int k, int targetLen) {
        if (len == targetLen) {
            switch (k) {
                case 1:
                    rotate1(len, y, x);
                    break;
                case 2:
                    rotate2(len, y, x);
                    break;
                case 3:
                    rotate3(len, y, x);
                    break;
                case 4:
                    rotate4(len, y, x);
                    break;
            }
            return;
        }

        int half = len / 2;
        divide(half, y, x, k, targetLen);
        divide(half, y, x + half, k, targetLen);
        divide(half, y + half, x, k, targetLen);
        divide(half, y + half, x + half, k, targetLen);
    }

    public static void rotate1(int len, int y, int x) {
        tmpList.clear();

        for (int ny = y; ny < y + len; ++ny) {
            for (int nx = x; nx < x + len; ++nx) {
                tmpList.add(map[ny][nx]);
            }
        }

        int idx = 0;
        for (int ny = y + len - 1; ny >= y; --ny) {
            for (int nx = x; nx < x + len; ++nx) {
                map[ny][nx] = tmpList.get(idx++);
            }
        }
    }

    public static void rotate2(int len, int y, int x) {
        tmpList.clear();

        for (int ny = y; ny < y + len; ++ny) {
            for (int nx = x; nx < x + len; ++nx) {
                tmpList.add(map[ny][nx]);
            }
        }

        int idx = 0;
        for (int ny = y; ny < y + len; ++ny) {
            for (int nx = x + len - 1; nx >= x; --nx) {
                map[ny][nx] = tmpList.get(idx++);
            }
        }
    }

    public static void rotate3(int len, int y, int x) {
        tmpList.clear();

        for (int nx = x; nx < x + len; ++nx) {
            for (int ny = y; ny < y + len; ++ny) {
                tmpList.add(map[ny][nx]);
            }
        }

        int idx = 0;
        for (int ny = y; ny < y + len; ++ny) {
            for (int nx = x + len - 1; nx >= x; --nx) {
                map[ny][nx] = tmpList.get(idx++);
            }
        }
    }

    public static void rotate4(int len, int y, int x) {
        tmpList.clear();

        for (int ny = y; ny < y + len; ++ny) {
            for (int nx = x; nx < x + len; ++nx) {
                tmpList.add(map[ny][nx]);
            }
        }

        int idx = 0;
        for (int nx = x; nx < x + len; ++nx) {
            for (int ny = y + len - 1; ny >= y; --ny) {
                map[ny][nx] = tmpList.get(idx++);
            }
        }
    }

    public static void rotate5(int len) { // 수정 필요
        tmpList.clear();
        for(int y = 0; y < M; y += len) {
            for(int x = 0; x < M; x += len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        tmpList.add(map[ny][nx]);
                    }
                }
            }
        }

        int idx = 0;
        for(int y = M-len; y >= 0; y -= len) {
            for(int x = 0; x < M; x += len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        map[ny][nx] = tmpList.get(idx++);
                    }
                }
            }
        }
    }

    public static void rotate6(int len) { // 수정 필요
        tmpList.clear();

        for(int y = 0; y < M; y += len) {
            for(int x = 0; x < M; x += len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        tmpList.add(map[ny][nx]);
                    }
                }
            }
        }

        int idx = 0;
        for(int y = 0; y < M; y += len) {
            for(int x = M-len; x >= 0; x -= len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        map[ny][nx] = tmpList.get(idx++);
                    }
                }
            }
        }
    }

    public static void rotate7(int len) {
        tmpList.clear();
        for(int x = 0; x < M; x += len) {
            for(int y = 0; y < M; y += len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        tmpList.add(map[ny][nx]);
                    }
                }
            }
        }

        int idx = 0;
        for(int y = 0; y < M; y += len) {
            for(int x = M-len; x >= 0; x-= len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        map[ny][nx] = tmpList.get(idx++);
                    }
                }
            }
        }
    }

    public static void rotate8(int len) {
        tmpList.clear();
        for(int y = 0; y < M; y += len) {
            for(int x = 0; x < M; x += len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        tmpList.add(map[ny][nx]);
                    }
                }
            }
        }

        int idx = 0;
        for(int x = 0; x < M; x += len) {
            for (int y = M - len; y >= 0; y -= len) {
                for(int ny = y; ny < y + len; ++ny) {
                    for(int nx = x; nx < x + len; ++nx) {
                        map[ny][nx] = tmpList.get(idx++);
                    }
                }
            }
        }
    }

    // 매우 중요! (한 번은 대비하자)
    // 바깥 사각형부터 안쪽 사각형까지 사각형을 따라 모두 시계방향으로 한 칸씩 이동
    public static void rotate9(int len) {
        int size = M / 2;
        boolean[][] visited = new boolean[M][M];

        for (int s = 0; s < size; s += len) {
            int y = s;
            int x = s;
            int d = 0;

            outer1:
            while (true) {
                for (int ny = y; ny < y + len; ++ny) {
                    for (int nx = x; nx < x + len; ++nx) {
                        int nny = ny + dy[d] * len;
                        int nnx = nx + dx[d] * len;

                        if (nny == s && nnx == s) {
                            break outer1;
                        }
                        if (!isIn(nny, nnx) || visited[nny][nnx]) {
                            d = (d + 1) % 4;
                            continue outer1;
                        }

                        tmpMap[nny][nnx] = map[ny][nx];
                        visited[ny][nx] = true;
                    }
                }

                y = y + dy[d] * len;
                x = x + dx[d] * len;
            }

            for (int ny = s; ny < s + len; ++ny) {
                for (int nx = s; nx < s + len; ++nx) {
                    int nny = ny + dy[1] * len;
                    int nnx = nx + dx[1] * len;
                    tmpMap[ny][nx] = map[nny][nnx];
                }
            }
        }
    }

    // 매우 중요! (한 번은 대비하자)
    // 바깥 사각형부터 안쪽 사각형까지 사각형을 따라 모두 반시계방향으로 한 칸씩 이동
    public static void rotate10(int len) {
        int size = M / 2;
        boolean[][] visited = new boolean[M][M];

        for (int s = 0; s < size; s += len) {
            int y = s;
            int x = s;
            int d = 1;

            outer1:
            while (true) {
                for (int ny = y; ny < y + len; ++ny) {
                    for (int nx = x; nx < x + len; ++nx) {
                        int nny = ny + dy[d] * len;
                        int nnx = nx + dx[d] * len;

                        if (nny == s && nnx == s) {
                            break outer1;
                        }
                        if (!isIn(nny, nnx) || visited[nny][nnx]) {
                            d = (d + 3) % 4;
                            continue outer1;
                        }

                        tmpMap[nny][nnx] = map[ny][nx];
                        visited[ny][nx] = true;
                    }
                }

                y = y + dy[d] * len;
                x = x + dx[d] * len;
            }

            for (int ny = s; ny < s + len; ++ny) {
                for (int nx = s; nx < s + len; ++nx) {
                    int nny = ny + dy[0] * len;
                    int nnx = nx + dx[0] * len;
                    tmpMap[ny][nx] = map[nny][nnx];
                }
            }
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < M && x < M;
    }
}
