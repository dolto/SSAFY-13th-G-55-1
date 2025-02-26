import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 홍도완 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	/// 시간제한: 1초
	/// 메모리제한: 512mb
	// 걸린시간: 692ms
	// 메모리: 63mb
	//
	/// 아이디어
	// nextPermutation으로 1번 선수를 제외한 모든 순서를 구하고
	// 해당 순서로 게임을 돌려보면 최적의 해를 구할 수 있다.
	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine().trim());
		int[][] inning = new int[N][8];
		int[] bestPlayer = new int[N];
		int[] players = new int[8];
		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			bestPlayer[i] = Integer.parseInt(st.nextToken());
			for (int j = 0; j < 8; ++j) {
				inning[i][j] = Integer.parseInt(st.nextToken());
				players[j] = j;
			}
//			Arrays.sort(players);
		}

		int answer = playBaseball(inning, bestPlayer, players);
//		for (int i = 0; i < 8; ++i) {
//			bw.write(String.format("%d ", players[i]));
//		}
//		bw.write("\n");
		while (nextPermutation(players)) {
			answer = Math.max(answer, playBaseball(inning, bestPlayer, players));
//			for (int i = 0; i < 8; ++i) {
//				bw.write(String.format("%d ", players[i]));
//			}
//			bw.write("\n");
		}

		bw.write(Integer.toString(answer));

		bw.flush();
		br.close();
		bw.close();
	}

	static int playBaseball(int[][] inning, int[] bestPlayer, int[] players) {
		int score = 0;
		int index = 0;
		for (int i = 0; i < inning.length; ++i) {
			int out = 0;
			boolean[] lu = new boolean[3];
			while (out < 3) {
				boolean home = true;
				int j = index > 3 ? index - 1 : index;
				if (index == 3) {
					j = bestPlayer[i];
				} else {
					j = inning[i][players[j]];
				}

				switch (j) {
				case 0:
					out += 1;
					break;
				case 1:
				case 2:
				case 3:
					for (int k = 0; k < j; ++k) {
						if (lu[2]) {
							lu[2] = false;
							score += 1;
						}
						if (lu[1]) {
							lu[2] = true;
							lu[1] = false;
						}
						if (lu[0]) {
							lu[1] = true;
							lu[0] = false;
						}
						if (home) {
							lu[0] = true;
							home = false;
						}
					}
					break;
				case 4:
					score += 1;
					for (int k = 0; k < 3; ++k) {
						if (lu[k]) {
							lu[k] = false;
							score += 1;
						}
					}
					break;
				}

				index = (index + 1) % 9;
			}
		}
		return score;
	}

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static boolean nextPermutation(int[] arr) {
		int i = arr.length - 1;
		while (i > 0 && arr[i] <= arr[i - 1]) {
			--i;
		}
		if (i == 0)
			return false;

		int j = arr.length - 1;
		while (arr[j] <= arr[i - 1]) {
			--j;
		}
		swap(arr, i - 1, j);

		int k = arr.length - 1;
		while (i < k)
			swap(arr, i++, k--);

		return true;
	}
}
