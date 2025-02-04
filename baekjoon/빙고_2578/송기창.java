package algo_study;

// 아이디어
// 시뮬레이션 문제입니다.
// 배열에 빙고판의 정보를 받아두고 사회자가 부른 번호에 따라 해당 위치의 값을 0으로 바꿉니다.
// 이후 빙고 여부를 확인하고 빙고가 3번이 되면 종료합니다.
import java.util.Scanner;

public class baekjoon_2578 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int[][] board = new int[5][5];

		// 빙고판 상태 입력
		for (int i = 0; i < 5; i++) {
			String newLine = sc.nextLine();
			String[] line = newLine.split(" ");
			for (int j = 0; j < line.length; j++) {
				board[i][j] = Integer.parseInt(line[j]);
			}
		}

		// count: 부른 수의 수
		int count = 0;

		// 사회자 입력 진행
		for (int i = 0; i < 5; i++) {
			String bingoLine = sc.nextLine();
			String[] line = bingoLine.split(" ");
			for (int j = 0; j < line.length; j++) {
				count++;
				// 해당 숫자를 찾아서 0으로
				for (int k = 0; k < 5; k++) {
					for (int l = 0; l < 5; l++) {
						if (board[k][l] == Integer.parseInt(line[j])) {
							board[k][l] = 0;
						}
					}
				}
				
				// bingoCount: 빙고의 수
				int bingoCount=0;
				
				// 시간 감소 아이디어: 숫자가 최소 12개 불렸을때부터 3줄 빙고가 가능
				if(count<=11) continue;

				// 가로줄, 세로줄 빙고 여부
				for (int k = 0; k < 5; k++) {
					int cnt = 0;
					int cnt2 = 0;
					for (int l = 0; l < 5; l++) {
						if (board[k][l] == 0)
							cnt++;
						if (board[l][k] == 0)
							cnt2++;
					}
					if (cnt == 5)
						bingoCount++;
					if (cnt2 == 5)
						bingoCount++;
				}

				// 오른쪽대각선, 왼쪽대각선 빙고 여부
				int cnt = 0;
				int cnt2 = 0;
				for (int k = 0; k < 5; k++) {
					if (board[k][k] == 0)
						cnt++;
					if (board[k][4 - k] == 0)
						cnt2++;
				}
				if (cnt == 5)
					bingoCount++;
				if (cnt2 == 5)
					bingoCount++;

				// 빙고의 수가 3이상이면 정지
				if (bingoCount >= 3) {
					System.out.print(count);
					// 여기서 프로그램을 종료
					System.exit(0);
				}
			}
		}

		sc.close();
	}
}
