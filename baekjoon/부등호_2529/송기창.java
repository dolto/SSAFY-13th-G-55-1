import java.util.*;
import java.io.*;

// 아이디어
// 백트래킹을 통해 모든 경우의 수를 비교하면 해결할 수 있습니다.
// 또한 조건에 맞지않는 경우 가지치기를 통해 시간을 단축하는 것도 가능합니다.
public class Main_2529_부등호_송기창 {

	// 부등호 개수
	static int n;
	// 부등호 저장할 배열
	static char[] inequality;
	// 부등호 사이 숫자 배열
	static int[] numbers;
	// 방문 처리를 위한 배열
	static boolean[] visit;
	// 최댓값, 최솟값
	static String max = "", min = "9999999999";

	public static void main(String[] args) throws IOException {
		// 빠른 입력을 위한 BufferedReader 사용
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 부등호의 숫자와 각 부등호 입력하는 부분
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 부등호를 저장할 배열, 각 숫자 정보를 저장해 둘 배열
		inequality = new char[n];
		numbers = new int[n + 1];
		visit = new boolean[10];

		// 부등호 입력, char로 저장
		for (int i = 0; i < n; i++) {
			inequality[i] = st.nextToken().charAt(0);
		}

		// 백트래킹 시작
		function(0);

		// 답 출력
		System.out.println(max);
		System.out.println(min);
	}

	static void function(int cnt) {
		// 모든 숫자가 자리잡으면 조건 검사를 하고 왔기 때문에 바로 출력
		if (cnt == n + 1) {
			// 현재 만들어진 숫자를 문자열로 저장
			String s = "";
			for (int i = 0; i <= n; i++) {
				s += numbers[i];
			}

			// 값을 숫자로 바꿔 대소 비교 후 최대, 최소 저장
			if (max.isEmpty() || s.compareTo(max) > 0) {
				max = s;
			}
			if (s.compareTo(min) < 0) {
				min = s;
			}
			return;
		}

		// 처리 부분
		for (int i = 0; i <= 9; i++) {
			// 아직 방문하지 않은 수일때
			if (!visit[i]) {
				// 첫 숫자는 무조건 삽입하고, 부등호 조건에 따라 가지치기
				if (cnt == 0 || check(numbers[cnt - 1], i, inequality[cnt - 1])) {
					// 방문 처리
					visit[i] = true;
					// 현재 위치에 숫자 넣기
					numbers[cnt] = i;
					// 백트래킹을 위한 재귀 호출
					function(cnt + 1);
					visit[i] = false;
				}
			}
		}
	}

	// 가지치기를 위한 부등호 검사
	static boolean check(int prev, int next, char op) {
		if (op == '<')
			return prev < next;
		if (op == '>')
			return prev > next;
		return false;
	}
}
