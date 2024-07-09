import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
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
        HashMap<Character, Integer> map;
        for(int num=x; num<=y; num++){
            map = new HashMap<>();
            char[] number = String.valueOf(num).toCharArray();
            for(char c : number){
                if(map.containsKey(c)){
                    map.put(c, map.get(c)+1);
                }else{
                    map.put(c, 1);
                }
            }
            if(map.size()!=2){
                continue;
            }
            HashSet<Character> keySet = new HashSet<>(map.keySet());
            for(char c : keySet){
                if(map.get(c)==1){
                    ans++;
                    break;
                }
            }
        }
        System.out.println(ans);
    }
}