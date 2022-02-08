package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1668_트로피진열 {

    private static int N;
    private static int[] trophies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        trophies = new int[N];
        for(int i = 0; i < N; ++i){
            trophies[i] = Integer.parseInt(br.readLine());
        }

        int cntLeft = 1;
        int cntRight = 1;
        int maxHeight = trophies[0];

        for(int i = 1; i < N; ++i){
            if(maxHeight < trophies[i]) {
                maxHeight = trophies[i];
                cntLeft++;
            }
        }

        maxHeight = trophies[N-1];
        for(int i = N-2; i >= 0; --i){
            if(maxHeight < trophies[i]) {
                maxHeight = trophies[i];
                cntRight++;
            }
        }

        System.out.println(cntLeft);
        System.out.println(cntRight);
    }
}
