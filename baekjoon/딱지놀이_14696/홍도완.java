import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Queue {

	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	/// 아이디어
	// 별,동그라미,네모,세모는 각각 4,3,2,1로 줄어드는 형태로 표현한다
	// 또한 각 딱지의 등급(숫자)이 높은게 많다면 그 이하의 딱지의 개수는 무시한다.
	// 이를 반복문으로 표현할 수 있다.
	public static void main(String[] args) throws IOException {
		int round = Integer.parseInt(br.readLine());
		ROUND: for (int i = 0; i < round; ++i) {
			int[] A = new int[4];
			int[] B = new int[4];
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			for (int j = 0; j < a; ++j) {
				A[Integer.parseInt(st.nextToken()) - 1] += 1;
			}

			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken());
			for (int j = 0; j < b; ++j) {
				B[Integer.parseInt(st.nextToken()) - 1] += 1;
			}

			for (int j = 3; j >= 0; --j) {
				if (A[j] > B[j]) {
					bw.write("A\n");
					continue ROUND;
				} else if (A[j] < B[j]) {
					bw.write("B\n");
					continue ROUND;
				}
			}
			bw.write("D\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
