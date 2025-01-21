import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
///
// 아이디어
// 최소 이진트리와 최대 이진트리를 생성한다
// 최대이진트리부터 값을 집어넣고, 그 다음 최소이진트리에 값을 넣는다.
// 값을 넣을 때마다 최대이진트리의 값이 최소이진트리의 값보다 크다면 바꾼다.
// 그러면 최대 이진트리의 가장 상단의 값에는 언제나 중간값이 들어가게된다.
class Solution
{
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
    int size = Integer.parseInt(br.readLine().trim());
    StringTokenizer st = new StringTokenizer(br.readLine());
    br.close();
 
    // 최소 이진트리와 최대 이진트리 생성
    PriorityQueue<Integer> minQ = new PriorityQueue<>(size);
    PriorityQueue<Integer> maxQ = new PriorityQueue<>(size, Collections.reverseOrder());
 
    for (int i = 0; i < size; ++i) {
      // 최대 이진트리는 최소이진트리의 크기보다 작아지면 안됨
      if (minQ.size() == maxQ.size()) {
        maxQ.add(Integer.parseInt(st.nextToken()));
      } else {
        minQ.add(Integer.parseInt(st.nextToken()));
      }

      // 둘중 하나라도 비어있으면 비교가 불가하니, 예외처리
      if (!maxQ.isEmpty() && !minQ.isEmpty()) {
        // 최대이진트리와 최소이진트리의 최상단값을 비교하고 언제나 최소이진트리의 최상단값이 더 크도록 유지한다
        if (maxQ.peek() > minQ.peek()) {
          int max = maxQ.remove();
          int min = minQ.remove();
          maxQ.add(min);
          minQ.add(max);
        }
      }
    }
 
    // 모든 연산이 끝나면 중간값을 호출한다.
    bw.write(String.format("%d", maxQ.peek()));
    bw.flush();
    bw.close();
  }
 
}
