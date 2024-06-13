import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] board;
	static boolean[][] visit;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];
		visit = new boolean[n][m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				visit[i][j] = true;
				normal(i, j, 0, board[i][j]);
				special(i, j, 0, 0, board[i][j]);
				visit[i][j] = false;
			}
		}
		System.out.println(max);
	}
	
	static void normal(int r, int c, int cnt, int sum) {
		if(cnt==3) {
			max = Math.max(max, sum);
			return;
		}
		for(int d=0; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nc<0 || nr>=n || nc>=m || visit[nr][nc]) {
				continue;
			}
			visit[nr][nc] = true;
			normal(nr, nc, cnt+1, sum+board[nr][nc]);
			visit[nr][nc] = false;
		}
	}
	
	static void special(int r, int c, int start, int cnt, int sum) {
		if(cnt==3) {
			max = Math.max(max, sum);
			return;
		}
		for(int d=start; d<4; d++) {
			int nr = r+dr[d];
			int nc = c+dc[d];
			if(nr<0 || nc<0 || nr>=n || nc>=m){
                continue;
            }
			special(r, c, d+1, cnt+1, sum+board[nr][nc]);
		}
	}

}