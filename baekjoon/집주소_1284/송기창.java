package algo_study;

import java.util.Scanner;

// 아이디어
// 어떤 수가 들어오든 길이+1 만큼의 길이가 필요하고
// 나머지는 숫자 길이에 맞게 추가해주면 됩니다.
public class baekjoon_1284 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			int n = sc.nextInt();
			if (n == 0)
				break;

			int ans = 0;
			String s = String.valueOf(n);
			ans += (s.length()) + 1;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '0')
					ans += 4;
				else if (s.charAt(i) == '1')
					ans += 2;
				else
					ans += 3;
			}
			System.out.println(ans);
		}

		sc.close();
	}

}
