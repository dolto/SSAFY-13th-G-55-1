package tmp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		// 아이디어 :  
		// 	사각형의 꼭지점을 통해 해당 사각형이 가지는 x값의 범위와 y값의 범위를 알 수 있다.
		//	두 사각형의 x값의 범위와 y값의 범위를 비교한다. 
		// 	두 개의 사각형을 비교하여  
		// 		겹치는 x값의 범위와 y값의 범위가 2 이상이면 공통 부분은 '사각형'이다.
		//		겹치는 (x값의 범위가 2 이상이고 y값은 1)이거나 (x값의 범위가 1이고 y값은 2이상이면) 공통 부분은 '선분'이다.
		//		겹치는 x값의 범위와 y값의 범위가 1이면 공통 부분은 '점선'이다.
		// 		겹치는 것이 없다면 공통 부분은 '없다'
		
		// input data 받기 
		// 첫번째 사각형의 꼭지점 값 받기 
		// 두번째 사각형의 꼭지점 값 받기
		// 예를 들어 2 <= x1 <= 5, 3 <= x2 <=8 일 때 첫번째  사각형을 기준으로 두번째 사각형의 시작점이 들어갈때 
		// 2 <= x1 <= 5에 3을 넣을 수 있는 지 확인,
		// 2 <= x1 <= 5에 8을 넣을 수 있는 지 확인,
		// 두 개 모두 들어갈 수 있다면 겹치는 부분은 2 이상일 경우 공통 부분은 사각형
		// 1 <= x1 <= 3, 0 <= x2 <=6일 경우에 첫번째 사각형을 기준으로 하면 위의 방법을 쓸 수 없다. 
		// 그래서 두 사각형의 꼭지점의 수가 더 적은 사각형을 기준 사각형으로 지정한다. 
		// y값의 범위도 위와 동일하게 한다. 
		
		System.setIn(new FileInputStream("rsc/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		

		for(int i=0; i<4; i++) {
			int resultX=-1;
			int resultY=-1;
			String result = null;
			st = new StringTokenizer(br.readLine());
			int [][] firstRec = new int[2][2];
			
			
			
			
			for(int j=0; j<2; j++) {
				firstRec[j][0] = Integer.parseInt(st.nextToken());
				firstRec[j][1] = Integer.parseInt(st.nextToken());
			}
			
			
			
			int [][] secondRec = new int[2][2];
			for(int j=0; j<2; j++) {
				secondRec[j][0] = Integer.parseInt(st.nextToken());
				secondRec[j][1] = Integer.parseInt(st.nextToken());
			}
			
			int [][] baseX = firstRec;
			int [][] baseY = firstRec;
			
			int [][] compareX = secondRec;
			int [][] compareY = secondRec;
			
			if(firstRec[0][0]>secondRec[0][0]) {
				baseX = secondRec;
				compareX = firstRec;
			}
			
			if(firstRec[0][1]>secondRec[0][1]) {
				baseY = secondRec;
				compareY = firstRec;
			}
			

			
			//x 값 범위 비교 
			if(baseX[1][0] >=compareX[0][0]) {
				resultX = baseX[1][0]- compareX[0][0];
			}
			
			//y 값 범위 비교
			if(baseY[1][1] >=compareY[0][1]) {
				resultY = baseY[1][1] -compareY[0][1];
			}
			
			
			if(resultX>=0 && resultY>=0) {
				if(resultX>0 && resultY>0) {
					result="a";
				}else if(resultX==0 && resultY==0) {
					result="c";
				}else {
					result="b";
				}
			}else {
				result="d";
			}
			
			
			System.out.println(result);

			
			
		}
		

		
	}
}
