package StepByStep.day210327;

import java.util.*;
import java.io.*;

public class BOJ16235_G4_나무재테크 {

    static int N, M, K;
    static int[][] map;
    static int[][] robot;
    static Queue<Tree> trees;
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken()); // map 의 크기
        M = Integer.parseInt(st.nextToken()); // M 개의 나무
        K = Integer.parseInt(st.nextToken()); // K 년 후,

        robot = new int[N][N];  // 로봇이 겨울마다 주는 양분
        map = new int[N][N];    // 현재 양분 상황

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                robot[y][x] = Integer.parseInt(st.nextToken());
                map[y][x] = 5;
            }
        }

        trees = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            // input 의 값은 1,1 부터 시작하므로, 좌표 수정 필요
            trees.offer(new Tree(Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())));
        }

        // 중요! 먼저 정렬을 하면 나중에 할 필요를 줄일 수 있다.
        Collections.sort((List<Tree>)trees);

        // K 년 만큼 반복문
        while (K-- > 0) {
            // (1)봄과 (2)여름에 죽은 나무를 이용하기 위해 List 선언
            List<Tree> deadTrees = new ArrayList<>();

            // 1. 봄, 2. 여름, 3. 가을, 4. 겨울
            for (int season = 1; season <= 4; season++) {

                // 1. 봄
                if (season == 1) {
                    // 현재 가진 나무의 수만큼 탐색
                    int size = trees.size();
                    while (size-- > 0) {
                        Tree now = trees.poll();
                        // 나무의 나이만큼 양분이 있다면 나이 + 1
                        if (map[now.y][now.x] >= now.age) {
                            map[now.y][now.x] -= now.age;
                            now.age++;
                            trees.offer(new Tree(now.y, now.x, now.age));
                        }
                        // 나무의 나이만큼 양분이 없다면 => 죽음
                        else {
                            deadTrees.add(now); // (2) 여름에 사용
                        }
                    }
                }

                // 2. 여름
                else if (season == 2) {
                    // 죽은 나무위치에 age/2 만큼 양분을 추가
                    for (Tree tree : deadTrees) {
                        map[tree.y][tree.x] += tree.age / 2;
                    }
                }

                // 3. 가을
                else if (season == 3) {
                    // 나무는 나이가 적은 나무부터 양분을 주어야 하기 때문에 
                    // Queue 에 새로 생긴 나무가 먼저 들어가기 위해 기존의 나무들을 임시 저장
                    List<Tree> originTrees = new ArrayList<>();
                    int size = trees.size();
                    
                    // 현재 나무 탐색
                    while (size-- > 0) {
                        Tree now = trees.poll();

                        // 나무의 나이가 5의 배수면 인접한 8방향에 나무를 심는다.
                        if (now.age % 5 == 0) {
                            for (int d = 0; d < 8; d++) {
                                int ny = now.y + dy[d];
                                int nx = now.x + dx[d];

                                // 유효성 검사 : 범위 check
                                if (isIn(ny, nx)) {
                                    // 나무 추가
                                    trees.offer(new Tree(ny, nx, 1));
                                }
                            }
                        }
                        originTrees.add(now);
                    }
                    // 새로운 나무가 들어간 후, 기존의 나무들을 추가
                    // 따로 정렬할 필요가 없다.
                    for(Tree t : originTrees)
                        trees.offer(t);
                }

                // 4. 겨울
                else{
                    // S2D2 로봇이 모든 곳을 돌아다니며 지정한 양분을 추가해준다.
                    for (int y = 0; y < N; y++) {
                        for (int x = 0; x < N; x++) {
                            map[y][x] += robot[y][x];
                        }
                    }
                }
            }
        }
        System.out.println(trees.size());

    }

    static class Tree implements Comparable<Tree>{
        int y;
        int x;
        int age;

        public Tree(int y, int x, int age) {
            this.y = y;
            this.x = x;
            this.age = age;
        }

        @Override
        public int compareTo(Tree t){
            return Integer.compare(this.age, t.age);
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
