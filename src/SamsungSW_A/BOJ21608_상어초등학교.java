package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21608_상어초등학교 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] scores = {0, 1, 10, 100, 1000};

    private static int N;
    private static int[][] map;
    private static boolean[][] relation;
    private static Queue<Integer> order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        relation = new boolean[N * N][N * N];
        order = new LinkedList<>();

        for (int y = 0; y < N; ++y) {
            Arrays.fill(map[y], -1);
        }

        for (int i = 0; i < N * N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int studentID = Integer.parseInt(st.nextToken()) - 1;
            order.offer(studentID);
            for (int j = 0; j < 4; j++) {
                int feelingID = Integer.parseInt(st.nextToken()) - 1;
                relation[studentID][feelingID] = true;
            }
        }

        while (!order.isEmpty()) {
            int nowStudentID = order.poll();
            List<Point> locList = getAdjLocList(nowStudentID);

            if (locList.size() > 1) {
                locList = getEmptyLocList(locList);
            }

            if (locList.size() > 1) {
                Collections.sort(locList);
            }
            Point selectedLoc = locList.get(0);
            map[selectedLoc.y][selectedLoc.x] = nowStudentID;
        }

        System.out.println(getSumSatisfiedScore());
    }

    public static List<Point> getAdjLocList(int studentID) {
        List<Point> list = new ArrayList<>();
        int adjCount = Integer.MIN_VALUE;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if(map[y][x] != -1){
                    continue;
                }
                int cnt = 0;
                for (int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if (!isIn(ny, nx) || map[ny][nx] == -1) { // 범위 밖 or 학생이 없다면 pass
                        continue;
                    }
                    if (relation[studentID][map[ny][nx]]) { // 좋아하는 학생이 인접하다면 cnt 증가
                        ++cnt;
                    }
                }

                if (adjCount < cnt) { // 더 큰 인접한 수가 나온다면, list 갱신
                    adjCount = cnt;
                    list.clear();
                    list.add(new Point(y, x));
                } else if (adjCount == cnt) { // 인접한 수가 같다면, list 추가
                    list.add(new Point(y, x));
                }
            }
        }

        return list;
    }

    public static List<Point> getEmptyLocList(List<Point> list) {
        List<Point> newList = new ArrayList<>();
        int emptyCount = Integer.MIN_VALUE;

        for (Point curLoc : list) {
            int y = curLoc.y;
            int x = curLoc.x;
            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (!isIn(ny, nx) || map[ny][nx] != -1) { // 범위 밖이거나 비어있지 않은 경우
                    continue;
                }
                ++cnt;
            }

            if (emptyCount < cnt) {
                emptyCount = cnt;
                newList.clear();
                newList.add(new Point(y, x));
            } else if (emptyCount == cnt) {
                newList.add(new Point(y, x));
            }
        }

        return newList;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static int getSumSatisfiedScore(){
        int sum = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int studentId = map[y][x];
                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if(!isIn(ny, nx)){
                        continue;
                    }
                    if(relation[studentId][map[ny][nx]]){
                        cnt++;
                    }
                }

                sum += scores[cnt];
            }
        }

        return sum;
    }

    public static class Point implements Comparable<Point> {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Point p) {
            if (this.y == p.y) {
                return Integer.compare(this.x, p.x);
            }
            return Integer.compare(this.y, p.y);
        }

        @Override
        public String toString() {
            return "y=" + y + ", x=" + x;
        }
    }

    public static void print(){
        System.out.println("=========");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
