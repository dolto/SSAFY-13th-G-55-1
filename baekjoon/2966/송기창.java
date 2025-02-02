package algo_study;

import java.util.*;

// 아이디어
// 각 학생의 찍는 패턴을 배열에 넣고 해당하는 순서의 답과 비교할 수 있도록 한다.
// 이후 답의 최댓값을 저장하고
// 답 최댓값 만큼 답을 맞춘 친구의 이름을 출력하면 됩니다.
public class baekjoon_2966 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		char[] sang = { 'A', 'B', 'C' };
		char[] chang = { 'B', 'A', 'B', 'C' };
		char[] heyon = { 'C', 'C', 'A', 'A', 'B', 'B' };

		int n = sc.nextInt();
		sc.nextLine(); // 공백 삭제
		String s = sc.nextLine();

		int sangAns = 0, changAns = 0, heyonAns = 0;
		for (int i = 0; i < n; i++) {
			if (s.charAt(i) == sang[i % sang.length])
				sangAns++;
			if (s.charAt(i) == chang[i % chang.length])
				changAns++;
			if (s.charAt(i) == heyon[i % heyon.length])
				heyonAns++;
		}

		int maxScore = Math.max(sangAns, Math.max(changAns, heyonAns));
		System.out.println(maxScore);
		if (sangAns == maxScore)
			System.out.println("Adrian");
		if (changAns == maxScore)
			System.out.println("Bruno");
		if (heyonAns == maxScore)
			System.out.println("Goran");

		sc.close();
	}

}
