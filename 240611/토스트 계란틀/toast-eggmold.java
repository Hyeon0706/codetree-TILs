import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int L;
    static int R;

    static int[][] map;
    static int[][] before;
    static boolean[][] check;

    static Queue<int[]> queue = new LinkedList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int cnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        check = new boolean[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 시작
        while(true){
            before = new int[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    before[i][j] = map[i][j];
                }
            }
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    //방문한 적 없는 칸이면
                    if(!check[i][j]){
                        queue.offer(new int[] {i,j});
                        check[i][j] = true;
                        open();
                    }
                }
            }
            check = new boolean[n][n];
            if(finish()){
                break;
            }
            cnt++;


        }
        System.out.println(cnt);
    }

    static void open(){
        Queue<int[]> location = new LinkedList<>();
        int sum = 0;
        while(queue.size()!=0){
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];
            location.offer(new int[] {r,c});
            sum+=map[r][c];
            for(int d=0; d<4; d++){
                int nr = r + dx[d];
                int nc = c + dy[d];
                if(nr<n && nc<n && nr>=0 && nc>=0 && !check[nr][nc] && Math.abs(map[r][c]-map[nr][nc])>=L && Math.abs(map[r][c]-map[nr][nc])<=R){
                    check[nr][nc] = true;
                    queue.offer(new int[] {nr,nc});
                }
            }
        }
        if(location.size()!=1){
            int div = sum/location.size();
            while(location.size()!=0){
                int[] current = location.poll();
                map[current[0]][current[1]] = div;
            }
        }

    }

    static boolean finish(){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j]!=before[i][j]){
                    return false;
                }
            }
        }
        return true;

    }
}