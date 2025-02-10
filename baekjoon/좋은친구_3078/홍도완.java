import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/// 아이디어
	/// 학생이름은 최대 20이므로 int[21] 변수를 하나 만든다.
	/// 학생들 정보를 k + 1개 만큼만 큐에 저장하면서 int[글자수] + 1한다.
	/// 학생을 하나 빼고, int[글자수] -= 1을 해주고, answer에 int[글자수]를 더해준다.
	/// 학생을 하나 넣고, int[글자수]에 +1 해준다.
	/// answer는 n과 k가 둘 다 300000일 때 int의 표현을 초과하는 수가 저장된다.
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()) + 1;
		int n = 0;
		int[] counter = new int[21];
		Queue<String> queue = new ArrayDeque<>(K);
		long answer = 0;

		do {
			if (queue.size() != K && n != N) {
				String temp = br.readLine().trim();
				queue.add(temp);
				n += 1;
				counter[temp.length()] += 1;
			} else {
				String temp = queue.poll();
				counter[temp.length()] -= 1;
				answer += counter[temp.length()];
			}
		} while (!queue.isEmpty());
		
		bw.write(String.format("%d", answer));
		br.close();
		bw.flush();
		bw.close();
	}
}
