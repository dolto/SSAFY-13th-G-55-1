import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/// 아이디어
///세로벽에 부딫히면 x*-1 가로벽에 부딫히면 y*-1
///마치 2차원에서 대각선으로 이동하는 지점을 처리하려면, 너무 어렵다.
///문제를 1차원에서 벽에 부딫히는 두 점이라고 생각하면 쉽다.
public class 홍도완 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(br.readLine().trim());
		
		//우선 x와 y를 각각 0으로 옮겨준다
		int xt = t + x;
		int yt = t + y;
		
		//각 변의 t를 길이의 두배만큼 나누고 나머지를 구한다.
		//이를 통해, 각각 0좌표를 유지하고, 반복적인 이동을 한번의 연산으로 처리할 수 있다. 
		int px = xt % (w * 2);
		int py = yt % (h * 2);
		
		//만약 남은 시간이 각 길이보다 클 경우, 최대까지 이동했다가 남은 시간만큼 뒤로 이동한다는 의미
		//그게 아니라면 남은 시간만큼 앞으로 이동한다는 의미
		if(px >= w) {
			px -= w;
			x = w - px;
		}else {
			x = px;
		}
		if(py > h) {
			py -= h;
			y = h - py;
		}else {
			y = py;
		}
		
		

		bw.write(String.format("%d %d\n", x,y));
		br.close();
		bw.flush();
		bw.close();
	}
}
