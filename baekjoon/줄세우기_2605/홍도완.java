import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//아이디어
//0~n까지의 숫자를 가진 리스트를 만들면서
//규칙에 맞춰서 각 번호의 학생의 위치를 이동한다
//(그래도 되 는이유는 학생의 처음 순번 이하의 인덱스만 참조하기 때문이다.)
//list의 각 위치를 +1 한 값을 출력한다.
public class 홍도완 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine().trim());
    int[] list = new int[n];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; ++i) {
      list[i] = i;
      int move = Integer.parseInt(st.nextToken());
      for (int j = 0; j < move; ++j) {
        int temp = list[i - j];
        list[i - j] = list[i - j - 1];
        list[i - j - 1] = temp;
      }
    }
    // 1 2 3 4 5
    // 1
    // 2 1
    // 2 3 1
    // 4 2 3 1
    // 4 2 5 3 1
    for (int i = 0; i < n; ++i) {
      bw.write(String.format("%d ", list[i] + 1));
    }
    br.close();
    bw.flush();
    bw.close();
  }
}
