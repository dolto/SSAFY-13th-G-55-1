import java.io.*;
import java.util.*;

// 아이디어
// n개의 수 중 m개를 선택하여 사전순서대로 출력하는 전형적인 순열 문제입니다.
// 백트래킹을 활용해 visit배열로 탐색한 숫자를 체크하면서 탐색합니다.
// 또한 탐색이 끝나면 해당 수는 list에서 빼고 다시 해당 상태부터 진행하도록 구현했습니다.
public class Main {

	static int n, m;
	static List<Integer> li = new ArrayList<>();
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		visit = new boolean[n + 1];
		func(0);
	}

	public static void func(int num) {
		if (num == m) {
			for (int iter : li) {
				System.out.print(iter + " ");
			}
			System.out.println();
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (!visit[i]) {
				visit[i] = true;
				li.add(i);
				func(num + 1);
				li.remove(li.size() - 1);
				visit[i] = false;
			}
		}
	}
}
