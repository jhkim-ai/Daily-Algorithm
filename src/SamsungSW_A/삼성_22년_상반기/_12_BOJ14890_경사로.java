package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _12_BOJ14890_경사로 {

    private static int N, L, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        run();
        System.out.println(ans);
    }

    public static void run(){
        int[] arr;
        for(int y = 0; y < N; ++y){
            arr  = new int[N];
            for(int x = 0; x < N; ++x){
                arr[x] = map[y][x];
            }
            check(arr);
        }
        for(int x = 0; x < N; ++x){
            arr  = new int[N];
            for(int y = 0; y < N; ++y){
                arr[y] = map[y][x];
            }
            check(arr);
        }
    }

    public static void check(int[] arr){
        int now = arr[0];
        int cnt = 1;
        boolean isDown = false; // 현재 오르막인지 내리막인지

        for(int i = 1; i < N; ++i){
            if(arr[i] == now){
                cnt++;
                continue;
            }

            if(arr[i] == now + 1){ // 오르막일 경우
                if(isDown){ // 내리막이었다 오르막이 되는 경우
                    if(cnt < L * 2) return;
                } else {    // 오르막이었다 오르막일 경우
                    if(cnt < L) return;
                }
                isDown = false;
            } else if(arr[i] == now - 1){ // 내리막일 경우
                if(isDown){ // 내리막이었다 내리막인 경우
                    if(cnt < L) return;
                } else {    // 오르막이었다 내리막인 경우
                }
                isDown = true;
            } else return;  // 높이 차가 2 이상은 안됨

            cnt = 1;
            now = arr[i];
        }

        if(isDown && cnt < L){ // 마지막에 내리막으로 남아있을 경우
            return;
        }

        ans++;
    }
}
