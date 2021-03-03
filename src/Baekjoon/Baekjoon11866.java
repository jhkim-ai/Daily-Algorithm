package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon11866 {
        public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 1; i < N+1; i++) {
            list.add(i);
        }
        bw.write("<");
        int cur_idx = 0;
        while(list.size() != 1){
            int t = K % list.size();

            if (cur_idx + t - 1 < list.size()){
                cur_idx += t - 1;
                if(cur_idx < 0) cur_idx = list.size() -1;
            }
            else{
                int x = list.size() - cur_idx;
                cur_idx = t - x - 1;
                // size - idx = x
                // N - x - 1 = 답                // 220 ms
            }
            if(cur_idx == list.size())
                cur_idx = 0;
            bw.write(Integer.toString(list.get(cur_idx))+", ");
            list.remove(cur_idx);
        }
        bw.write(Integer.toString(list.get(0)));
        bw.write(">");
        bw.flush();
    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int N = Integer.parseInt(st.nextToken());
//        int K = Integer.parseInt(st.nextToken());
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 1; i <= N; i++) {
//            list.add(i);
//        }
//        bw.write("<");
//        while(list.size() != 1){
//            for(int i = 0; i<K-1;i++){
//                list.add(list.get(0));     //  3 4 5 1 2
//                list.remove(list.get(0));
//            }
//            bw.write(Integer.toString(list.get(0)) + ", ");
//            list.remove(0);
//        }
//        bw.write(Integer.toString(list.get(0)) + ">");
//        bw.flush();
//    }
}
