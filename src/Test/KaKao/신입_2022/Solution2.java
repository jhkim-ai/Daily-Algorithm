package Test.KaKao.신입_2022;

import java.util.StringTokenizer;

public class Solution2 {

    private static int n, k;

    public static void main(String[] args) {
        n = 437674;
        k = 3;

        int ans = 0;
        String numBaseK = digitToBaseK(n, k); // 진법 변환

        // "0"을 기준으로 나눈다.
        StringTokenizer st = new StringTokenizer(numBaseK, "0");
        while(st.hasMoreTokens()){
            long num = Long.parseLong(st.nextToken());
            if(checkPrimeNumber(num)){ // 각 숫자가 Prime Number 라면 Check
                ans++;
            }
        }

        System.out.println(ans);
    }

    // num 까지의 자연수 중 Prime Number 를 판별
    public static boolean checkPrimeNumber(long num){

        if(num == 1){
            return false;
        }

        for(long i = 2; i <= Math.sqrt(num); ++i){
            if(num % i == 0){
                return false;
            }
        }

        return true;
    }

    // 10진수를 K 진수로 변환
    public static String digitToBaseK(int num, int base){
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            sb.insert(0, num%base);
            num /= base;
        }
        return sb.toString();
    }
}
