package StepByStep.day211030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2511_카드놀이 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int scoreA = 0;
        int scoreB = 0;
        int lastWinRoundA = -1;
        int lastWinRoundB = -1;
        int[] A = new int[10];
        int[] B = new int[10];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 10; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 10; i++) {
            if(A[i] > B[i]){
                scoreA += 3;
                lastWinRoundA = i;
            } else if (A[i] < B[i]){
                scoreB += 3;
                lastWinRoundB = i;
            } else {
                scoreA += 1;
                scoreB += 1;
            }
        }
        sb.append(scoreA).append(" ").append(scoreB).append("\n");
        if(scoreA > scoreB){
            sb.append("A");
        } else if (scoreA < scoreB){
            sb.append("B");
        } else {
            if(lastWinRoundA > lastWinRoundB){
                sb.append("A");
            } else if(lastWinRoundA < lastWinRoundB){
                sb.append("B");
            } else {
                sb.append("D");
            }
        }

        System.out.println(sb);
    }
}
