import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 종이 크기 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 가로 길이
		int m = Integer.parseInt(st.nextToken()); // 세로 길이

		int line = Integer.parseInt(br.readLine()); // 점선 개수

		// 자르는 위치를 저장할 리스트
		List<Integer> arr1 = new ArrayList<>();
		List<Integer> arr2 = new ArrayList<>();

		for (int i = 0; i < line; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 세로로 자르기
			if (a == 0) {
				arr2.add(b);
			}
			// 가로로 자르기
			else {
				arr1.add(b);
			}
		}
		Collections.sort(arr1);
		Collections.sort(arr2);

		// 최대 가로 길이 찾기
		int max1 = 0;
		if (!arr1.isEmpty()) {
			max1 = arr1.get(0);
		} else {
			max1 = n;
		}

		for (int i = 1; i < arr1.size(); i++) {
			int diff = arr1.get(i) - arr1.get(i - 1);
			if (diff > max1) {
				max1 = diff;
			}
		}

		if (!arr1.isEmpty()) {
			int lastDiff = n - arr1.get(arr1.size() - 1);
			if (lastDiff > max1) {
				max1 = lastDiff;
			}
		}

		// 최대 세로 길이 찾기
		int max2 = 0;
		if (!arr2.isEmpty()) {
			max2 = arr2.get(0);
		} else {
			max2 = m;
		}

		for (int i = 1; i < arr2.size(); i++) {
			int diff = arr2.get(i) - arr2.get(i - 1);
			if (diff > max2) {
				max2 = diff;
			}
		}

		if (!arr2.isEmpty()) {
			int lastDiff = m - arr2.get(arr2.size() - 1);
			if (lastDiff > max2) {
				max2 = lastDiff;
			}
		}

		// 정답
		System.out.println(max1 * max2);
	}
}
