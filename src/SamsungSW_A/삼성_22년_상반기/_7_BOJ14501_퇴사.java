package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class _7_BOJ14501_퇴사 {
	
	private static int N, ans;
	private static int[] time, pay;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		ans = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		time = new int[N+1];
		pay = new int[N+1];
		
		for(int i = 1; i <= N; ++i){
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			time[i] = Integer.parseInt(st.nextToken());
			pay[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= N; ++i){
			dfs(i, 0);
		}
		
		System.out.println(ans);
	}
	
	// Idea. day가 되는 날에 상담할지 안할지를 고르겠다.
	// +추가) 39번 Line에서 day가 N+1(퇴사일)이 되었다는 것은 상담을 할지 안할지 고른다는 말로
    //       퇴사 전날(N일)까지 상담을 마쳤다고 볼 수 있다.
	public static void dfs(int day, int sum){
        if (day > N + 1) {
            return;
        }
		
		if(day == N + 1) {
			ans = Math.max(ans, sum);
			return;
		}

        if (day <= N) {
            ans = Math.max(ans, sum);
        }
		
		dfs(day+time[day], sum + pay[day]); // 현재 day에 상담을 선택했을 때
		for(int i = day + 1; i <= N; ++i){  // 현재 day에 상담을 선택하지 않고 i일로 넘어갈 때 (단, i > day)
			dfs(i, sum);
		}
	}
}
