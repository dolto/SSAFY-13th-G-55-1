package tmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main  {


	public static void main(String [] args) throws Exception {
		// 1. input data 받아오기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		
		// 	- 가로, 세로 크기 stringTokenizer로 공백 기준으로 나눠서 저장
		int column = Integer.parseInt(st.nextToken());
		int  row=Integer.parseInt(st.nextToken());
		
		//  - 상점 개수 저장
		int storeNum = Integer.parseInt(br.readLine());
		
		//  - 상점 위치를 넣을 배열 생성 크기는 row:상점 개수, column : 2 -> [방향(북,서,남,동), 위치]
		int [][] storeLoc = new int[storeNum+1][2];
		
		//  - 상점 위치 저장 StringTokenizer로 공백 기준으로 나눠서 저장 for (int n<상점 개수)
		for(int i=0; i<=storeNum; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			switch(l) {
			case 1:
				storeLoc[i][0] = 0;
				storeLoc[i][1] = Integer.parseInt(st.nextToken());
				break;
			case 2:
				storeLoc[i][0] = row;
				storeLoc[i][1] = Integer.parseInt(st.nextToken());
				break;
			case 3:
				storeLoc[i][0] = Integer.parseInt(st.nextToken());
				storeLoc[i][1] = 0;
				break;
			case 4:
				storeLoc[i][0] = Integer.parseInt(st.nextToken());
				storeLoc[i][1] = column;

			}

		}

		

		//  - 주인공 위치 저장
		int [] person = new int[2];
		
		
		person[0] = storeLoc[storeNum][0];
		person[1] = storeLoc[storeNum][1];

		// 동서남북으로 이동
		int [][] move = {{0,1},{0,-1},{1,0},{-1,0}};
		
		// visit check

		//  - 최소 길이를 저장할 변수 생성 int sum
		int sum = 0;
		// 2. 상점 배열을 하나씩 읽는다. for() 
		for(int i=0; i<storeNum; i++) {
			boolean [][] grid = new boolean[row+1][column+1];
			
			// BFS
			// deque 생성
			Deque<int[]> d = new ArrayDeque<>();
			
			d.offer(new int[]{person[0], person[1],0});
			grid[person[0]][person[1]] = true;
			 while(!d.isEmpty()) {
				 int [] cur = d.pollFirst();

				 int cur_x = cur[0];
				 int cur_y = cur[1];
				 int length = cur[2];
				 if (cur_x==storeLoc[i][0] && cur_y==storeLoc[i][1]) {
					 sum+= length;
					 break;
				 }
				 
				 for(int j=0; j<4; j++) {
					 int n_x = cur_x+ move[j][0];
					 int n_y = cur_y+ move[j][1];
					 
					 if(((n_x == 0 || n_x == row)&& (n_y>=0 && n_y<=column ))|| ((n_y ==0 ||n_y ==column) && (n_x>=0 && n_x<=row))) {
						 if(grid[n_x][n_y]== false) {

							 grid[n_x][n_y] = true;
							 d.offer(new int[] {n_x, n_y, length+1});
						 }
					 }
				 }
				 
			 }
			 

		}
		
		System.out.println(sum);

		//  - sum에 각 길이를 더하여 반환한다. 
		
		

		
	}
	

}
