package StepByStep._2021.day210626;

import java.util.*;
import java.io.*;

public class BOJ19942_다이어트 {

    private static int N, ans;
    private static String ansList;
    private static int[] criteria;
    private static int[][] nutrient;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        criteria = new int[4];
        nutrient = new int[N][5];
        ans = Integer.MAX_VALUE;
        ansList = "";

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; ++i) {
            criteria[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; ++j) {
                nutrient[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 부분집합
        subset();
        if(ansList.length() == 0)
            System.out.println("-1");
        else {
            System.out.println(ans);
            System.out.println(ansList);
        }
    }

    public static void subset() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < (1 << N); ++i) {
            list.clear();
            for (int j = 0; j < N; ++j) {
                if ((i & (1 << j)) == 0) continue;
                list.add(j);
            }

            StringBuilder sb = new StringBuilder();
            int sumProtein = 0;
            int sumFat = 0;
            int sumCarbohydrate = 0;
            int sumVitamin = 0;
            int sumCost = 0;

            for(int k = 0; k < list.size(); ++k){
                int idx = list.get(k);
                sumProtein += nutrient[idx][0];
                sumFat += nutrient[idx][1];
                sumCarbohydrate += nutrient[idx][2];
                sumVitamin += nutrient[idx][3];
                sumCost += nutrient[idx][4];
            }

            if(sumProtein < criteria[0]) continue;
            if(sumFat < criteria[1]) continue;
            if(sumCarbohydrate < criteria[2]) continue;
            if(sumVitamin < criteria[3]) continue;

            if(ans > sumCost) {
                ans = Math.min(ans, sumCost);
                for(int num : list)
                    sb.append(num+1).append(" ");
                ansList = sb.toString();
            }
            else if(ans == sumCost){
                for(int num : list)
                    sb.append(num+1).append(" ");
                String tmp = sb.toString();

                if(ansList.compareTo(tmp) > 0) ansList = tmp;
            }
        }
    }
}
