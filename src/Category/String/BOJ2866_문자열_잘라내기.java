package Category.String;

import java.util.*;
import java.io.*;

public class BOJ2866_문자열_잘라내기 {

    private static int R, C;
    private static char[][] input;
    private static Set<String> s;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        input = new char[R][C];
        s = new HashSet<>();
        int ans = -1;

        // String 입력
        for(int i = 0; i < R; ++i){
            input[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < R; ++i){
            if(!search(i)) break;
            ++ans;
        }

        if(ans == -1) ans = 0;
        System.out.println(ans);
    }

    public static boolean search(int idx) {
        s.clear();
        StringBuilder sb = new StringBuilder();
        for(int c = 0; c < C; ++c){
            for(int r = idx; r < R; ++r){
                sb.append(input[r][c]);
            }
            String tmp = sb.toString();
            if(s.contains(tmp)) return false;
            s.add(tmp);
            sb.setLength(0);
        }

        // 중복된 문자열이 없음
        return true;
    }
}
