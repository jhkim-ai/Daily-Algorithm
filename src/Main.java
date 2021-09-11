import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] arr = new int[N*N];

        for(int i = 0; i < arr.length; ++i){
            int row = i / N + 1;
            int col = i % N + 1;
            arr[i] = row*col;
        }
        Arrays.sort(arr);
        System.out.println(arr[K-1]);
    }
}


