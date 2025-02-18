import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class solution2 {


	// 연결된 노드 확인 함수
	public static void check(Set<Integer> set, Map<Integer, List<Integer>> map, int i) {

		// 현재 노드를 set에 저장
		set.add(i);
		
		// map에서 get을 통해 연결된 List가 없다면 return 
		if (map.get(i) == null) {
			return;
		}
		
		// map에서 get을 통해 연결된 List == 연결된 노드 확인할 수 있는 List
		List<Integer> tmp = map.get(i);
		
		// 연결된 리스트 전부 방문
		for (Integer key : tmp) {
			
			// 방문 전 set에 저장되어 있다면 다음 반복문으로 jump
			if(set.contains(key))	{
				continue;
			}else {
			// 다시 연결된 노드 확인 
			check(set, map, key);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		// 아이디어 : 학생 키 크기를 그래프로 그렸을 때, 
		// 현재 노드 + 현재에서 나가는 노드 + 현재에서 들어오는 노드 == 총 노드수 라면,
		// 그 노드는 키의 순서를 알 수 있다. 
		// 실행시간 : 2,709 ms
		// 메모리 : 125,888 kb
		
		// 입력을 받이 위한 BufferedReader, InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력값을 빈칸을 기준으로 나뉘기 위한 StringTokenizer
		StringTokenizer st = null;
		
		
		// 출력값에 쓰일 test_case 번호  
		int test_case = parseInt(br.readLine());
		
		// test_case 만큼 반복 
		for (int t = 1; t <= test_case; t++) {
			
			// 최종 정답을 담을 answer
			int answer = 0;
			
			// input 값 중 학생수를 담을 students
			int students = parseInt(br.readLine());
			
			// input 값 중 키 정보 개수를 담을 cnt
			int cnt = parseInt(br.readLine());
			
			// 나가는 노드를 그린 map
			Map<Integer, List<Integer>> map = new HashMap<>();
			
			// 들어오는 노드를 그린 map
			Map<Integer, List<Integer>> map2 = new HashMap<>();
			
			//  키 정보 개수 만큼 반복 
			for (int i = 0; i < cnt; i++) {
				
				// input data를 빈칸 기준으로 나눌  StringTokenizer
				st = new StringTokenizer(br.readLine());
				
				// 키 정보 중 앞 데이터는 더 키가 작은 학생 
				int small = parseInt(st.nextToken());
				
				// 키 정보 중 뒤 데이터는 더 키가 큰 학생 
				int big = parseInt(st.nextToken());
				
				// 나가는 노드 정보를 저장할 map
				if (map.get(small) == null) {
					
					// 작은 키 데이터에서 나가는 데이터를 저장하기 위해 ArrayList 사용
					List<Integer> tmp = new ArrayList<Integer>();
					
					// 작은 키 데이터에서 나가는 데이터를 저장
					tmp.add(big);
					
					// 작은 키 데이터에서 나가는 데이터를 저장한 ArrayList를 map에 저장
					map.put(small, tmp);
				} else {
					// 작은 키 데이터에서 나가는 데이터를  저장한 ArrayList에 추가
					map.get(small).add(big);
				}

				// 들어오는 노드를 저장할 map2
				if (map2.get(big) == null) {
					
					// 큰 키 데이터에 들어오는 데이터를 저장하기 위해 ArrayList 사용
					List<Integer> tmp2 = new ArrayList<Integer>();
					
					// 큰 키 데이터에 들어오는 데이터를 저장
					tmp2.add(small);
					
					// 큰 키 데이터에 들어오는 데이터를 저장한 ArrayList를 map2에 저장
					map2.put(big, tmp2);
				} else {
					// 큰 키 데이터에 들어오는 데이터를 저장한 ArrayList에 추가
					map2.get(big).add(small);
				}

			}

			// 각 노드마다 나가는 노드, 들어오는 노드를 저장할 map
			Map<Integer, Set<Integer>> m = new HashMap<>();
			
			// 나가는 노드, 들어오는 노드 중 중복 데이터를 제거하기 위한 set
			Set<Integer> set;
			
			// 나가는 노드 확인 
			for (Integer i : map.keySet()) {
				// 해당 노드의 set이 아직 설정이 안되어 있을 떄 set 생성
				if (m.get(i) == null) {

					m.put(i, new HashSet<>());
				}
				// 나가는 노드, 들어오는 노드를 저장할 map에서 set 가져오기 
				set = m.get(i);
				// 나가는 노드 모두 확인하기 
				check(set, map, i);

			}

			// 들어오는 노드 확인 
			for (Integer i : map2.keySet()) {
				
				// 해당 노드의 set이 아직 설정이 안되어 있을 떄  set 생성
				if (m.get(i) == null) {

					m.put(i, new HashSet<>());
				}
				// 나가는 노드, 들어오는 노드를 저장할 map에서 set 가져오기 
				set = m.get(i);
				
				// 들어오는 노드 모두 확인하기 
				check(set, map2, i);

			}

			for (Integer key : m.keySet()) {
				// 해당 노드 포함 연결된 노드의 개수와 학생 수가 같다면 키 순서를 알 수 있는 학생수를 +1 한다 
				if (m.get(key).size() == students) {
					answer++;
				}
			}
			

			// 결과값 출력
			System.out.println("#" + t + " " + answer);
		}

	}

}
