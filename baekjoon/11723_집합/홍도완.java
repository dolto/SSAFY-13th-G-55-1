import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	/// 시간제한: 1.5초
	/// 메모리제한: 448mb
	// 시간: 944ms
	// 메모리: 297mb
	//
	/// 아이디어
	// 입력량이 너무 많다... 그거 말고는 그냥, 비트연산으로 구하면 된다.
	public static void main(String[] args) throws IOException {
		final int N = Integer.parseInt(br.readLine().trim());
		int check = 0;
		for(int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int temp = 0;
			switch(st.nextToken()) {
			case "add":
				temp = 1 << Integer.parseInt(st.nextToken());
				if((check & temp) == 0) check += temp;
				break;
			case "remove":
				temp = 1 << Integer.parseInt(st.nextToken());
				if((check & temp) == temp) check -= temp;
				break;
			case "check":
				temp = 1 << Integer.parseInt(st.nextToken());
				if((check & temp) == temp) bw.write("1\n");
				else bw.write("0\n");
				break;
			case "toggle":
				temp = 1 << Integer.parseInt(st.nextToken());
				if((check & temp) == temp) check -= temp;
				else check += temp;
				break;
			case "all":
				check = Integer.MAX_VALUE;
				break;
			case "empty":
				check = 0;
				break;
			}
		}
		bw.flush();
		br.close();
		bw.close();
	}
}
