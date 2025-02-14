import java.util.*;
import java.io.*;

// 아이디어
// 배열에 불가능한 타이밍을 모두 저장해 두고
// 마지막에 가장 빠른 전화 타이밍에 전화를 받을 수 있는지 체크했습니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int ans = 0;
		// 타이밍을 저장할 논리형 배열
		boolean[] d = new boolean[5000];
		// 전화가 불가능한 타이밍을 모두 true로 바꿔둡니다.
		for (int i = 0; i < n; i++) {
			for (int j = ans; j < ans + m; j++) {
				d[j] = true;
			}
			ans += (m + 5);
		}

		// 전화가 가능한 타이밍을 순차적으로 확인하며 가장 빠른 타이밍에 전화를 받고 종료합니다.
		int ansIdx = 0;
		while (true) {
			if (d[ansIdx] == false) {
				System.out.println(ansIdx);
				return;
			}
			ansIdx += k;
		}
	}
}
