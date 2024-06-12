import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int[][] room;
    static int[][] students;
    static HashMap<Integer, int[]> seating = new HashMap<>();
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        room = new int[n][n];
        students = new int[(n*n)+1][5];
        for(int i=1; i<=n*n; i++){
            st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            students[i][0] = student;
            for(int j=1; j<5; j++){
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        staffing();
        calc();
    }

    static void staffing(){
        for(int s=1; s<=n*n; s++){
            int[] info = students[s];
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=1; i<5; i++){
                if(seating.containsKey(info[i])){
                    list.add(info[i]);
                }
            }
            if(list.size()==0){
                findBlank(s);
            }else{
                findLike(s);
            }
        }
    }

    static void findBlank(int student){
        int cnt = 0;
        ArrayList<int[]> remain = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(room[i][j]==0){
                    int sum = 0;
                    for(int d=0; d<4; d++){
                        int nr = i+dr[d];
                        int nc = j+dc[d];
                        if(nr>=0 && nc>=0 && nr<n && nc<n && room[nr][nc]==0){
                            sum++;
                        }
                    }
                    cnt = Math.max(cnt, sum);
                    if(sum==4){
                        room[i][j] = students[student][0];
                        seating.put(students[student][0], new int[] {i, j});
                        return;
                    }
                    remain.add(new int[] {i, j, sum});
                }

            }
        }
        for(int[] info : remain){
            if(info[2]==cnt){
                room[info[0]][info[1]] = students[student][0];
                seating.put(students[student][0], new int[] {info[0], info[1]});
                return;
            }
        }
    }

    static void findLike(int student){
        int blankMax = 0;
        int likeMax = 0;
        ArrayList<int[]> remain = new ArrayList<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(room[i][j]==0){
                    int blankSum = 0;
                    int likeSum = 0;
                    for(int d=0; d<4; d++){
                        int nr = i+dr[d];
                        int nc = j+dc[d];
                        if(nr>=0 && nc>=0 && nr<n && nc<n){
                            if(room[nr][nc]==0){
                                blankSum++;
                            }
                            for(int s=1; s<5; s++){
                                if(students[student][s]==room[nr][nc]){
                                    likeSum++;
                                }
                            }
                        }
                    }
                    blankMax = Math.max(blankMax, blankSum);
                    likeMax = Math.max(likeMax, likeSum);
                    remain.add(new int[] {i, j, likeSum, blankSum});
                }
            }
        }
        if(likeMax==1){
            int blank = 0;
            for(int[] info : remain){
                if(info[2]==1){
                    blank = Math.max(blank, info[3]);
                }
            }
            for(int[] info : remain){
                if(info[3]==blank && info[2]==1){
                    room[info[0]][info[1]] = students[student][0];
                    seating.put(students[student][0], new int[] {info[0], info[1]});
                    return;
                }
            }
        }else if(likeMax>1){
            int blank = 0;
            for(int[] info : remain){
                if(info[2]==likeMax){
                    blank = Math.max(blank, info[3]);
                }
            }
            for(int[] info : remain){
                if(info[3]==blank && info[2]==likeMax){
                    room[info[0]][info[1]] = students[student][0];
                    seating.put(students[student][0], new int[] {info[0], info[1]});
                    return;
                }
            }
        }else{
            for(int[] info : remain){
                if(info[3]==blankMax){
                    room[info[0]][info[1]] = students[student][0];
                    seating.put(students[student][0], new int[] {info[0], info[1]});
                    return;
                }
            }
        }
    }

    static void calc(){
        int sum = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int likeCnt = 0;
                int student = 0;
                for(int s=1; s<=n*n; s++){
                    if(room[i][j]==students[s][0]){
                        student = s;
                        break;
                    }
                }
                for(int d=0; d<4; d++){
                    int nr = i+dr[d];
                    int nc = j+dc[d];
                    if(nr>=0 && nc>=0 && nr<n && nc<n){
                        for(int s=1; s<5; s++){
                            if(students[student][s]==room[nr][nc]){
                                likeCnt++;
                            }
                        }
                    }
                }
                if(likeCnt==1){
                    sum+=1;
                }else if(likeCnt==2){
                    sum+=10;
                }else if(likeCnt==3){
                    sum+=100;
                }else if(likeCnt==4){
                    sum+=1000;
                }
            }
        }
        System.out.println(sum);
    }
}