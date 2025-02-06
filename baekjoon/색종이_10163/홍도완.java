import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

  /// 아이디어
  // 격자크기는 10001 * 1001
  // 1000002 * 4 = 4mb
  // 배열로 풀면 쉽다. (각 색종이의 종류별로 답을 출력해야 하기 때문에, 색종이 배열에 색종이 넘버를 저장하고, 그 넘버의 개수를 세면 된다.
	public static void main(String[] args) throws IOException {
		int[][] map = new int[1001][1001];
		int n = Integer.parseInt(br.readLine().trim());
		int[] answer = new int[n];

		for (int i = 1; i <= n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int cr = Integer.parseInt(st.nextToken());
			int rd = Integer.parseInt(st.nextToken());
			int cl = cr + Integer.parseInt(st.nextToken());
			int ru = rd + Integer.parseInt(st.nextToken());

			for (int j = rd; j < ru; ++j) {
				for (int k = cr; k < cl; ++k) {
					map[j][k] = i;
				}
			}
		}
		
		for(int i = 0; i < 1001; ++i) {
			for(int j = 0; j < 1001; ++j) {
				if(map[i][j] == 0) continue;
				answer[map[i][j] - 1] += 1;
			}
		}
		
		for(int a:answer) {
			bw.write(String.format("%d\n", a));
		}

		bw.flush();
		bw.close();
		br.close();
	}

}
