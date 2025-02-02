// 아이디어 
// 9개의 데이터를 ArrayList에 넣은 후 길이가 7인 조합으로 만든 다음 해당 조합의 합이 100이라면 값을 return 한다. 
// 조합은 backtracking으로 생성한다. 

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// backtracking 메서드를 가진 객체
class B2306 {
	// backtracking 메서드 
	// param List<Integer> tmp --> 조합을 담을 ArrayList
	// param int start --> 조합에 들어갈 난쟁이 index
	// param List<Integer> list --> 난쟁이 키가 담긴 ArrayList
	// return List<Integer> --> 난쟁이 키의 합이 100인 조합이 나타나면  List<Integer>을 return , 합이 100이 아니라면 null을 return 
	public  List<Integer> backtracking(List<Integer> tmp, int start, List<Integer> list) {
		// 조합의 길이가 7이라면 backtracking은 return 함
		if (tmp.size()==7) {

			// 난쟁이들 키의 합을 저장하기 위한 변수
			int sum = 0;
			
			// 해당 난쟁이 조합의 합을 구함
			for (int j=0; j < 7; j++) {
				sum+= tmp.get(j);
			}
			// 난쟁이들 키의 합이 100이라면 더이상 해당 메서드를 할필요가 없으므로 해당 난쟁이 조합을 return 
			if (sum==100) {
				return tmp;
			}
			// 난쟁이들 키의 합이 100이 아니므로 또다른 조합을 만들라는 신호로 null을 return 
			return null;
			
		}
		
		// 조합이 길이가 7이 될 때까지 조합을 구성
		for(int k=start; k<9; k++) {

			// ArrayList에 add
			tmp.add(list.get(k));

			// backtracking 메서드 사용
			List<Integer> tmpResult = backtracking(tmp,k+1,list);

			// backtracking return 값이 null이 아닌 경우는 그 조합의 난쟁이 키의 합이 100이라는 의미이므로 해당 난쟁이 조합을 return 
			if (tmpResult!=null ) {
				return tmpResult;
			}
			// ArrayList에서 remove
			tmp.remove(tmp.size()-1);
		}
		
		
		
		return null;
			 
	}
}

public class Main {

	public static void main(String[] args) throws IOException {
		// input data 받기 위한 BufferedReader, InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input data를 받기 위한 ArrayList
		List<Integer> list = new ArrayList<>();
		
		// ArrayList에  input data add
		for (int i=0; i<9; i++) {
			list.add(Integer.parseInt(br.readLine()));
		}
		
		// 조합을 담을 ArrayList
		List<Integer> tmp = new ArrayList<>();
		
		// backtracking 메서드가 있는 객체 생성
		B2306 b = new B2306();
		
		//backtracking 메서드 실행 
		List<Integer> result = b.backtracking(tmp,0,list);
		
		// 난쟁이 키를 오름차순으로 정렬
		Collections.sort(result);
		
		// 난쟁이 키 출력 
		for(int index=0; index<7; index++) {
			System.out.println(result.get(index));
		}
		
		
	}
	
}
