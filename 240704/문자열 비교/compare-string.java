import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static HashSet<String> set = new HashSet<>();
    static int cnt;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        while(n-->0){
            set.add(br.readLine());
        }
        while(m-->0){
            String input = br.readLine();
            if(set.contains(input)){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}