import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static String[] first = new String[8];
	static String[] second = new String[8];
	static String[] third = new String[8];
	static String[] fourth = new String[8];
	static int k;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		first = br.readLine().split("");
		second = br.readLine().split("");
		third = br.readLine().split("");
		fourth = br.readLine().split("");
		k = Integer.parseInt(br.readLine());
		while(k-->0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			switch (n) {
			case 1:
				turn1(d, 1);
				break;
			case 2:
				turn2(d, 2);
				break;
			case 3:
				turn3(d, 3);
				break;
			case 4:
				turn4(d, 4);
				
			}
		}
		int sum = (Integer.parseInt(first[0]) * 1) + (Integer.parseInt(second[0]) * 2) + (Integer.parseInt(third[0]) * 4) + (Integer.parseInt(fourth[0]) * 8);
		System.out.println(sum);
	}
	
	static void turn1(int d, int pre) {
//		System.out.println("turn1 to " + d);
		String two = first[2];
		if(d==1) {
			String temp = first[7];
			for(int i=7; i>0; i--) {
				first[i] = first[i-1];
			}
			first[0] = temp;
			if(!two.equals(second[6]) && pre!=2) {
				turn2(-1, 1);
			}
		}else {
			String temp = first[0];
			for(int i=0; i<7; i++) {
				first[i] = first[i+1];
			}
			first[7] = temp;
			if(!two.equals(second[6]) && pre!=2) {
				turn2(1, 1);
			}
		}
	}
	
	static void turn2(int d, int pre) {
//		System.out.println("turn2 to " + d);
		String two = second[2];
		String six = second[6];
		if(d==1) {
			String temp = second[7];
			for(int i=7; i>0; i--) {
				second[i] = second[i-1];
			}
			second[0] = temp;
			if(!two.equals(third[6]) && pre!=3) {
				turn3(-1, 2);
			}else if(!six.equals(first[2]) && pre!=1) {
				turn1(-1, 2);
			}
		}else {
			String temp = second[0];
			for(int i=0; i<7; i++) {
				second[i] = second[i+1];
			}
			second[7] = temp;
			if(!two.equals(third[6]) && pre!=3) {
				turn3(1, 2);
			}else if(!six.equals(first[2]) && pre!=1) {
				turn1(1, 2);
			}
		}
	}

	static void turn3(int d, int pre) {
//		System.out.println("turn3 to " + d);
		String two = third[2];
		String six = third[6];
		if(d==1) {
			String temp = third[7];
			for(int i=7; i>0; i--) {
				third[i] = third[i-1];
			}
			third[0] = temp;
			if(!two.equals(fourth[6]) && pre!=4) {
				turn4(-1, 3);
			}else if(!six.equals(second[2]) && pre!=2) {
				turn2(-1, 3);
			}
		}else {
			String temp = third[0];
			for(int i=0; i<7; i++) {
				third[i] = third[i+1];
			}
			third[7] = temp;
			if(!two.equals(fourth[6]) && pre!=4) {
				turn4(1, 3);
			}else if(!six.equals(second[2]) && pre!=2) {
				turn2(1, 3);
			}
		}
	}

	static void turn4(int d, int pre) {
//		System.out.println("turn4 to " + d);
		String six = fourth[6];
		if(d==1) {
			String temp = fourth[7];
			for(int i=7; i>0; i--) {
				fourth[i] = fourth[i-1];
			}
			fourth[0] = temp;
			if(!six.equals(third[2]) && pre!=3) {
				turn3(-1, 4);
			}
		}else {
			String temp = fourth[0];
			for(int i=0; i<7; i++) {
				fourth[i] = fourth[i+1];
			}
			fourth[7] = temp;
			if(!six.equals(third[2]) && pre!=3) {
				turn3(1, 4);
			}
		}
	}

}