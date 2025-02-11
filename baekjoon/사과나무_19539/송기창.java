import java.util.*;
import java.io.*;

// 아이디어
// 현재 나무의 상태가 중요한게 아니라 최종적으로 몇 번의 물을 주는지에 관점을 두고 생각해야합니다.
// 2씩 성장하는 물뿌리개 하나와 1씩 성장하는 물뿌리게 하나를 매일 매일 뿌려야 하기 때문에
// 필요한 양을 전부 합쳐서 2로 나눈 값과 나머지를 각각 저장해두고
// 2로 나눈값은 물뿌리개2, 1로 나눈값은 물뿌리개1의 급수 횟수로 정하고
// 두 숫자가 같아질 수 있다면 yes 아니면 no를 출력하면 됩니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int cnt1 = 0, cnt2 = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			cnt2 += num / 2;
			cnt1 += num % 2;
		}

		while (true) {
			if (cnt2 < cnt1) {
				System.out.println("NO");
				return;
			} else if (cnt2 == cnt1) {
				System.out.println("YES");
				return;
			}
			cnt2--;
			cnt1 += 2;
		}
	}
}
