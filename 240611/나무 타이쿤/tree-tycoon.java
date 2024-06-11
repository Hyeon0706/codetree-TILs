import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int m;
	static int[][] grid;
	static boolean[][] pre;
	static int[] dr = {0,-1,-1,-1,0,1,1,1};
	static int[] dc = {1,1,0,-1,-1,-1,0,1};
	static int[] ddr = {-1,-1,1,1};
    static int[] ddc = {-1,1,1,-1};
	static Queue<Medicine> medicines = new LinkedList<>();
	static class Medicine{
		int r, c;
		
		public Medicine(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		grid = new int[n][n];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		medicines.offer(new Medicine(n-1, 0));
		medicines.offer(new Medicine(n-1, 1));
		medicines.offer(new Medicine(n-2, 0));
		medicines.offer(new Medicine(n-2, 1));
		while(m-->0) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken())-1;
			int p = Integer.parseInt(st.nextToken());
			moveMedicine(d, p);
			searchDia();
			createMedicine();
		}
		System.out.println(calc());
	}
	
	static void moveMedicine(int d, int p) {
		p = p%n;
		pre = new boolean[n][n];
		int queueSize = medicines.size();
		while(queueSize-->0) {
			Medicine cur = medicines.poll();
			int nr = cur.r+(dr[d]*p);
            int nc = cur.c+(dc[d]*p);
            if(nr>=n){
                nr = nr-n;
            }
            if(nc>=n){
                nc = nc-n;
            }
            if(nr<0){
                nr = n+nr;
            }
            if(nc<0){
                nc = n+nc;
            }
            grid[nr][nc]++;
            medicines.offer(new Medicine(nr, nc));
            pre[nr][nc] = true;
		}
	}
	
	static void searchDia() {
		while(medicines.size()!=0) {
			Medicine cur = medicines.poll();
			int cnt = 0;
			for(int d=0; d<4; d++) {
				int nr = cur.r+ddr[d];
                int nc = cur.c+ddc[d];
                if(nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc]>0){
                    cnt++;
                }
			}
			grid[cur.r][cur.c]+=cnt;
		}
	}
	
	static void createMedicine() {
		for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]>1 && !pre[i][j]){
                    medicines.offer(new Medicine(i, j));
                    grid[i][j]-=2;
                }
            }
        }
	}

	static int calc(){
        int sum = 0;
        for(int[] r : grid){
            for(int c : r){
                sum+=c;
            }
        }
        return sum;
    }
}