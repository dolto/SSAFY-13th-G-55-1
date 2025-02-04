import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 홍도완 {
	/// 아이디어
	///기둥을 왼쪽부터 배치순서대로 정렬 한다 
	///현재 높이(h) 현재 위치(l) 를 첫 기둥을 기준으로 잡는다.
	///현재 높이보다 큰 기둥이 나올 때 까지 tl을 구한다.
	///현재 높이보다 큰 기둥이 나오면, tl - l로 가로의 크기를 구하고, 이를 h와 곱하여 answer에 더한 뒤 h를 갱신한다
	///위를 반복하되, 마지막 기둥까지 h가 갱신되지 않는다면, 계산을 멈추고, 마지막 기둥부터 오른쪽으로 체크하며 answer를 더한다
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		Pillar[] ps = new Pillar[n];
		for(int i = 0; i < n; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ps[i]= new Pillar(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(ps);
		
		int h = 0;
		int l = 0;
		int lastIndex = 0;

		int answer = 0;
		
		for(int i = 0; i < n; ++i) {
			if(ps[i].H >= h) {
				int tl = ps[i].L;
				answer += (tl - l) * h;
//				bw.write(String.format("%d\n", (tl - l) * h));
				l = ps[i].L;
				h = ps[i].H;
				lastIndex = i;
			}
		}
//		bw.write(String.format("%d\n", answer));
		
//		answer = 0;
		h = ps[n - 1].H;
		l = ps[n - 1].L + 1;
		for(int i = n - 1; i >= lastIndex; --i) {
			if(ps[i].H >= h) {
				int tl = ps[i].L + 1;
				answer += (l - tl) * h;
//				bw.write(String.format("%d\n", (l - tl) * h));
				l = ps[i].L + 1;
				h = ps[i].H;
			}			
		}
		
		answer += ps[lastIndex].H;
		
		bw.write(String.format("%d", answer));

		br.close();
		bw.flush();
		bw.close();
	}
}

class Pillar implements Comparable<Pillar>{
	int L, H;
	Pillar(int l, int h){
		L = l;
		H = h;
	}
	
	@Override
	public int compareTo(Pillar o) {
		return Integer.compare(L, o.L);
	}
}
