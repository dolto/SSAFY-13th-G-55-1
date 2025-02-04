import java.util.*;
public class p2063 {	
    public static void main(String[] args) {	
		    // 아이디어 
		    // 중간값을 구하기 위해서 배열을 정렬하고 그 배열의 길이의 절반을 인덱스로 사용하여 출력
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