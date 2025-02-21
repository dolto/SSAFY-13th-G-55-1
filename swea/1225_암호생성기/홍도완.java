import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	/// 시간제한: 20초
	/// 메모리제한: 256mb
	/// 시간: 90ms
	/// 메모리: 25mb
	//
	/// 아이디어
	// 다만, 각 요소의 수가 int범위라는 것이 걸린다... 너무 많은 숫자라면 위험하지 않은가?
	// 1,2,3,4,5 씩 작아진다... 한 사이클이 돌면, 다시 1부터 5까지 감소시킨다.
	// 1, 4, 2, 5, 3, 1 씩 줄어든다... 같은 위치로 돌아오면서 같은 수를 제거하는 타이밍은 1,4,2,5,3으로 15이다.
	// 가장 작은 요소를 15로 나눈 수에 그 요소가 0이라면 -1하고, 거기에 15를 곱하여, 모든 요소에 뺄셈한다.
	// 그 후 시뮬레이션을 돌리면 정답이 나온다.

	public static void main(String[] args) throws IOException {
		final int T = 10;
		for (int t = 1; t <= T; ++t) {
			final int tt = Integer.parseInt(br.readLine());
			int[] code = new int[8];
			StringTokenizer st = new StringTokenizer(br.readLine());
			int min = Integer.MAX_VALUE;
			int mindex= 0;
			for (int i = 0; i < 8; ++i) {
				code[i] = Integer.parseInt(st.nextToken());
				if(min > code[i]) {
					min = code[i];
					mindex = i;
				}
			}
			
			int sub = min/15 * 15;
			if(code[mindex] == sub) {
				sub -= 15;
			}
			if(sub < 0) {
				sub = 0;
			}
			
			for(int i = 0; i < 8; ++i) {
				code[i] -= sub;
			}
			
			int pivot = 0;
			sub = 0;
			while(true) {
				code[pivot] -= ++sub;
				if(code[pivot] <= 0) {
					code[pivot++] = 0;
					break;
				}
				pivot += 1;
				sub %= 5;
				pivot %= 8;
			}
			
			bw.write(String.format("#%d ", tt));
			for(int i = 0; i < 8; ++i) {
				bw.write(String.format("%d ",code[pivot++]));
				pivot %= 8;
			}
			bw.write("\n");
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
