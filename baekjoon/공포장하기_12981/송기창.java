import java.util.*;
import java.io.*;

// 아이디어
// 그리디 알고리즘 문제입니다.
// 크게 처리할 수 있는 부분을 먼저 해결해 두고 남은 공을 처리하면 됩니다.
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		// r,g,b 공의 수를 입력받습니다.
		StringTokenizer st=new StringTokenizer(br.readLine());
		int r=Integer.parseInt(st.nextToken());
		int g=Integer.parseInt(st.nextToken());
		int b=Integer.parseInt(st.nextToken());
		
		int ans=0;
		// 먼저 3개씩 넣을 수 있으면 다 넣어버리기 이때 각 공은 무조건 3개 미만으로 남습니다.
		ans+=(r/3);
		r%=3;
		ans+=(g/3);
		g%=3;
		ans+=(b/3);
		b%=3;
		
		// 남은 공 중 한개씩 3개 묶을 수 있으면 다 넣어버리기
		int min=Integer.MAX_VALUE;
		if(min>r) min=r;
		if(min>g) min=g;
		if(min>b) min=b;
		r-=min; g-=min; b-=min;
		ans += min;
		
		// 나머지 공 해결하기, 현재 남은 공의 종류는 2개
		if(r+g+b==0) {
			// 남은 공이 없으면 걍 건너뛰기
		}
		// 남은 공이 2개 이하면 같은색 1개나 2개이거나 서로 다른 색 1 1 이기 때문에 1박스로 해결가능
		else if(r+g+b<=2) {
			ans++;
		}
		// 그 밖의 경우 공이 2 2 0, 2 1 0인 경우만 존재하는데 어떻게 남든 박스 2개 필요함
		else {
			ans+=2;
		}
		System.out.print(ans);
	}
}
