package StepByStep._2021.day210416;

import java.util.*;
import java.io.*;

public class BOJ2617_구슬찾기 {

    static ArrayList<Integer>[] inputHeavy;
    static ArrayList<Integer>[] inputLight;
    static Set<Integer>[] ansHeavy;
    static Set<Integer>[] ansLight;
    static List<Integer> nextList;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        inputHeavy = new ArrayList[N+1];
        inputLight = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            inputHeavy[i] = new ArrayList<Integer>();
            inputLight[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int heavy = Integer.parseInt(st.nextToken());
            int light = Integer.parseInt(st.nextToken());
            inputHeavy[heavy].add(light);   // index보다 가벼운 것
            inputLight[light].add(heavy);   // index보다 무거운 것
        }

        ansHeavy = new HashSet[N+1];
        ansLight = new HashSet[N+1];
        for (int i = 0; i < N+1; i++) {
            ansHeavy[i] = new HashSet<Integer>();
            ansLight[i] = new HashSet<Integer>();
        }

        for (int i = 1; i < N+1; i++) {
            for(int num : inputHeavy[i]){
                nextList = new ArrayList<>();
                dfs(num, new ArrayList<>());
            }
        }
    }

    static void dfs(int now, ArrayList<Integer> list){
        list.add(now);

        for(int next : inputHeavy[now]){
            if (next == 0) {
                for (int element : nextList)
                    ansHeavy[now].add(element);
                return;
            }
            dfs(next, list);
        }
    }
}
