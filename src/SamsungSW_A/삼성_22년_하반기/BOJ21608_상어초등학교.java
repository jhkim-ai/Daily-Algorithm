package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ21608_상어초등학교 {

    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static int N, M;
    private static int[][] map;
    private static Set<Integer>[] sets;
    private static List<Seat> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = N * N + 1;
        sets = new Set[M];
        map = new int[N][N];

        for(int i = 1; i < M; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int studentIdx = Integer.parseInt(st.nextToken());
            sets[studentIdx] = new HashSet<>();
            list = new ArrayList<>();

            for(int j = 0; j < 4; ++j) {
                sets[studentIdx].add(Integer.parseInt(st.nextToken()));
            }

            step1(studentIdx);
            Collections.sort(list);

            int setY = list.get(0).y;
            int setX = list.get(0).x;
            map[setY][setX] = studentIdx;

        }

        System.out.println(getScore());
    }

    public static void step1(int studentIdx) {
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {

                if(map[y][x] != 0) continue;

                int cntLikeStudent = 0;
                int cntEmptySeat = 0;

                for(int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx)) continue;
                    if(sets[studentIdx].contains(map[ny][nx])) cntLikeStudent++;
                    if(map[ny][nx] == 0) cntEmptySeat++;

                    list.add(new Seat(y, x, cntLikeStudent, cntEmptySeat));
                }
            }
        }
    }

    public static int getScore() {
        int score = 0;

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                int cnt = 0;
                int studentIdx = map[y][x];
                for(int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if(!isIn(ny, nx) || !sets[studentIdx].contains(map[ny][nx])) continue;
                    ++cnt;
                }

                if(cnt != 0) score += (int)Math.pow(10, cnt - 1);
            }
        }

        return score;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Seat implements Comparable<Seat>{
        int y;
        int x;
        int cntLikeStudent;
        int cntEmptySeat;

        public Seat(int y, int x, int cntLikeStudent, int cntEmptySeat) {
            this.y = y;
            this.x = x;
            this.cntLikeStudent = cntLikeStudent;
            this.cntEmptySeat = cntEmptySeat;
        }

        @Override
        public int compareTo(Seat s){
            if(this.cntLikeStudent == s.cntLikeStudent){
                if(this.cntEmptySeat == s.cntEmptySeat){
                    if(this.y == s.y) return Integer.compare(this.x, s.x);
                        return Integer.compare(this.y, s.y);
                }
                return Integer.compare(s.cntEmptySeat, this.cntEmptySeat);
            }
            return Integer.compare(s.cntLikeStudent, this.cntLikeStudent);
        }
    }

}
