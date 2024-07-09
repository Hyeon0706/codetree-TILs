import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] grid;
    static boolean[][] visit;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        grid = new int[n][n];
        visit = new boolean[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==1 && !visit[i][j]){
                    findLinked(i, j);
                }
            }
        }
        sb.append(pq.size()).append('\n');
        while(pq.size()!=0){
            sb.append(pq.poll()).append('\n');
        }
        System.out.println(sb);
    }

    static void findLinked(int sr, int sc){
        int cnt = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sr, sc});
        visit[sr][sc] = true;
        while(queue.size()!=0){
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                if(nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc]==1 && !visit[nr][nc]){
                    visit[nr][nc] = true;
                    queue.offer(new int[] {nr, nc});
                    cnt++;
                }
            }
        }
        pq.offer(cnt);
    }
}