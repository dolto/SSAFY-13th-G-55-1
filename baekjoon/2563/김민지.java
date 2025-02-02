// 아이디어 
// 100*100의 boolean array를 만들어서  해당 array에서 true로 변한 array 요소의 개수를 return 하자. 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main  {


	public static void main(String[] args) throws Exception {
		
		// input data를 받기 위한 BufferedReader, InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// input data를 공백 기준으로 나누기 위한 StringTokenizer
		StringTokenizer st = null;
		
		
		// 도화지를 boolean 타입의 array로 생성
		boolean [][] paper = new boolean[100][100];
		
		// 색종이 개수 저장
		int n = Integer.parseInt(br.readLine());
		
		// 색종이가 차지한 개수를 저장
		int result = 0;
		
		for(int i=0; i<n; i++) {
			
			// input data를 공백 기준으로 나누기
			st = new StringTokenizer(br.readLine());
			
			// array는 0부터 시작하므로 -1해줌
			int x = Integer.parseInt(st.nextToken()) -1;
			int y = Integer.parseInt(st.nextToken()) -1;

			// 도화지에 색종이가 차지하는 부분은 true로 설정
			// false에서 true로 설정되면 result에 +1 해줌 
			for(int j=x; j<x+10; j++) {
				for(int h=y; h<y+10; h++) {
					if (paper[j][h] == true)  {
						continue;
					}else{
						paper[j][h] =true;
						result +=1;
					}
				}
			}
			
		}
		
		System.out.println(result);

		
	}

}
