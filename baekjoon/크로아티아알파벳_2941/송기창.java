package algo_study;

// 아이디어
// 가장 간단한 구현 형식으로 풀었습니다.
// 주의점: 저처럼 풀면 index 초과 가능성 있어서 주의해야 합니다.
import java.util.*;

public class baekjoon_2941 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();

		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'c') {
				if (i + 1 < s.length() && (s.charAt(i + 1) == '=' || s.charAt(i + 1) == '-')) {
					count++;
					i++;
					continue;
				}
			} else if (s.charAt(i) == 'd') {
				if (i + 2 < s.length() && s.charAt(i + 1) == 'z' && s.charAt(i + 2) == '=') {
					count++;
					i += 2;
					continue;
                } else if (i + 1 < s.length() && s.charAt(i + 1) == '-') {
					count++;
					i++;
					continue;
				}
            } else if (s.charAt(i) == 'l' && i + 1 < s.length() && s.charAt(i + 1) == 'j') {
				count++;
				i++;
				continue;
            } else if (s.charAt(i) == 'n' && i + 1 < s.length() && s.charAt(i + 1) == 'j') {
				count++;
				i++;
				continue;
            } else if (s.charAt(i) == 's' && i + 1 < s.length() && s.charAt(i + 1) == '=') {
				count++;
				i++;
				continue;
            } else if (s.charAt(i) == 'z' && i + 1 < s.length() && s.charAt(i + 1) == '=') {
				count++;
				i++;
				continue;
			}
			count++;
		}
		System.out.print(count);
	}
}
