package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10409_서버 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        int sum = 0;
        int ans = 0;
        st = new StringTokenizer(br.readLine(), " ");
        while(n-- > 0){
            int time = Integer.parseInt(st.nextToken());
            if(sum + time <= t){
                sum += time;
                ans++;
            } else break;
        }

        System.out.println(ans);
    }
}
