package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Baekjoon2493 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Tower> s = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            Tower p = new Tower(i+1, Long.parseLong(st.nextToken()));
            while(!s.isEmpty()){
                if(s.peek().height >= p.height){
                    break;
                }
                s.pop();
            }
            if(s.isEmpty()){
                ans.add(0);
            }else{
                ans.add(s.peek().idx);
            }
            s.push(p);
        }
        for (int i = 0 ; i<ans.size(); i++){
            sb.append(ans.get(i)).append(" ");
        }
        System.out.println(sb);
    }

    static class Tower{
        int idx;
        long height;
        public Tower(int idx, long height){
            this.idx = idx;
            this.height = height;
        }
    }
}
