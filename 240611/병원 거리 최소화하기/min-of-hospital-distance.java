import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m;
	static int min = Integer.MAX_VALUE;
	static ArrayList<int[]> person = new ArrayList<>();
	static ArrayList<int[]> hospital = new ArrayList<>();
	static int[] choose;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		choose = new int[m];
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				int value = Integer.parseInt(st.nextToken());
				if(value==1) {
					person.add(new int[] {i, j});
				}else if(value==2) {
					hospital.add(new int[] {i, j});
				}
			}
		}
		chooseHospital(0, 0);
		System.out.println(min);
	}
	
	static void chooseHospital(int start, int cnt) {
		if(cnt==m) {
			calcDis();
			return;
		}
		for(int i=start; i<hospital.size(); i++) {
			choose[cnt] = i;
			chooseHospital(i+1, cnt+1);
		}
	}
	
	static void calcDis() {
		int sum = 0;
		int[] minDis = new int[person.size()];
		Arrays.fill(minDis, 100);
		for(int i : choose) {
			int[] curHospital = hospital.get(i);
			for(int j=0; j<person.size(); j++) {
				int[] curPerson = person.get(j);
				minDis[j] = Math.min(minDis[j], Math.abs(curHospital[0]-curPerson[0]) + Math.abs(curHospital[1]-curPerson[1]));
			}
		}
		for(int i : minDis) {
			sum+=i;
		}
		min = Math.min(min, sum);
	}

}