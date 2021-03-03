package Baekjoon;

import java.io.*;

public class Baekjoon2810_컵홀더 {

    static int N;
    static int cnt;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        
        N = Integer.parseInt(br.readLine());
        char [] str = br.readLine().toCharArray();
        cnt = 0;
        boolean flag = false;

        // LL (커플석)의 좌석수 - 1인 사람만 컵홀더를 사용할 수 없다.
        for (int i = 0; i < N; i++) {
            if(str[i] == 'L') {
                i++;
                cnt++;
                flag = true;
            }
        }

        // 반례 때문에 만든 flag (SS 일 때는 2가 나와야 하는데 3이 나왔다)
        if(!flag)
            System.out.println(N);
        else
            System.out.println(N-cnt+1);
    }

    // 반례..
    static String input = "2\n" +
            "SS";
}
