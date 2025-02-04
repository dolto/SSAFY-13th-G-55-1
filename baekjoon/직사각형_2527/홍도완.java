import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/// 아이디어
///두 직사각형의 x와 q의 범위가 겹치는지, 확인하고, y와 p의 범위가 겹치는지 확인한다.
/// 위의 두 조건이 모두 성립하면 d가 아니다
///x와 q의 겹치는 범위가 0이거나, y와 p의 겹치는 범위가 0이라면 a가 아니다
///	위의 두 조건이 하나만 성립하면 b가 성립한다
/// 위의 두 조건이 모두 성립하면 c가 성립한다
public class 홍도완 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int t = 0; t < 4; ++t) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken()),
					y1 = Integer.parseInt(st.nextToken()),
					p1 = Integer.parseInt(st.nextToken()),
					q1 = Integer.parseInt(st.nextToken());
			
			int x2 = Integer.parseInt(st.nextToken()),
					y2 = Integer.parseInt(st.nextToken()),
					p2 = Integer.parseInt(st.nextToken()),
					q2 = Integer.parseInt(st.nextToken());
			
			// p의 최솟값을 x의 최댓값으로 뺀 값이 0 이상이면 겹치는 선분
			int xp = Math.min(p1, p2) - Math.max(x1, x2);
			int yq = Math.min(q1, q2) - Math.max(y1, y2);
			
			if(xp < 0 || yq < 0) {
				bw.write("d\n");
			}else if(xp == 0 && yq == 0) {
				bw.write("c\n");
			}else if(xp == 0 || yq == 0) {
				bw.write("b\n");
			}else {
				bw.write("a\n");
			}
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
