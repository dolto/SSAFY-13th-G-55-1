import java.io.*;
import java.util.*;

public class Main {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	/// 아이디어
	// x부터 시작해서 위로 이동하다, 좌,우로 이동가능한 경우가 생기면 해당 방향으로 이동한다.
	// 이 때 좌,우로 동시에 이동가능한 선택의 경우는 없다. (가로지르는 경우가 없다와 동치)
	// 그러다 위로 이동가능한 경우가 생기면, 다른 막대를 가로지르는 경우가 없으므로, 위로 이동한다.
	// 이를 반복하면 된다.
	static enum Derect {
		Up, Left, Right
	}

	public static void main(String[] args) throws IOException {
		final int T = 10;
		for (int i = 1; i <= T; ++i) {
			int t = Integer.parseInt(br.readLine().trim());
			// 세로 크기를 알 수 없다...
			ArrayList<char[]> map = new ArrayList<>();
			int xr = 0, xc = 0;
			while (xr == 0) {
				char[] line = br.readLine().toCharArray();
				for (int j = 0; j < line.length; j += 2) {
//    				System.out.println(line[j]);
					if (line[j] == '2') {
						xr = map.size() - 1;
						xc = j;
					}
				}
				map.add(line);
			}
			int lineSize = map.get(0).length;
			Derect derct = Derect.Up;
			while (xr != 0) {
				switch (derct) {
				case Up:
					if (xc >= 2 && map.get(xr)[xc - 2] == '1') {
//						bw.write(String.format("turn left whrer: %d %d %c\n", xr,xc ,map.get(xr)[xc]));
						derct = Derect.Left;
						xc -= 2;
					} else if (xc + 2 < lineSize && map.get(xr)[xc + 2] == '1') {
//						bw.write(String.format("turn right whrer: %d %d %c\n", xr,xc ,map.get(xr)[xc]));
						derct = Derect.Right;
						xc += 2;
					} else
						--xr;
					break;
				case Left:
					if (xr >= 1 && map.get(xr - 1)[xc] == '1') {
						derct = Derect.Up;
						xr -= 1;
					} else
						xc -= 2;
					break;
				case Right:
					if (xr >= 1 && map.get(xr - 1)[xc] == '1') {
						derct = Derect.Up;
						xr -= 1;
					} else
						xc += 2;
					break;
				}
			}

			bw.write(String.format("#%d %d\n", t, xc / 2));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
