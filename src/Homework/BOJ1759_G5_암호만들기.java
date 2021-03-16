package Homework;

import java.io.*;
import java.util.*;

public class BOJ1759_G5_암호만들기 {

    static int L, C;
    static char[] arr;
    static StringBuilder sb = new StringBuilder();
    static List<String> list;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[C];
        list = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        // --------- 알고리즘 시작 --------- //

        // 1. 정렬
        Arrays.sort(arr);

        // 2. 조합
        comb(L, new char[L], 0);

        // 3. 출력
        for(String s : list){
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }

    static void comb(int cnt, char[] selected, int startIdx){
        if(cnt == 0){
            int vol = 0;
            int cons = 0;

            // 2-1
            // 제약 사항 분류기 (필수: 자음 2개 이상, 모음 1개 이상
            for (int i = 0; i < L; i++) {
                // 자음, 모음 check
                if(isVowel(selected[i]))
                    vol++;
                else
                    cons++;
            }
            
            if( 1<= vol && 2<= cons){
                // 2-2 검수된 암호문은 출력을 위해 list로 저장
                list.add(String.valueOf(selected));
            }
            return;
        }

        // 2-0 조합공식
        for (int i = startIdx; i < C; i++) {
            selected[selected.length - cnt] = arr[i];
            comb(cnt-1, selected, i+1);
        }
    }

    static String input = "4 6\n" +
            "a t c i s w";

    // 자음, 모음 분류
    static boolean isVowel(char c){
        if(c == 'a' || c == 'e' || c == 'i' || c=='o' || c=='u'){
            return true;
        }
        return false;
    }
}
