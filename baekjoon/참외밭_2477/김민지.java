package tmp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class tmp {

	public static void main(String[] args) throws Exception {
		// 아이디어 : (전체 밭 넓이-빈 밭 넓이) * 평당 참외 수확 개수
		
		// input data는 다음과 같은 패턴 중 하나를 갖는다.
		// 서남서남동북
		//  남서남동북서
		//    서남동북서남
		//     남동북서남서
		//       동북서남서남
		//         북서남서남동
		// 위의 패턴에서 전체 밭 넓이와 빈 밭 넓이를 구하기 위해서 필요한 데이터는 아래와 같다. 
		// () -> 전체 밭 넓이에 필요한 데이터 {} -> 빈 밭 넓이에 필요한 데이터 
		
		// 서{남서}남(동북)
		//  {남서}남(동북)서
		//    {서}남(동북)서{남}
		//        남(동북)서{남서}
		//          (동북)서{남서}남
		//            (북)서{남서}남(동)
		
		// 빈 밭 넓이에 필요한 데이터가 전체 밭 넓이에 필요한 데이터 중 첫번째 데이터을 기준으로 각각 -2,-3 만큼 떨어져 있다는 것을 알 수 있다. 
		// 예를 들어, 아래와 같은 상황이며 이 데이터를 array에 넣었다고 가정해보면
		//  {남서}남(동북)서
		// arr =  {남, 서, 남, 동, 북, 서}
		// 전체 밭 넓이 - 빈 밭 넓이 = arr[3]*arr[3+1] - arr[3-2]*arr[3-3]
		// 즉, 전체 밭 넓이에 필요한 데이터 중 첫번째 데이터만 파악하면 답을 구할 수 있다. 
		
		// 전체 밭 넓이에 필요한 첫번째 데이터를 얻기 위해 다음과 같은 조건을 확인한다. 
		// input data 모두 읽었을 때 해당 데이터의 방향이 중복되지 않아야 한다.
		
		// 전체 넓이에 필요한 데이터 중 첫번재 데이터와 두번째 데이터를 구분하기 위해 다음과 같은 조건을 확인한다. 
		// input data를 읽을 때 어떤 데이터가 먼저 불렸냐에 따라 순서가 정해진다. 
		// 다만, 첫번째 데이터가 index 5에 있고 두번째 데이터가 index 0 자리에 있을 떄가 있으며 이는 예외사항으로 처리한다. 
		
		// input data 읽기 
		System.setIn(new FileInputStream("rsc/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int melonCnt = Integer.parseInt(br.readLine());
		
		int [][] arr = new int[6][2];
		int [] checkDupl = new int[5];
		int derection;
		int length;
		for(int i=0; i<6; i++) {
			st = new StringTokenizer(br.readLine());
			// 나중에 사용하기 위해 array에 넣기 
			derection = Integer.parseInt(st.nextToken());
			length = Integer.parseInt(st.nextToken());
			arr[i][0] = derection;
			arr[i][1] = length;
			
			// 중복 확인 
			if(checkDupl[derection]==0) {
				checkDupl[derection] = i+1;
			}else {
				
				// 중복 시 해당 index 값에는 -1을 지정한다. 
				checkDupl[derection] = -1;
			}
			
			
		}
		
		int first=0;
		int second=0;
		for(int i=1; i<5; i++) {
			// 중복이 아닌 값 == 전체 밭 넓이를 구할 데이터
			if(checkDupl[i]!=-1 ) {
				// 전체 밭 넓이를 구할 데이터 중 첫번째 데이터 찾기
				if(first==0) {
					first = checkDupl[i];
				}else {
					
					if(Math.abs(first-checkDupl[i])==5) {
						first = 6;
						second = 1;
						break;
					}
					first = Math.min(first, checkDupl[i]);
					second = first+1;
				}
				
			}
		}
		
		first--;
		second--;
		int blank_first = first-2;
		int blank_second = first-3;
		if (blank_first<0) blank_first +=6;
		if (blank_second<0) blank_second +=6;
		
		
		int result = ((arr[first][1] *arr[second][1])- (arr[blank_first][1]*arr[blank_second][1])) *melonCnt;
		System.out.println(result);
		


	}

}
