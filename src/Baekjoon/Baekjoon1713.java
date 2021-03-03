package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Baekjoon1713 {

    static int N, P;
    static ArrayList<Voter> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());


        while (P-- > 0) {
            int num = Integer.parseInt(br.readLine());
            Voter vt = new Voter(num);

            if (list.size() == 0)
                list.add(vt);
            else {
                for (int i = 0; i <list.size(); i++){
                    if (list.get(i).num == vt.num){
                        list.get(i).cnt++;
                        list.get(i).time++;
                    }
                    else{
                        if(list.size() < N){
                            list.add(vt);
                        }
                        if(list.size() == N){

                        }
                    }
                }
            }
        }
    }

    static class Voter{
        public int num;
        public int cnt;
        public int time;

        public Voter(int num){
            this.num = num;
            this.cnt = 1;
            this.time = 1;
        }
    }
}
