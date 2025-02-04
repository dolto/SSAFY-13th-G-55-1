# 아이디어 
# 9개의 input data 중에 7개를 뽑아 조합을 만든 후 합이 100이라면 100을 만든 조합을 결과값으로 반환한다. 

arr = []

# input data를 array에 담는다. --> 변수명 : arr
for i in range(9) : 
    arr.append(int(input()))



def backtracking(tmpArr, start) : 
    # backtracking으로 길이가 7인 array를 만든다. 
    if len(tmpArr) == 7 : 
        # array 길이가 7이 되면 합이 100이 되는지 확인한다. 
        if sum(tmpArr) == 100 : 
	    # 합이 100이라면 해당 조합을 반환한다. 
            return tmpArr
        else : 
	    # 합이 100이 아니라면 False를 반환한다. 	
            return False

    for i in range(start,9) :
	# 임시 array에 input data를 하나씩 append 한다. 
        tmpArr.append(arr[i])
	# backtracking
        resultTmp =  backtracking(tmpArr, i+1) 
	# backtracking의 반환값이 조합을 반환할 경우 for문을 끝내고 해당 조합을 반환한다. 
        if resultTmp : 
            return resultTmp
	# 위의 backtracking이 끝나면 임시 array에서 pop한다. 
        tmpArr.pop()
    return None



result  = backtracking([],0)
# backtracking의 반환한 조합을 정렬한다. 
result.sort()
# 정렬한 결과값을 하나씩 출력한다. 
for i in result : 
    print(i)