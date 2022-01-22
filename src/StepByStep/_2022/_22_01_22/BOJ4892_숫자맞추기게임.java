package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ4892_숫자맞추기게임 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while(true){
            ++idx;
            int n0 = Integer.parseInt(br.readLine());
            if(n0 == 0){ // 0 입력 시, 종료
                System.out.println(sb);
                return;
            }

            int n1 = 3 * n0;
            int n2 = 0;
            int n3 = 0;
            int n4 = 0;
            boolean isOdd = n1 % 2 == 1 ? true : false;
            sb.append(idx).append(". ");
            if(isOdd){
                n2 = (n1 + 1) / 2;
                sb.append("odd");
            } else {
                n2 = n1 / 2;
                sb.append("even");
            }
            sb.append(" ");

            n3 = 3 * n2;
            n4 = n3 / 9;
            sb.append(n4).append("\n");
        }
    }
}
