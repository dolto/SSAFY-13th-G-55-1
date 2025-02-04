    /// 아이디어
    // 모든 경우의 수를 탐색한다.
    
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static List<Integer> answer = new ArrayList<>();
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            // 일곱 난쟁이 받을 배열 정리
            int[] nums = new int[9];
    
            // 난쟁이 키 저장
            for (int i=0; i < 9; i++) {
                nums[i] = Integer.parseInt(br.readLine());
            }
    
            // 오름차순 정령
            Arrays.sort(nums);
    
            // 백트랙킹 알고리즘 호출
            backtrack(nums, 0, 0);
    
            for (int num : answer) {
                bw.write(""+num+"\n");
            }
    
            bw.flush();
    
        }
    
        public static boolean backtrack(int[] nums, int cur, int tall) {
    
            for (int i=cur; i < 9; i++) {
                // 현재 넣을 난쟁이의 키와 현재까지의 난쟁이들의 키의 합이 100일 경우와 현재까지의 난쟁이들의 수가 6인 경우 true 반환
                if (tall + nums[i] == 100 && answer.size() == 6) {
                    answer.add(nums[i]);
                    return true;
                }
                // 다음 난쟁이의 키와 현재까지의 난쟁이들의 키의 합이 100 미만일 경우, answer에 추가 후 재귀호출
                else if (tall + nums[i] < 100) {
                    answer.add(nums[i]);
                    if (backtrack(nums, i+1, tall+nums[i])) return true;
                    // 다음 난쟁이의 키를 위해 최근 추가한 난쟁이 키 제거
                    answer.remove(answer.size() - 1);
                }
            }
    
            return false;
        }
    }