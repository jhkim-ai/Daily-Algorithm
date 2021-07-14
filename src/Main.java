import java.io.*;
import java.util.*;

public class Main {

    private static int T, N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; ++t){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            for(int i = 0; i < M; ++i){
                br.readLine();
            }
            sb.append(N-1).append("\n");
        }
        System.out.print(sb);
    }
}