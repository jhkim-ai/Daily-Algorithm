// https://www.acmicpc.net/problem/1600
// 말이 되고픈 원숭이
// bfs
package 오답_및_복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 조만기 풀이
public class BOJ_1600_말이되고픈원숭이 {

	static class INFO{
		int x, y, c, dis;
		INFO(int x, int y, int c, int dis){
			this.x = x;
			this.y = y;
			this.c = c;
			this.dis = dis;
		}
	}
	
	static final int[] dx = {0,0,1,-1,-1,-2,-2,-1,1,2,2,1};
	static final int[] dy = {1,-1,0,0,-2,-1,1,2,2,1,-1,-2};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[][] map = new int[H][W];
		for(int i = 0; i<H; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<W; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[][][] visit = new boolean[H][W][K+1];
		Queue<INFO> q = new LinkedList<>();
		
		Arrays.fill(visit[0][0], true);
		q.offer(new INFO(0,0,0,0));
		
		while(!q.isEmpty()) {
			int x = q.peek().x;
			int y = q.peek().y;
			int c = q.peek().c;
			int dis = q.poll().dis;
			
			
			if(x == H-1 && y == W-1) {
				System.out.println(dis);
				return;
			}
			for(int i = 0; i<4; ++i) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||ny<0||nx>=H||ny>=W||map[nx][ny]==1||visit[nx][ny][c]) continue;
				visit[nx][ny][c] = true;
				q.offer(new INFO(nx, ny, c, dis+1));
			}
			
			if(c==K) continue;
			for(int i = 4; i<12; ++i) {
				int nx = x+dx[i];
				int ny = y+dy[i];
				if(nx<0||ny<0||nx>=H||ny>=W||map[nx][ny]==1||visit[nx][ny][c+1]) continue;
				visit[nx][ny][c+1] = true;
				q.offer(new INFO(nx, ny, c+1, dis+1));
			}
		}
		
		System.out.println(-1);
	}

}