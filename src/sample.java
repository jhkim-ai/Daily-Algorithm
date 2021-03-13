import java.io.*;
import java.util.*;

public class sample {

    static int[] arr = {1, 2, 3};

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if(N%2 == 0)
            System.out.println("CK");
        else
            System.out.println("SK");
        // test
}
}
