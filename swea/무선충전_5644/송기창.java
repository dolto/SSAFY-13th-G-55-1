import java.util.*;
import java.io.*;

public class Solution {
    static int[] dx = { 0, 0, 1, 0, -1 };
    static int[] dy = { 0, -1, 0, 1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스
        int tcn = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= tcn; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // 사용자 이동 횟수, BC의 수
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 각 사용자의 이동 정보를 저장할 리스트
            List<Integer> user1 = new ArrayList<>();
            List<Integer> user2 = new ArrayList<>();
            
            // user1 정보 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                user1.add(Integer.parseInt(st.nextToken()));
            }

            // user2 정보 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                user2.add(Integer.parseInt(st.nextToken()));
            }

            // BC 정보 저장
            int[][] bcInfo = new int[m][5];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                // 좌표는 -1 해주는거 주의
                bcInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
                bcInfo[i][1] = Integer.parseInt(st.nextToken()) - 1;
                bcInfo[i][2] = Integer.parseInt(st.nextToken());
                bcInfo[i][3] = Integer.parseInt(st.nextToken());
                bcInfo[i][4] = i;
            }

            // 사용자 위치 초기화
            int x1 = 0, y1 = 0;
            int x2 = 9, y2 = 9;
            
            // 최종적으로 합친 답
            int ans = 0;

            // 충전량 계산을 위한 리스트 생성
            List<int[]> bc1 = new ArrayList<>();
            List<int[]> bc2 = new ArrayList<>();

            // 사용자 이동을 따라가면서 BC 확인
            for (int i = 0; i < m; i++) {
            	// user1이 현재 위치에서 충전 가능한지 확인
                if (Math.abs(bcInfo[i][0] - x1) + Math.abs(bcInfo[i][1] - y1) <= bcInfo[i][2]) {
                    bc1.add(new int[] { bcInfo[i][4], bcInfo[i][3] });
                }
                // user2가 현재 위치에서 충전 가능한지 확인
                if (Math.abs(bcInfo[i][0] - x2) + Math.abs(bcInfo[i][1] - y2) <= bcInfo[i][2]) {
                    bc2.add(new int[] { bcInfo[i][4], bcInfo[i][3] });
                }
            }

            // 가장 최적의 조합 찾기
            int maxCharge = 0;
            for (int j = 0; j < bc1.size(); j++) {
                for (int k = 0; k < bc2.size(); k++) {
                	// 같은 BC면 충전량 반씩 나누기
                    if (bc1.get(j)[0] == bc2.get(k)[0]) {
                        maxCharge = Math.max(maxCharge, bc1.get(j)[1] / 2 + bc2.get(k)[1] / 2);
                    }
                    // 다른 BC 선택하면 합치기
                    else {
                        maxCharge = Math.max(maxCharge, bc1.get(j)[1] + bc2.get(k)[1]);
                    }
                }
            }
            
            // 한 사용자만 BC에 접속하는 경우
            for (int j = 0; j < bc1.size(); j++) {
                maxCharge = Math.max(maxCharge, bc1.get(j)[1]);
            }
            for (int j = 0; j < bc2.size(); j++) {
                maxCharge = Math.max(maxCharge, bc2.get(j)[1]);
            }

            ans += maxCharge;

            // 이동 및 충전
            for (int i = 0; i < n; i++) {
            	// user1, user2 위치  갱신
                x1 += dx[user1.get(i)];
                y1 += dy[user1.get(i)];
                x2 += dx[user2.get(i)];
                y2 += dy[user2.get(i)];

                bc1.clear();
                bc2.clear();
                maxCharge = 0;

                // 각 사용자 위치에서 접속 가능한 BC 찾기
                for (int j = 0; j < m; j++) {
                    if (Math.abs(bcInfo[j][0] - x1) + Math.abs(bcInfo[j][1] - y1) <= bcInfo[j][2]) {
                        bc1.add(new int[] { bcInfo[j][4], bcInfo[j][3] });
                    }
                    if (Math.abs(bcInfo[j][0] - x2) + Math.abs(bcInfo[j][1] - y2) <= bcInfo[j][2]) {
                        bc2.add(new int[] { bcInfo[j][4], bcInfo[j][3] });
                    }
                }

                // 최적 충전량 계산
                for (int j = 0; j < bc1.size(); j++) {
                    for (int k = 0; k < bc2.size(); k++) {
                        if (bc1.get(j)[0] == bc2.get(k)[0]) {
                            maxCharge = Math.max(maxCharge, bc1.get(j)[1] / 2 + bc2.get(k)[1] / 2);
                        } else {
                            maxCharge = Math.max(maxCharge, bc1.get(j)[1] + bc2.get(k)[1]);
                        }
                    }
                }
                for (int j = 0; j < bc1.size(); j++) {
                    maxCharge = Math.max(maxCharge, bc1.get(j)[1]);
                }
                for (int j = 0; j < bc2.size(); j++) {
                    maxCharge = Math.max(maxCharge, bc2.get(j)[1]);
                }

                ans += maxCharge;
            }

            System.out.printf("#%d %d\n", tc, ans);
        }
    }
}
