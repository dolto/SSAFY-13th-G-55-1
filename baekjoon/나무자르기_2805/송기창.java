import java.util.*;
import java.io.*;

// 아이디어
// 절단기 높이의 최댓값을 구하는 문제이기 때문에 가장 키가 큰 나무를 기준으로
// 이진탐색을 통해 답을 구할 수 있습니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 입력을 받아 나무 길이를 저장하면서 가장 키가 큰 나무의 길이를 찾습니다.
		long maxHeight = 0;
		List<Integer> woodList = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			woodList.add(Integer.parseInt(st.nextToken()));
			maxHeight = Math.max(maxHeight, woodList.get(i));
		}

		long ans = 0;
		long start = 0, end = maxHeight;
		// 이진탐색을 진행합니다.
		while (start <= end) {
			long mid = (start + end) / 2;

			// 현재 절단기의 높이로 나무를 잘라 그 합을 구합니다.
			long sum = 0;
			for (int i = 0; i < n; i++) {
				sum += Math.max(woodList.get(i) - mid, 0);
			}

			if (sum >= m) {
				// 현재까지 가능한 가장 높은 높이를 ans에 저장해 둡니다.
				ans = mid;
				// 절단기 높이를 높여봅니다.
				start = mid + 1;
			} else {
				// 필요한 길이보다 부족하기 때문에 높이를 줄여봅니다.
				end = mid - 1;
			}
		}
		System.out.println(ans);
	}
}
