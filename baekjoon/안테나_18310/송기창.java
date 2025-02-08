package algo_study;

import java.io.*;
import java.util.*;

// 아이디어
// 그리디 문제입니다.
// 집의 위치를 정렬한 뒤, 중앙에 위치한 곳에 안테나를 설치하면 항상 최소값이 됩니다.

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Integer> li = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			li.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(li);
		System.out.println(li.get((n - 1) / 2));
	}
}
