import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int x, y;
    static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        HashSet<Character> set;
        for(int num=x; num<=y; num++){
            set = new HashSet<>();
            char[] number = String.valueOf(num).toCharArray();
            for(char c : number){
                set.add(c);
            }
            if(set.size()==2){
                ans++;
            }
            set.clear();
        }
        System.out.println(ans);
    }
}