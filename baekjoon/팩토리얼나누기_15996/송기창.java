package algo_study;

import java.io.*;
import java.util.*;

// 아이디어
// 해당 문제와 비슷한 문제들은 특히 팩토리얼이 포함된 문제들은
// 로직이 간단할 것 같지만 주로 N의 범위가 매우커서 직관적인 방식으로는 해결할 수 없습니다.
// 저는 이런 문제가 나오면 항상 N을 나눠가며 누적하는 방식을 생각합니다.
// 이 문제는 N!에 등장하는 1~n까지의 수 중에서 m의 등장 횟수를 구하여 모두 더하면 답이 됩니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int ans = 0;
		while(n>=m) {
			n/=m;
			ans+=n;
		}
		System.out.println(ans);
	}
}
