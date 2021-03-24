import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int low = 1;
        int high = 0;
        int idx = 1;
        while(true){
            high = 3*idx*idx -(3*idx) +1;
            if(N == 1)
                break;
            if(low < N && N <= high)
                break;
            low = high;
            idx++;
        }

        System.out.println(idx);
    }

}
