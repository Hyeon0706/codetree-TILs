import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int r, c, k;
	static int[][] grid = new int[101][101];
	static int rowSize = 3;
	static int colSize = 3;
	static class Number implements Comparable<Number> {
        int number;
        int count;

        public Number(int n, int c) {
            this.number = n;
            this.count = c;
        }

        public int compareTo(Number o) {
            if (this.count > o.count) {
                return 1;
            } else if (this.count == o.count) {
                return this.number - o.number;
            } else {
                return -1;
            }
        }
    }

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for(int i=1; i<=3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=3; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int time=0; time<=100; time++) {
//			System.out.println(rowSize);
//			System.out.println(colSize);
			if(grid[r][c]==k) {
				System.out.println(time);
				return;
			}
			if(rowSize>=colSize) {
				for(int i=1; i<=rowSize; i++) {
					func1(i);
				}
			}else {
				for(int i=1; i<=colSize; i++) {
					func2(i);
				}
			}
//			print();
		}
		System.out.println(-1);
	}
	
	static void func1(int rowNum) { //행 정렬
		PriorityQueue<Number> pq = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=1; i<=colSize; i++) {
			int num = grid[rowNum][i]; 
			if(num==0) {
				continue;
			}
			if(map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			}else {
				map.put(num, 1);
			}
		}
		map.forEach((k, v) -> pq.offer(new Number(k, v)));
		int i = 1;
		while(pq.size()!=0) {
			Number cur = pq.poll();
			grid[rowNum][i++] = cur.number;
			grid[rowNum][i++] = cur.count;
		}
		colSize = Math.max(colSize, i);
		while(i<=99) {
			grid[rowNum][i++] = 0;
			grid[rowNum][i++] = 0;
		}
	}
	
	static void func2(int colNum) { //열 정렬
		PriorityQueue<Number> pq = new PriorityQueue<>();
		HashMap<Integer, Integer> map = new HashMap<>();
		for(int i=1; i<=rowSize; i++) {
			int num = grid[i][colNum]; 
			if(num==0) {
				continue;
			}
			if(map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			}else {
				map.put(num, 1);
			}
		}
		map.forEach((k, v) -> pq.offer(new Number(k, v)));
		int i = 1;
		while(pq.size()!=0) {
			Number cur = pq.poll();
			grid[i++][colNum] = cur.number;
			grid[i++][colNum] = cur.count;
		}
		rowSize = Math.max(rowSize, i);
		while(i<=99) {
			grid[i++][colNum] = 0;
			grid[i++][colNum] = 0;
		}
	}
	
	static void print() {
		for(int i=0; i<12; i++) {
			for(int j=0; j<12; j++) {
				System.out.print(String.format("%02d ", grid[i][j]));
			}
			System.out.println();
		}
		System.out.println();
	}

}