// 아이디어 
// 빙고 번호만 들었을때 그 번호가 어느 행과 열에 있는지 확인할 수 있도록  빙고 데이터의 행과 열 데이터를 array에 담는다. 
// 빙고 데이터를 2차원 배열에 그냥 담을 경우 빙고 번호를 들을 때마다 2차원 배열을 탐색하는 것이 비효율 적이라 위의 방법으로 시도 
// 해당 빙고의 현황판(ex. n번째 행의 체크 개수)를 알 수 있도록 HashMap 사용 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
	// 빙고가 되었는지 확인하는 메소드
	public static boolean checkBingo(String str,Map <String, Integer> m) {

		// 빙고
		int cnt = m.put(str, m.get(str)+1) + 1;

		// 해당 줄에 빙고가 되면 true를 반환
		if (cnt == 5) {
			return true;
		}else { // 해당 줄에 빙고가 아니라면 false 반환
			return false;
		}
		
	}

	public static void main(String[] args) throws Exception {
		// input data를 받기 위한 BufferedReader, InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input data를 공백기준으로 나누기 위한 StringTokenizer
		StringTokenizer st = null;

		// 빙고 데이터의 행과 열을 저장할 array
		int [][] arr = new int[26][2];
		
		for(int i=0; i<5; i++) {
			// 빙고 데이터를 StringTokenizer 객체에 담음
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {

				// input data를 공백을 기준으로 출력
				int index = Integer.parseInt(st.nextToken());

				// 빙고 데이터의 행과 열을 저장
				arr[index][0] = i; 
				arr[index][1] = j; 
			}
			
		}
		
		//해당 빙고의 현황판(ex. n번째 행의 체크 개수)를 알 수 있는HashMap 
		// "r0" --> row 0번째를 의미하며 해당 key의 value는 몇개 체크되었는지 알 수 있다. 
		// "crd" --> cross right down으로 오른쪽 아래로 내려가는 대각선을 의미
		// "cld" --> cross left down으로 왼쪽 아래로 내려가는 대각선을 의미
		Map <String, Integer> m = new HashMap<>();
		m.put("r0", 0);
		m.put("r1", 0);
		m.put("r2", 0);
		m.put("r3", 0);
		m.put("r4", 0);
		m.put("c0", 0);
		m.put("c1", 0);
		m.put("c2", 0);
		m.put("c3", 0);
		m.put("c4", 0);
		m.put("crd", 0);
		m.put("cld", 0);
		
		// 결과값을 저장
		int result = 0;

		// 빙고 개수 
		int bingo_cnt = 0;

		// 3중 for문을 break하기 위한 flag
		boolean flag = false;

		for(int i=0; i<5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				result +=1;
				int index = Integer.parseInt(st.nextToken());
				// 해당 발표 번호가 해당하는 줄을 저장할 객체 
				List<String> list = new ArrayList<>();

				int r = arr[index][0];
				int c = arr[index][1];

				//해당 번호의 row 추가 
				list.add("r" + r);

				//해당 번호의  column 추가
				list.add("c" + c);

				// row와 column이 같다는 것은 오른쪽 아래로 내려가는 대각선에 들어간다는 의미이므로  오른쪽 대각선 추가 
				if (r==c) {
					list.add("crd");
				}

				// row와 column가 더한값이 4와 같다는 것은 왼쪽 아래로 내려가는 대각선에 들어간다는 의미이므로  왼쪽 대각선 추가 
				if(r+c==4) {
					list.add("cld");
				}
				
				for(String s : list) {
					// 빙고가 되었는지 check 
					boolean b = checkBingo(s,m);
					// 빙고가 되었을 경우 지금까지 빙고 개수를 확인하며 빙고 개수가 3일 경우 3중 for문을 빠져나온다. 
					if(b==true) {
						bingo_cnt +=1;
						if (bingo_cnt==3) {
							flag = true;
							break;
						}
					}
				}
				
				if (flag == true) {
					break;
				}
				
			}
			if (flag == true) {
				break;
			}
		}
		
		System.out.println(result);
		
		
		
	}

}
