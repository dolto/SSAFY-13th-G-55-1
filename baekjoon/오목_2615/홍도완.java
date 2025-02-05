import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/// 아이디어
///왼쪽 위에서부터 오른쪽 아래로 내려가, 각 오목알을 확인하면된다.
///여기서 주의할 점
///1. 왼쪽 위에서 부터 오른쪽 아래로 내려가기 때문에, 왼쪽위 부분은 체크할 필요 없다.
///- 즉, 오른쪽 위, 오른쪽, 오른쪽 아래, 아래만 확인해주면 된다.
///2. 오목알이 연속해서 6개가 있는경우를 확인해야한다.
///- 연속해서 6개라면 기준 오목알을 중심으로, 그 이전것과, 6번째 있는 것을 의미한다.
///
///생각을 많이해보자... 시험에서 틀렸ㄷ ㅏ... 응애
public class 홍도완 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		int[][] map = new int[19][19];
		for (int i = 0; i < 19; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				bw.write(String.format("%d", map[i][j]));
			}
//			bw.write("\n");
		}

		for (int i = 0; i < 19; ++i) {
			for (int j = 0; j < 19; ++j) {
				if (map[i][j] != 0 && chacker(map, i, j, map[i][j])) {
					bw.write(String.format("%d\n%d %d", map[i][j], i + 1, j + 1));
					bw.flush();
					bw.close();
					br.close();
					return;
				}
			}
		}

		bw.write("0");
		bw.flush();
		bw.close();
		br.close();
	}

	static boolean chacker(int[][] map, int r, int c, int mal) throws IOException {
		// 왼쪽 위는 이미 체크를 했으므로, 오른쪽 아래 3개를 체크한다
		boolean result = true;
		for (int i = 0; i < 5; ++i) {
			if (c + i >= 19 || map[r][c + i] != mal) {
				result = false;
				break;
			}
		}
		if (result) {
//			bw.write("One\n");
			if (c + 5 < 19 && map[r][c + 5] == mal)
				result = false;
			else if (c - 1 >= 0 && map[r][c - 1] == mal)
				result = false;
		}
		if (result) {
			return true;
		}

		result = true;
		for (int i = 0; i < 5; ++i) {
			if (c + i >= 19 || r + i >= 19 || map[r + i][c + i] != mal) {
				result = false;
				break;
			}
		}
		if (result) {
//			bw.write("Tew\n");
			if (c + 5 < 19 && r + 5 < 19 && map[r + 5][c + 5] == mal)
				result = false;
			else if (c - 1 >= 0 && r - 1 >= 0 && map[r - 1][c - 1] == mal)
				result = false;
		}
		if (result) {
			return true;
		}

		result = true;
		for (int i = 0; i < 5; ++i) {
			if (r + i >= 19 || map[r + i][c] != mal) {
				result = false;
				break;
			}
		}
		if (result) {
//			bw.write("Thr\n");
			if (r + 5 < 19 && map[r + 5][c] == mal)
				result = false;
			else if (r - 1 >= 0 && map[r - 1][c] == mal)
				result = false;
		}
		if (result) {
			return true;
		}

		result = true;
		for (int i = 0; i < 5; ++i) {
			if (r - i < 0 || c + i >= 19 || map[r - i][c + i] != mal) {
				result = false;
				break;
			}
		}
		if (result) {
//			bw.write("Thr\n");
			if (r - 5 >= 0 && c + 5 < 19 && map[r - 5][c + 5] == mal)
				result = false;
			if (r + 1 < 19 && c - 1 >= 0 && map[r + 1][c - 1] == mal)
				result = false;
		}
		if (result) {
			return true;
		}
		return false;
	}

}
