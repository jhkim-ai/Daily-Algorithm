import java.io.*;
import java.util.*;

public class Main {

    private static int N, S;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int start = 0;
        int end = 0;
        for(int i = 0; i < N; ++i){
            sum += arr[i];
        }
    }
}