package KaKao_코딩테스트;

import java.util.*;

public class 비밀지도 {

    public static int n = 5;
    public static int[] arr1 = {9, 20, 28, 18, 4};
    public static int[] arr2 = {30, 1, 21, 17, 3};

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        String[] res = new String[n];
        for(int i = 0; i < n; ++i){
            res[i] = Integer.toBinaryString(arr1[i] | arr2[i]);

            for(int k = 0; k < n -res[i].length(); ++k) sb.append(" ");
            for(int j = 0; j < res[i].length(); ++j){
                if(res[i].charAt(j) == '1') sb.append("#");
                else sb.append(" ");
            }
            res[i] = sb.toString();
            sb.setLength(0);
        }
        System.out.println(Arrays.toString(res));
    }
}
