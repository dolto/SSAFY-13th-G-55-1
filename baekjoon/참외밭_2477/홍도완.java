import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/// 아이디어
///일단 리스트에 각 변의 길이를 넣는다
///각 변의 길이중 가로 혹은 세로가 가장 큰 변이 연속된 부분의 앞 인덱스를 start에 넣는다.
///start부터 시작하여 한바퀴를 돌고, 두번째와 세번째 변을 곱하고, 다섯번째와 여섯번째의 변을 곱한 값을 더한다
///그 값을 n(참외)에 곱한다
///다른 사람꺼 보니까 %연산을 사용하던데 그것도 이런 방식을 이쁘게 푼 것으로보아, 대충 이러한 방법이 (아마) 정석이 맞는 거 같다
public class 홍도완 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine().trim());
		int start = 0;
		int maxx = 0,maxy=0;
		Line currentLine = null;
		for(int i = 0; i < 6; ++i) {
			st = new StringTokenizer(br.readLine());
			currentLine = new Line(i,Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			if(currentLine.direction) {
				maxy = Math.max(maxy, currentLine.lenght);
			}else {
				maxx = Math.max(maxx, currentLine.lenght);				
			}
		}
		
		Line startLine = currentLine;
		for(int i = 0; i < 6; ++i) {
			Line temp = currentLine.next();
			if(currentLine.direction && currentLine.lenght == maxy) {
				if(temp.lenght == maxx) {
					startLine = temp;
				}
			}else if(currentLine.lenght == maxx) {
				if(temp.lenght == maxy) {
					startLine = temp;
				}
			}
			currentLine = temp;
		}
		
		int a = startLine.lenght;
		startLine = startLine.next();
		a *= startLine.lenght;
		
		startLine = startLine.next().next();
		int b = startLine.lenght;
		startLine = startLine.next();
		b *= startLine.lenght;
		
		int answer = (a + b) * n;
		bw.write(String.format("%d", answer));
		br.close();
		bw.flush();
		bw.close();
	}
}

class Line{
	static Line[] lines = new Line[6];
	int lenght;
	boolean direction;
	int index;
	
	Line(int index, int direction, int length) {
		this.index = index;
		this.lenght = length;
		switch(direction) {
		case 1:
			this.direction = true;
			break;
		case 2:
			this.direction = true;
			break;
		default:
			this.direction = false;
			break;
		}
		lines[index] = this;
	}
	Line next() {
		if(index >= 5) {
			return lines[0];
		}else {
			return lines[index + 1];
		}
	}
}
