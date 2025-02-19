import java.util.*;
import java.io.*;

public class Solution {

	static int winCount = 0;
	static int loseCount = 0;
	static Set<Integer> cardSet = new HashSet<>();
	static List<Integer> arr = new ArrayList<>();
	static List<Integer> arr2 = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tcn = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= tcn; tc++) {
			// 테케마다 초기화
			winCount = 0;
			loseCount = 0;
			arr.clear();
			arr2.clear();
			cardSet.clear();

			// 인영이 카드를 set을 통해 구한다.
			for (int i = 1; i <= 18; i++)
				cardSet.add(i);

			// 규영이 카드 입력, set에서 지워주기
			int total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				int card = Integer.parseInt(st.nextToken());
				arr.add(card);
				cardSet.remove(card);
				total += card;
			}

			// set의 요소를 arr2로 이동
			for (int iter : cardSet) {
				arr2.add(iter);
			}
			
			// 백트래킹 시작
			function(0);

			// 정답 출력
			System.out.printf("#%d %d %d\n", tc, winCount, loseCount);
		}
	}

	static int[] arr3 = new int[9];
	static boolean[] isVisited = new boolean[9];

	static void function(int idx) {
		if (idx == 9) {
			// 카드의 위치가 정해지면 규영이 카드랑 비교해서 승리횟수, 패배횟수 구하기
			int sum1=0, sum2=0;
			for (int i = 0; i < 9; i++) {
				if (arr.get(i) > arr3[i]) {
					sum1+=arr.get(i);
					sum1+=arr3[i];
				}
				else if (arr.get(i) < arr3[i]) {
					sum2+=arr.get(i);
					sum2+=arr3[i];
				}
			}
			if(sum1>sum2) winCount++;
			else if(sum1<sum2) loseCount++;
			return;
		}
		
		// 카드 수만큼 반복하면서
		for (int i = 0; i < 9; i++) {
			// 이미 방문한 카드면 넘어가고
			if (isVisited[i])
				continue;
			// 아직 방문안한 카드 순서대로 정해주고
			arr3[idx] = arr2.get(i);
			// 방문처리
			isVisited[i] = true;
			// 다음 인덱스 카드 구하기 위한 재귀호출
			function(idx + 1);
			// 방문초기화
			isVisited[i] = false;
		}
	}
}