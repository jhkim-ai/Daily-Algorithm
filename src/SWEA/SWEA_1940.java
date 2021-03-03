package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1940 {

    static int T, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= 2; t++) {
            N = Integer.parseInt(br.readLine());

            int acc =0;
            int distance = 0;

            for (int i = 0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int dir = Integer.parseInt(st.nextToken());
                if (dir == 0){
                    distance += acc;
                    continue;
                }
                int speed = Integer.parseInt(st.nextToken());

                switch (dir){
                    case 1:
                        acc += speed;
                        distance += acc;
                        break;
                    case 2:
                        acc -= speed;
                        if(acc < 0)
                            distance = 0;
                        else
                            distance -= acc;
                        break;
                }
            }
            sb.append("#").append(t).append(" ").append(distance).append("\n");
        }
        System.out.println(sb);
    }
}
