package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ1963_소수경로 {

    private static String start;
    private static String end;
    private static boolean[] isNotPrime;
    private static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        isNotPrime = new boolean[10000];
        initPrime();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; ++t){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            start = st.nextToken();
            end = st.nextToken();
            set = new HashSet<>();

            int cnt = findPassword();
            if(cnt == -1){
                ans.append("Impossible");
            } else {
                ans.append(cnt);
            }
            ans.append("\n");
        }

        System.out.println(ans);
    }

    public static void initPrime(){
        int size = isNotPrime.length;
        for(int i = 2; i*i < size; ++i){
            for(int j = i*i; j < size; j += i){
                if(!isNotPrime[j]){
                    isNotPrime[j] = true;
                }
            }
        }
    }

    public static int findPassword(){
        Queue<String> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        q.offer(start);
        set.add(start);
        int cnt = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; ++i){
                String str = q.poll();
                if(str.equals(end)){
                    return cnt;
                }
                for(int idx = 0; idx < 4; ++idx){
                    for(int n = 0; n < 10; ++n){
                        if(idx == 0 && n == 0){
                            continue;
                        }
                        sb.setLength(0);
                        sb.append(str.substring(0, idx));
                        sb.append(n);
                        sb.append(str.substring(idx+1));
                        String tmp = sb.toString();
                        if(set.contains(tmp) || isNotPrime[Integer.parseInt(tmp)]){
                            continue;
                        }
                        set.add(tmp);
                        q.offer(tmp);
                    }
                }

            }
            ++cnt;
        }

        return -1;
    }
}
