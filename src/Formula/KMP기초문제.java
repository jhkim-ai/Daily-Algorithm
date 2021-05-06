package Formula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KMP기초문제 {

    static String str;
    static String subStr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        subStr = br.readLine();

        int ans = 0;
        int idx = 0;

        out:for(int i = 0 ; i < str.length(); ++i){
            // subStr(부분 문자열)의 시작이 str(문자열)과 같을 때, 시작
            if(str.charAt(i) == subStr.charAt(idx)){
                while(true){

                    // 현재까지 일치, 다음 탐색 진행
                    i++;
                    idx++;

                    // 문자열 같음
                    if(idx >= subStr.length()){
                        ans = 1;
                        break out;
                    }
                    // 부분문자열 탐색이 끝나기 전에 문자열의 길이 탐색이 끝났을 때 : 0
                    if(i >= str.length() && idx < subStr.length()){
                        break;
                    }

                    // 문자열이 다르다면 : 0
                    if(str.charAt(i) != subStr.charAt(idx)) {
                        idx = 0;
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
