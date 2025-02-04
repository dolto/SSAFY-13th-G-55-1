# 사용자가 적은 빙고판
bingo = []

for i in range (5) : 
    bingo.append(list(map(int,input().split())))

# 사회자가 불러주는 번호
call = []

for i in range (5) : 
    call.append(list(map(int,input().split())))


# 빙고 현황을 알수 있는 hash map
#  key : "r0"이란 0번째 row, "c0"이란 0번째 column, "cr"이란 오른쪽 아래로 내려가는 대각선, "cl" 이란 왼쪽 아래로 내려가는 대각선을 뜻한다. 
#  value : 해당 라인에서 몇개가 선택된 개수이다. 
h = {"r0" : 0,
    "r1" : 0,
    "r2" : 0,
    "r3" : 0,
    "r4" : 0,
    "c0" : 0,
    "c1" : 0,
    "c2" : 0,
    "c3" : 0,
    "c4" : 0,
    "cr" : 0,
    "cl" : 0}

#빙고는 1~25까지의 숫자를 사용하므로 해당 숫자의 좌표를 임시 Array에 저장한다. 
# 개수를 26을 한 이유는 인덱스 번호와 숫자 번호를 일치시키기 위함으로 0번째 인덱스 값은 쓰지 않는다. 
tmpArr = [(0,0)] * 26

# bingo input data를 하나씩 읽어 임시 Array에 좌표를 저장한다. 
for i in range(5) : 
    for j  in range(5) : 
        tmpArr[bingo[i][j]] = (i,j)

# 지금까지 선택된 숫자 개수
result = 0 

# 빙고개수
cnt = 0 

# 빙고인지 체크하는 메소드
def checkBingo(location) : 
    global cnt
    h[location] +=1 
    # 해당 라인에 선택된 개수가 5개라면 빙고로 판단하여 cnt에 1을 더한다. 
    if h[location] == 5 : 
        cnt +=1 
    return  

# 빙고 개수가 3개가 되면 for문을 빠져나올 장치이다. 
Flag = False 
for i in range(5) : 
    for j in range(5) : 
        result +=1 
	# 사회자가 불러준 번호를 통해 임시 Array에서 좌표를 받아온다. 
        r,c = tmpArr[call[i][j]]
	
	# hash map의 key로 사용될 임시 Array 2이다. 
	# 어떤 숫자가 나오든 row기준 라인과 column 기준 라인의 선택 숫자는 항상 더해져야 하므로 임시 Array2에 default로 저장한다. 
        tmpArr2 = ["r" + str(r), "c" + str(c)]

	# 컬럼 좌표와 로우 좌표가 같다는 것은 오른쪽 아래로 내려가는 대각선을 지난다는 의미이므로 임시 Array2에 추가한다. 
        if r==c : 
            tmpArr2.append("cr")

	# 컬럼 좌표와 로우 좌표의 합이 4라는 것은 왼쪽 아래로 내려가는 대각선을 지난다는 의미이므로 임시 Array2에 추가한다. 
        if r+c == 4 : 
            tmpArr2.append("cl")

	# 임시 Array2에 저장된 요소를 checkBingo 메서드를 통해 확인한다.  
        for k in tmpArr2 : 
            checkBingo(k)
	    # 빙고 개수가 3이라면 Flag를 True로 변경한다.
            if cnt==3 : 
                Flag = True 
	# Flag가 True라는 것은 빙고 개수가 3개라는 의미이므로 반복문을 종료한다. 
        if Flag : 
            break
    # Flag가 True라는 것은 빙고 개수가 3개라는 의미이므로 반복문을 종료한다. 
    if Flag : 
        break 
        

print(result)