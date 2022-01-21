package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17136_색종이붙이기 {

    public static final int[] dy = {-1, 1, 0, 0, 1};
    public static final int[] dx = {0, 0, -1, 1, 1};

    private static int N, ans;
    private static int[] cntColoredPaper;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 10;
        map = new int[N][N];
        cntColoredPaper = new int[]{0, 0, 0, 0, 0, 0};

        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for(int n = 5; n >0; --n) {
            findPaper(n);
            System.out.println("====================");
        }

        System.out.println(Arrays.toString(cntColoredPaper));
        if(isRight()){
            System.out.println(ans);
        } else {
            System.out.println(-1);
        }
    }

    public static void findPaper(int n){
        int cnt = 0;
        List<int[]> list = new ArrayList<>();

        mapY:
        for(int y = 0; y <= N-n; ++ y){
            mapX:
            for(int x = 0; x <= N-n; ++x){
                cnt = 0;
                list.clear();
                for(int ny = y; ny < y+n; ++ny){
                    for(int nx = x; nx < x+n; ++nx){
                        if(map[ny][nx] == 0){
                            continue mapX;
                        }
                        list.add(new int[]{ny, nx});
                        cnt++;
                    }
                }
                if(cnt == n*n){
                    deletePaper(list);
                    cntColoredPaper[n]++;
                    if(n == 5){
                        System.out.println("y: " + y + ", x: " + x);
                    }
                }
            }
        }
    }

    public static void deletePaper(List<int[]> list){
        for(int[] point : list){
            map[point[0]][point[1]] = 0;
        }
    }

    public static boolean isRight(){
        for(int cnt : cntColoredPaper){
            if(cnt > 5){
                return false;
            }
            ans += cnt;
        }

        return true;
    }
}
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 1
//1 1 1 1 1 1 1 1 1 0
