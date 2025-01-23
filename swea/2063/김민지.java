import java.util.*;
public class p2063 {	
    public static void main(String[] args) {	
		    // input data 읽기위해 Scanner 생성
        Scanner sc = new Scanner(System.in);		
        
        // input data : 배열 개수
        int N = sc.nextInt();		
        
        // 배열의 중간값
        int middle = N / 2; 
        
        // 점수를 담을 배열을 생성
        int[] nums = new int[N];
        
        // input data : 점수 -> 배열에 점수 초기화		
        for (int i = 0; i < N; i++) {			
            nums[i] = sc.nextInt();		
        }	
        	
        // Array.sort를 이용하여 정렬
        Arrays.sort(nums);		
        
        // 점수 배열에서 중간값 출력
        System.out.println(nums[middle]);	
    }
}
