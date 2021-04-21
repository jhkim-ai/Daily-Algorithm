package StepByStep.day210423;

import java.util.*;
import java.io.*;

public class BOJ1662_압축 {

    static String str;
    static int ans;
    static boolean[] visited;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        visited = new boolean[str.length()];

        // 주의!. String 으로 접근했을 때, Worst Case 에 의하여 메모리 초과가 난다.
        // Worst Case : 9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(111))))))))))))))))))))))))

        // Idea. 길이를 계산 (dfs)
        System.out.println(dfs(0));
    }

    // 6(22)122 : 15
    // 3(3(3(2(2)2(2)))) : 108
    static int dfs(int start){
        int len = 0;
        for(int i = start; i<str.length(); ++i){
            char now = str.charAt(i);

            if(visited[i]) continue;
            // 방문 표시를 한 뒤에 dfs를 돌아야 한다.
            visited[i] = true;

            if(now == ')'){
                return len;
            } else if (now == '('){
                len--;
                len += (str.charAt(i-1) - '0') * dfs(i+1);
            } else
                len++;
        }
        return len;
    }
}
