import java.io.*;
import java.util.*;

public class Solution {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/// 아이디어
	// 너무 어렵게 생각하지말고 up, down, left, right를 벽으로 하고, 해당 구간을 출력한 다음 해당 구간을 출력하면 변경되는 부분을 갱신한다
	// 예시) 위쪽 가로를 다 채웠으면 up은 한칸 내려간다.
	// 갱신할 때 마다 up이 down보다 더 아래에 있는지, left가 right보다 더 오른쪽에 있는지 체크하면 된다.
	public static void main(String[] args) throws IOException {
		final int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; ++t) {
			final int N = Integer.parseInt(br.readLine().trim());
			int u = 0, d = N - 1, l = 0, r = N - 1;
			int[][] answer = new int[N][N];
			int count = 1;
			while (true) {
				if (u > d && l > r)
					break;
				int temp = u;
				for (int i = l; i <= r; ++i) {
					answer[temp][i] = count++;
				}
				temp = r;
				u += 1;
				if (u > d && l > r)
					break;
				for (int i = u; i <= d; ++i) {
					answer[i][temp] = count++;
				}
				temp = d;
				r -= 1;
				if (u > d && l > r)
					break;
				for (int i = r; i >= l; --i) {
					answer[temp][i] = count++;
				}
				temp = l;
				d -= 1;
				if (u > d && l > r)
					break;
				for (int i = d; i >= u; --i) {
					answer[i][temp] = count++;
				}
				l += 1;
			}
			bw.write(String.format("#%d\n", t));
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					bw.write(String.format("%d ", answer[i][j]));
				}
				bw.write("\n");
			}
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
