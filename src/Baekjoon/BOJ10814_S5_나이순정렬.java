package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ10814_S5_나이순정렬 {
    public class Person{
        int age;
        String name;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[200001];
        String [][] str = new String[N][2];

        for (int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            str[i][0] = st.nextToken();
            str[i][1] = st.nextToken();
            arr[Integer.parseInt(str[i][0]) + 100000]++;
        }

        for(int i = 0; i<arr.length; i++){
            if (arr[i] != 0){
                for(int j=0;j<arr[i];j++){

                }
            }
        }


    }


//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st;
//
//        int N = Integer.parseInt(br.readLine());
//        String [][] str = new String[N][2];
//
//        for (int i = 0; i<N;i++){
//            st = new StringTokenizer(br.readLine());
//            str[i][0] = st.nextToken(); // 나이
//            str[i][1] = st.nextToken(); // 이름
//        }
//
//        Arrays.sort(str, new Comparator<String[]>() {
//            @Override
//            public int compare(String[] o1, String[] o2) {
//                return Integer.compare(Integer.parseInt(o1[0]), Integer.parseInt(o2[0]));
//            }
//        });
//
//        for (String[] s : str){
//            bw.write(s[0] + " " + s[1] + "\n");
//        }
//        bw.flush();
//    }
}
