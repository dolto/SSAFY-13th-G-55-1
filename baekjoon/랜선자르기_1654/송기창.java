// 백준 1654번 랜선 자르기
import java.util.*;
import java.io.*;

// 아이디어
// 이진 탐색을 통해 해결할 수 있습니다.
// 필요한 랜선의 수만큼 만들 수 있으면 자르는 길이를 늘려보고, 부족하면 줄이는 과정을 통해
// 최적값을 찾을 수 있습니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 먼저 입력을 받으면서 가장 긴 랜선의 길이를 찾습니다.
		long maxLine = 0;
		List<Integer> arr = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int line = Integer.parseInt(br.readLine().trim());
			arr.add(line);
			maxLine = Math.max(maxLine, line);
		}

		long ans = 0;
		long start = 1, end = maxLine;
		// 이진탐색을 통해 가장 긴 랜선의 길이를 찾습니다.
		while (start <= end) {
			// 이진 탐색의 중앙값
			long mid = (start + end) / 2;

			// 현재 길이로 랜선을 몇 개 만들 수 있는지 체크
			long sum = 0;
			for (int i = 0; i < n; i++) {
				sum += (arr.get(i) / mid);
			}

			if (sum >= m) {
				// 구해진 답을 일단 저장합니다.
				ans = mid;
				// 더 긴 길이가 가능할 수 있으니 길이를 늘려봅니다.
				start = mid + 1;
			} else {
				// 원하는 갯수보다 작으면 더 짧은 길이로 시도합니다.
				end = mid - 1;
			}
		}
		System.out.print(ans);
	}
}
