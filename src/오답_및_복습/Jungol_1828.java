package 오답_및_복습;

import java.io.*;
import java.util.*;

// 냉장고 문제
public class Jungol_1828 {

    static int N;
    static Some[] arr;
    static int ans = 1;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        N = Integer.parseInt(br.readLine());
        arr = new Some[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Some(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr);

        // 1st. 최대 온도(y)를 기준으로 정렬
        // 2nd. 최대 온도(y)가 같을 시, 최저 온도(x)로 정렬

        // Why? 최저 온도(최솟값)으로 정렬을 하면 안될까?
        // 정렬 시, 하나의 기준(base)안에 최댓값과 최솟값이 공존할 수 있다.
        // 따라서, ( end < arr[i].start ) 로 알고리즘 설정 시 Error 가 날 수 있다.

        // ex. ( 0 10 )
        //     ( 0  2 )
        //     ( 0  4 )
        //     ( 6  20) 을 반례로 들 수 있다.

        int end = arr[0].end;
        for (int i = 1; i < arr.length; i++) {
            if(end < arr[i].start){
                end = arr[i].end;
                ans++;
            }
        }
        System.out.println(ans);
    }

    static class Some implements Comparable<Some>{
        int start;
        int end;

        public Some(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Some o) {
            return Integer.compare(this.end, o.end);
        }

        public String toString(){
            return "(" + start + ", " + end +")";
        }
    }

    static String input = "4\n" +
            "-15 5\n" +
            "-10 36\n" +
            "10 73\n" +
            "27 44";
}
