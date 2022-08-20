package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ1769_3의배수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arrNum = br.readLine().toCharArray();
        int sum = 0;
        int ans = 0;
        for(char c : arrNum){
            sum += c - '0';
        }
        if(arrNum.length > 1){
            ans = 1;
        }

        while(sum >= 10){
            sum = reduceNum(sum);
            ans++;
        }

        System.out.println(ans);
        if(sum == 3 || sum == 6 || sum == 9){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static int reduceNum(int num){
        int sum = 0;
        char[] arrNum = Integer.toString(num).toCharArray();
        for(char c : arrNum){
            sum += c - '0';
        }

        return sum;
    }
}
