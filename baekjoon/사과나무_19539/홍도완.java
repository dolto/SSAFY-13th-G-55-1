import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Temp {
	/// 아이디어
	/// 각 요소의 전체를 더한다
	/// 전체를 n * 1 + n * 2 로 나눈다.
	/// 3으로 나누면 n을 구할 수 있다. (나머지가 있다면 실패)
	/// (12 / 3 = 4 = 1 * 4 + 2 * 4 = 12)
	/// 각 요소에 2가 몇 번 들어갈 수 있는지는 각 요소에 2를 나누면 된다.
	/// n은 물을 주는 횟수를 의미하며, 2가 들어갈 수 있는 수 이하라면 성공한다.
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine().trim());
		st = new StringTokenizer(br.readLine());
		int sum = 0;
		int needTew = 0;
		for(int i = 0; i < N; ++i) {
			int temp = Integer.parseInt(st.nextToken());
			needTew += temp / 2;
			sum += temp;
		}
		if(sum % 3 == 0) {
			sum /= 3;
			if(sum <= needTew) {
				bw.write("YES");
			}else {
				bw.write("NO");
			}
		}else {
			bw.write("NO");
		}

		br.close();
		bw.flush();
		bw.close();
	}
}
