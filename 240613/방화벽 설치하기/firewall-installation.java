import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] area;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int max = Integer.MIN_VALUE;
	static ArrayList<Fire> start = new ArrayList<>();
	static class Fire{
		int r, c;
		
		public Fire(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		area = new int[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				area[i][j] = Integer.parseInt(st.nextToken());
				if(area[i][j]==2) {
					start.add(new Fire(i, j));
				}
			}
		}
		choose(0);
		System.out.println(max);
	}
	
	static void choose(int cnt) {
		if(cnt==3) {
			int[][] copy = new int[n][m];
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					copy[i][j] = area[i][j];
				}
			}
			spread(copy);
			calcSafe(copy);
			return;
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				if(area[i][j]==0) {
					area[i][j] = 1;
					choose(cnt+1);
					area[i][j] = 0;
				}
			}
		}
	}
	
	static void spread(int[][] copy) {
		Queue<Fire> fires = new LinkedList<>();
		for(Fire f : start) {
			fires.offer(f);
		}
		while(fires.size()!=0) {
			Fire cur = fires.poll();
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nr>=0 && nc>=0 && nr<n && nc<m && copy[nr][nc]==0) {
					copy[nr][nc] = 2;
					fires.offer(new Fire(nr, nc));
				}
			}
		}
	}
	
	static void calcSafe(int[][] copy) {
		int sum = 0;
		for(int[] r : copy) {
			for(int c : r) {
				if(c==0) {
					sum++;
				}
			}
		}
		max = Math.max(max, sum);
	}

}