package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1963_소수경로 {

    private static String start;
    private static String end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; ++t){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            start = st.nextToken();
            end = st.nextToken();
        }
    }
}
