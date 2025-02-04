# 아이디어 
# array 요소를 하나씩 읽어가며 0이 아닌 숫자가 나타나면 해당 숫자를 remove하고 이동할 인덱스로 insert한다. 


n = int(input())

# 주어진 input data를 array에 담는다.
arr = list(map(int, input().split()))

# 1부터 5까지 순서대로 array에 담는다
result = [i for i in range(1,n+1)]

for i in range(n) : 
    # arr[i] 값이 0이 아니라면
    if arr[i] : 
	# result에서 해당 index의 숫자를 remove한다. 
        result.remove(i+1)
	# result에서 해당 index의 숫자를 자리에 맞게 insert한다. 
        result.insert(i-arr[i], i+1)

for j in result : 
    print(j, end = " ")
