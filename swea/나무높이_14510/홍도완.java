import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	/// 아이디어
	/// 모든 나무를 List에 받으면서, max를 구한다
	/// 모든 나무에서 max크기를 가진 나무를 뺀 필터링을 거친다.
	/// 모든 나무를 max - 나무높이로 성장에 필요한 수치를 구한다.
	/// 모든 나무를 2로 나눈 값을 더하여 2가 들어갈 수 있는 개수를 구한다.
	/// 그 나머지는 1이 들어가야 하는 개수를 구한다.
	/// 모든 나무의 높이 합도 구한다.
	/// 모든 나무의 높이 합에 3을 나눈 값을 구하며, 나머지가 1 이상이면 거기에 + 1을 해준다.
	/// (왜냐하면, 2가 들어가지 못하는 경우가 한번은 존재하기 때문임을 의미하기 때문이다)
	/// 일단 2가 들어갈 수 있는 개수가 1이 들어가야 하는 개수의 이상이라면, 2가 들어가는 횟수를 다 써도, 1이 더 들어가야 함을 보장한다.
	/// 즉, need2 >= need1 이라면 div * 2 을 하고, 만약 sum % 3이 1이라면 1일을 더 넣어야 한다는 의미이므로, +1을 해준다.
	/// 그게 아니라면 need1 * 2 - 1 이 정답이다
	/// (이유는 1, 2, 1, 2 ... 순서로 물을 주기 때문에 1을 마지막으로 준다면 홀수일이기 때문이다.)
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; ++t) {
			int N = Integer.parseInt(br.readLine().trim());
			List<Integer> woods = new ArrayList<>(N);
			st = new StringTokenizer(br.readLine());
			int maxtemp = 0;
			int sum = 0;
			int div = 0;
			int need = 0;
			int ones = 0;
			for (int n = 0; n < N; ++n) {
				int temp = Integer.parseInt(st.nextToken());
				woods.add(temp);
				maxtemp = Math.max(maxtemp, temp);
			}
			final int max = maxtemp;
			woods = woods.stream()
					.filter(i -> i != max)
					.map(i -> max - i).collect(Collectors.toList());
			for(int w:woods) {
				need += w / 2;
				ones += w % 2;
				sum += w;
			}
			div = sum / 3;
			div += Math.min(sum % 3, 1);
			
			int answer;
			if(need >= ones) {
				answer = Math.min(Math.max(1, div), sum) * 2;
				if(sum % 3 == 1) {
					answer -= 1;
				}
			}else {
				answer = ones * 2 - 1;
			}
			bw.write(String.format("#%d %d\n", t,answer));
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
