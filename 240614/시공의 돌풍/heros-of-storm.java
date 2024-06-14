import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int r,c,t;
	static int[][] room;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[] squall = new int[2];
	static int remain;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		room = new int[r][c];
		int ac = 0;
		for(int i=0; i<r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<c; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				if(room[i][j]==-1) {
					squall[ac] = i;
					ac++;
				}
			}
		}
		for(int time=0; time<t; time++) {
			spread();
			up();
			down();
		}
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				remain+=room[i][j];
			}
		}
		remain+=2;
		sb.append(remain);
		System.out.println(sb);
	}
	
	
	
	static void spread() {
		int[][] copyRoom = new int[r][c];
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				int meesae = room[i][j];
				if(meesae>=1) {
					int count = 0;
					for(int d=0; d<4; d++) {
						int nr = i+dr[d];
						int nc = j+dc[d];
						if(nr>=0 && nr<r && nc>=0 && nc<c && room[nr][nc]!=-1) {
							copyRoom[nr][nc] += room[i][j]/5;
							count++;
						}
					}
					copyRoom[i][j] += room[i][j]-(room[i][j]/5) * count;
				}
			}
		}
		for(int i=0; i<r; i++) {
			for(int j=0; j<c; j++) {
				room[i][j] = copyRoom[i][j];
			}
		}
		room[squall[0]][0] = -1;
		room[squall[1]][0] = -1;
	}
	
	static void up() {
		int start = squall[0];
		for(int i=start-1; i>=1; i--) {
			room[i][0] = room[i-1][0];
		}
		for(int i=0; i<c-1; i++) {
			room[0][i] = room[0][i+1];
		}
		for(int i=0; i<start; i++) {
			room[i][c-1] = room[i+1][c-1];
		}
		for(int i=c-1; i>=2; i--) {
			room[start][i] = room[start][i-1];
		}
		room[start][1] = 0;
	}
	static void down() {
		int start = squall[1];
		for(int i=start+1; i<r-1; i++) {
			room[i][0] = room[i+1][0];
		}
		for(int i=0; i<c-1; i++) {
			room[r-1][i] = room[r-1][i+1];
		}
		for(int i=r-1; i>start; i--) {
			room[i][c-1] = room[i-1][c-1];
		}
		for(int i=c-1; i>=2; i--) {
			room[start][i] = room[start][i-1];
		}
		room[start][1] = 0;
	}

}