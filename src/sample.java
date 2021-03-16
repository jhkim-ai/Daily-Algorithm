import java.io.*;
import java.util.*;

public class sample {

    static String str;
    static StringBuilder sb = new StringBuilder();
    static int[] arr = {1,2,3,4,5,6};

    public static void main(String[] args) throws Exception {
        comb(4, new int[4], 0);
    }

    static void comb(int cnt, int[] selected, int startIdx){
        if(cnt == 0){
            System.out.println(Arrays.toString(selected));
            return;
        }

        for (int i = startIdx; i < arr.length; i++) {
            selected[selected.length - cnt] = arr[i];
            comb(cnt-1, selected, i+1);
        }
    }
}
