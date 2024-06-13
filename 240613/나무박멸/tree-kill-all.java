import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	/**
	 * 1. 상하좌우 나무의 개수만큼 나무가 성장
	 * 2. 기존의 나무들은 상하좌우 빈칸에 번식
	 * 		2-1. 이 때 해당 나무/가능한 칸, 나머지 숫자는 버림, 겹치면 합침
	 * 3. 가장 많은 나무를 죽일 수 있는 칸을 선택해서 제초제 뿌림, 나무가 있는 칸에 뿌려야 함
	 * 		3-1. 이 때, 가다가 벽이나 나무가 없는 칸 만나면 제초제 멈춤
	 * 		3-2. 제초제가 뿌려진 칸에는 c년만큼 제초제가 남아있다가 c+1년에 없어짐
	 * 4. 만약 제거할 수 있는 나무의 수가 같다면 좌상단에 뿌림
	 * 5. m년동안 박멸한 나무의 수 구하기
	 * */
	
	static int N, M, K, C;
	static int[][] grid;
	static Queue<Tree> trees = new LinkedList<>();
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int[] ddr = {-1,1,1,-1};
	static int[] ddc = {1,1,-1,-1};
	static int killCnt;
	static class Tree{
		int r, c;
		int cnt;
		
		public Tree(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		grid = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(M-->0) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(grid[i][j]>0) {
						trees.offer(new Tree(i, j, grid[i][j]));
					}
				}
			}
			cleanMedicine();
//			System.out.println("초기상태");
//			print();
			grow();
//			System.out.println("성장");
//			print();
			breeding();
//			System.out.println("번식");
//			print();
			if(!chooseKillPoint()) {
				break;
			}
//			System.out.println("제초");
//			print();
		}
		System.out.println(killCnt);
	}
	
	static void cleanMedicine() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(grid[i][j]<-2) {
					grid[i][j]++;
					if(grid[i][j]==-2) {
						grid[i][j] = 0;
					}
				}
			}
		}
	}
	
	static void grow() {
		int queueSize = trees.size();
		for(int i=0; i<queueSize; i++) {
			Tree cur = trees.poll();
			int sum = 0;
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nr>=0 && nc>=0 && nr<N && nc<N && grid[nr][nc]>0) {
					sum++;
				}
			}
			trees.offer(new Tree(cur.r, cur.c, cur.cnt+sum));
		}
		for(Tree tree : trees) {
			grid[tree.r][tree.c] = tree.cnt;
		}
	}
	
	static void breeding() {
		int[][] temp = new int[N][N];
		int queueSize = trees.size();
		for(int i=0; i<queueSize; i++) {
			Tree cur = trees.poll();
			int blank = 0;
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nr>=0 && nc>=0 && nr<N && nc<N && grid[nr][nc]==0) {
					blank++;
				}
			}
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nr>=0 && nc>=0 && nr<N && nc<N && grid[nr][nc]==0) {
					temp[nr][nc]+=cur.cnt/blank;
				}
			}
		}
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(temp[i][j]!=0) {
					trees.offer(new Tree(i, j, temp[i][j]));
				}
			}
		}
		while(trees.size()!=0) {
			Tree tree = trees.poll();
			grid[tree.r][tree.c] = tree.cnt;
		}
	}
	
	static boolean chooseKillPoint() {
		int maxR = -1;
		int maxC = -1;
		int maxCnt = -1;
		for(int i=N-1; i>=0; i--) {
			for(int j=N-1; j>=0; j--) {
				if(grid[i][j]>0) {
					int sum = calc(i, j);
					if(sum>=maxCnt) {
						maxR = i;
						maxC = j;
						maxCnt = sum;
					}
				}
			}
		}
//		System.out.println(maxR + " : " + maxC + " : " + maxCnt);
		if(maxR==-1 || maxC==-1) {
			return false;
		}
		killTree(maxR, maxC);
		killCnt+=maxCnt;
		return true;
	}
	
	static int calc(int r, int c) {
		int sum = grid[r][c];
		for(int d=0; d<4; d++) {
			for(int i=1; i<=K; i++) {
				int nr = r+(ddr[d]*i);
				int nc = c+(ddc[d]*i);
				if(nr<0 || nc<0 || nr>=N || nc>=N || grid[nr][nc]<1) {
					break;
				}
				sum+=grid[nr][nc];
			}
		}
		return sum;
	}
	
	static void killTree(int r, int c) {
		grid[r][c] = C*-1-3;
		for(int d=0; d<4; d++) {
			for(int i=1; i<=K; i++) {
				int nr = r+(ddr[d]*i);
				int nc = c+(ddc[d]*i);
				if(nr>=0 && nc>=0 && nr<N && nc<N){
					if(grid[nr][nc]==-1) {
						break;
					}else if(grid[nr][nc]<-1 || grid[nr][nc]==0) {
						grid[nr][nc] = C*-1-3;
						break;
					}else if(grid[nr][nc]>0) {
						grid[nr][nc] = C*-1-3;
					}
				}
			}
		}
	}
	
	static void print() {
		for(int[] r : grid) {
			for(int c : r) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}

}