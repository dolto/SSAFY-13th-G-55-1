package algo_study;

import java.util.Scanner;

// 아이디어
// 큰 단위의 동전부터 차례로 사용해서 최소 개수를 구하면 되는
// 가장 기초적인 그리디 문제입니다.
public class baekjoon_5585 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int n = sc.nextInt();
		int ch = 1000 - n;
		int t = 0;

		if (ch / 500 != 0) {
			t += ch / 500;
			ch = ch % 500;
		}
		if (ch / 100 != 0) {
			t += ch / 100;
			ch = ch % 100;
		}
		if (ch / 50 != 0) {
			t += ch / 50;
			ch = ch % 50;
		}
		if (ch / 10 != 0) {
			t += ch / 10;
			ch = ch % 10;
		}
		if (ch / 5 != 0) {
			t += ch / 5;
			ch = ch % 5;
		}
		t += ch;

		System.out.println(t);
		sc.close();
	}

}
