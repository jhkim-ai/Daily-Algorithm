import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int N, M;
    static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boolean[][][] visited = new boolean[N][M][16];
        char[][] map = new char[N][M];
        Queue<Ssafy> queue = new LinkedList<Ssafy>();

        for (int n = 0; n < N; n++) {
            String input = br.readLine();
            for (int m = 0; m < M; m++) {
                map[n][m] = input.charAt(m);
                if (map[n][m] == 'P') {
                    queue.offer(new Ssafy(n, m, 0));
                    visited[n][m][0] = true;
                    map[n][m] = '.';
                }
            }
        }

        int answer = -1;
        int time = 0;
        loop: while (!queue.isEmpty()) {
            int size = queue.size();
            time++;

            while (size-- > 0) {
                Ssafy now = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nX = now.x + delta[i][0];
                    int nY = now.y + delta[i][1];
                    int nVisit = now.visit;

                    while (isIn(nX, nY) && map[nX][nY] != '#') {
                        if (map[nX][nY] != '.') {
                            int loc = 0;

                            switch (map[nX][nY]) {
                                case 'S':
                                    loc = 0;
                                    break;
                                case 'D':
                                    loc = 1;
                                    break;
                                case 'G':
                                    loc = 2;
                                    break;
                                case 'M':
                                    loc = 3;
                                    break;
                            }

                            nVisit = nVisit | 1 << loc;
                        }
                        nX += delta[i][0];
                        nY += delta[i][1];
                    }

                    nX -= delta[i][0];
                    nY -= delta[i][1];

                    if (nVisit == 15) {
                        answer = time;
                        break loop;
                    }

                    if (!visited[nX][nY][nVisit]) {
                        visited[nX][nY][nVisit] = true;
                        queue.offer(new Ssafy(nX, nY, nVisit));
                    }
                }
            }
        }

        System.out.println(answer);
    }

    static private class Ssafy {
        int x, y, visit;

        public Ssafy(int x, int y, int visit) {
            this.x = x;
            this.y = y;
            this.visit = visit;
        }

    }

    static boolean isIn(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < M) {
            return true;
        }
        return false;
    }
}