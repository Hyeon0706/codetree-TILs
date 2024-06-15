import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int[][] city;
	static boolean[][] visit;
	static ArrayList<Hospital> hospitals = new ArrayList<>();
	static int[] choose;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int ans = Integer.MAX_VALUE;
	static class Hospital implements Comparable<Hospital>{
		int r, c;
		int cnt;
		
		public Hospital(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
		@Override
		public int compareTo(Hospital o) {
			return this.cnt - o.cnt;
		}
	}

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		city = new int[n][n];
		choose = new int[m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j]==2) {
					hospitals.add(new Hospital(i, j, 0));
				}
			}
		}
		chooseHospital(0, 0);
		if(ans==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(ans);
		}
	}
	
	static void chooseHospital(int start, int cnt) {
		if(cnt==m) {
			killVirus();
			return;
		}
		for(int i=start; i<hospitals.size(); i++) {
			choose[cnt] = i;
			chooseHospital(i+1, cnt+1);
		}
	}
	
	static void killVirus() {
		int max = 0;
		visit = new boolean[n][n];
		PriorityQueue<Hospital> pq = new PriorityQueue<>();
		for(int i : choose) {
			Hospital hospital = hospitals.get(i);
			int r = hospital.r;
			int c = hospital.c;
			visit[r][c] = true;
			pq.offer(hospital);
		}
		while(pq.size()!=0) {
			Hospital cur = pq.poll();
			for(int d=0; d<4; d++) {
				int nr = cur.r+dr[d];
				int nc = cur.c+dc[d];
				if(nr>=0 && nc>=0 && nr<n && nc<n && !visit[nr][nc]) {
					if(city[nr][nc]==0) {
						visit[nr][nc] = true;
						pq.offer(new Hospital(nr, nc, cur.cnt+1));
						max = Math.max(max, cur.cnt+1);
					}else if(city[nr][nc]==2) {
						visit[nr][nc] = true;
						pq.offer(new Hospital(nr, nc, cur.cnt+1));
					}
				}
			}
		}
		if(check()) {
			ans = Math.min(ans, max);
		}
	}
	
	static boolean check() {
		for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visit[i][j] && city[i][j]==0){
                    return false;
                }
            }
        }
        return true;
	}
}