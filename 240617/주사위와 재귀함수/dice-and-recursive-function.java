import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int x, y;
    static StringBuilder sb = new StringBuilder();
    static int[] choose;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        choose = new int[x];
        com(0);
        System.out.println(sb);
    }

    static void com(int cnt){
        if(cnt==x){
            int sum = 0;
            for(int i : choose){
                sum+=i;
            }
            if(sum==y){
                for(int i : choose){
                    sb.append(i).append(' ');
                }
                sb.append('\n');
            }
            return;
        }
        for(int i=1; i<=6; i++){
            choose[cnt] = i;
            com(cnt+1);
        }
    }
}