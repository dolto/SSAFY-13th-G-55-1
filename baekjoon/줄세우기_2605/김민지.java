// 아이디어 
// 1~5를 ArrayList에 넣은 후 해당 자리에서 remove하고 다시 add

package tmp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws Exception {
		// input data를 받기 위한 BufferedReader, StringTokenizer
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input data를 공백 기준으로 나누기 위한 StringTokenizer
		StringTokenizer st = null;
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());

		// input data를 넣기 위한 ArrayList
		List<Integer> list = new ArrayList<>(); 

		// 학생의 현재 위치를 기록하기 위한 ArrayList
		List<Integer> result = new ArrayList<>(); 
		for(int i=0; i<n; i++) {

			// input data를 ArrayList에 add
			list.add(Integer.parseInt(st.nextToken()));

			// 학생 위치 초기화 
			result.add(i+1);
		}
		
		// input data ArrayList를 하나씩 읽음
		for(int j=0; j<n; j++) {
			// input data가 0 이상일 경우 자리를 이동한다는 의미
			if (list.get(j) >0) {
				// 해당 학생을 그 자리에서 remove
				int r = result.remove(j);
				// input data에 맞게 다시 add
				result.add(j-list.get(j), r);
			}
			
		}
		for(int r : result) {
			System.out.print(r+" ");
		}
	}

}
