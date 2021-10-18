package StepByStep._2021.day210305;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class BOJ1541_S2_잃어버린괄호 {

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        // (1) "-" 기준으로 분류
        StringTokenizer total = new StringTokenizer(br.readLine(), "-");
        // (2) "-"로 분류된 것 중 가장 앞에 있는 덩어리(문자열)를 다 더하기 위해 "+"로 분류
        StringTokenizer first = new StringTokenizer(total.nextToken(), "+");

        // "-"로 분류 중 가장 첫 번째 덩어리를 다 더해준다.
        int sum = 0;
        while(first.hasMoreTokens()){
            sum += Integer.parseInt(first.nextToken());
        }
        // "-"로 분류 중 두 번째 덩어리들의 숫자들을 다 더한 후, 전체에서 빼준다.
        while(total.hasMoreTokens()){
            // block = "+"가 있는 덩어리(문자열)
            StringTokenizer block = new StringTokenizer(total.nextToken(), "+"); // "+"를 기준으로 분류

            // 숫자들만 더한다.
            while(block.hasMoreTokens()){ // 분류된 숫자들을 더함
                sum -= Integer.parseInt(block.nextToken());
            }
        }

        System.out.println(sum);
    }

    static String input = "55-50+40";
}
