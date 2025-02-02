package algo_study;

import java.util.Scanner;

// 아이디어
// 해당 날짜까지의 일 수를 구해두고
// 7로 나눠서 해당하는 요일을 출력하면 됩니다.
public class baekjoon_2948 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int[] marr = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String[] dayArr = { "Thursday", "Friday", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday" };

		int day = sc.nextInt();
		int month = sc.nextInt();

		int ans = 0;
		for (int i = 0; i < month - 1; i++) {
			ans += marr[i];
		}
		ans += day;

		System.out.println(dayArr[(ans-1) % 7]);
		sc.close();
	}
}
