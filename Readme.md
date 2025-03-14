# 광주 13기 스터디 레포지토리입니다

## 참여자

<table>
    <tr height="140px">
        <td align="center" width="130px">
            <a href="https://github.com/dolto">
                <img height="100px" width="100px" src="https://avatars.githubusercontent.com/u/43398206?v=4"/>
            </a>
            <br />
            <a href="https://github.com/dolto">홍도완</a>
            <br />
            <img src="https://mazassumnida.wtf/api/mini/generate_badge?boj=dolto" />
            <br />
            <a href="https://www.acmicpc.net/user/dolto">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/dolto">solved.ac</a>
        </td>
        <td align="center" width="130px">
            <a href="https://github.com/babobabo112">
                <img height="100px" width="100px" src="https://avatars.githubusercontent.com/u/59717513?v=4"/>
            </a>
            <br />
            <a href="https://github.com/babobabo112">김민지</a>
            <br />
            <img src="https://mazassumnida.wtf/api/mini/generate_badge?boj=imp9221" />
            <br />
            <a href="https://www.acmicpc.net/user/imp9221">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/imp9221">solved.ac</a>
        </td>
        <!-- <td align="center" width="130px">
            <a href="https://github.com/Park-JuH">
                <img height="100px" width="100px" src="https://avatars.githubusercontent.com/u/83206160?v=4"/>
            </a>
            <br />
            <a href="https://github.com/Park-JuH">박주혁</a>
            <br />
            <img src="https://mazassumnida.wtf/api/mini/generate_badge?boj=think5213" />
            <br />
            <a href="https://www.acmicpc.net/user/think5213">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/think5213">solved.ac</a>
        </td> -->
        <td align="center" width="130px">
            <a href="https://github.com/skc-98">
                <img height="100px" width="100px" src="https://avatars.githubusercontent.com/u/143603126?v=4"/>
            </a>
            <br />
            <a href="https://github.com/skc-98">송기창</a>
            <br />
            <img src="https://mazassumnida.wtf/api/mini/generate_badge?boj=richkc2006" />
            <br />
            <a href="https://www.acmicpc.net/user/richkc2006">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/richkc2006">solved.ac</a>
        </td>
        <td align="center" width="130px">
            <a href="https://github.com/skc-98">
                <img height="100px" width="100px" src="https://avatars.githubusercontent.com/u/123109572?v=4"/>
            </a>
            <br />
            <a href="https://github.com/hobinBae">배호빈</a>
            <br />
            <img src="https://mazassumnida.wtf/api/mini/generate_badge?boj=qoghqls" />
            <br />
            <a href="https://www.acmicpc.net/user/qoghqls">Baekjoon</a>
            <br />
            <a href="https://solved.ac/profile/qoghqls">solved.ac</a>
        </td>
    </tr>
</table>

----

## 레포 규칙

- 코드를 작성하기 시작하거나 푸시를 하기 전 반드시 **git pull**을 해주시기 바랍니다.
- **root/문제출처/문제이름_문제번호** (ex) **beakjoon/문제이름_1000** ex2) **swea/문제이름_1000**) 디렉토리 안에 코드를 넣습니다.
- 추가 **root/문제출처/문제번호_문제이름** (ex) **beakjoon/1000_문제이름** ex2) **swea/1000_문제이름**) 디렉토리 안에 코드를 넣어도 됩니다.
- 코드 이름은**이름.확장자**로 해주시면 됩니다.  (ex) 홍도완.rs)

- #### 가급적 스스로 문제를 풀고나서 다른사람의 코드를 읽어주시기 바랍니다

## 문제 추가 예시 (선택)

- 상단의 문제디렉토리를 만들고, 그 안에 problem.md를 첨부한다.
- 사이트에 문제가 있기 때문에 링크만 남겨도 좋을 듯 싶습니다.
- ex)

```text
problem.md

link: https://www.acmicpc.net/problem/1120

```

- 추가적으로 hint<n>.txt를 만들어, 문제풀이가 어려운 학우를 위해서 길잡이를 해줄 수 있습니다.
- ex) hint1.txt

```text
  백트레킹으로는 시간초과가 나므로 다른 전략을 생각해 보시면 좋습니다.
```

## 코드 작성 규칙

- 가장 상단에 문제해결 전략 아이디어를 서술합니다.
- ex)

```js
  /// 아이디어
  // 입력받은 a,b중 짝수인 것을 모두 더한다.
  ... 코드
  
```

- 입출력의 방식에 대해선 굳이 서술하지 않는다.
- 입력이 많은 경우, 어떤 라인에서 어떤 입력을 받는 구간인지 명시한다.
- 전략의 핵심부분에 이 코드가 아이디어의 어떤 역할을 하는지 서술한다.
- ex)

```python
dfs(n, cnt){
  # 종료처리
  if n == N:
    # 최댓값을 정답에 저장함
    ans = Math.max(cnt, ans)
    return

  # 동작 처리
  ... 코드
}
  
```

_..수정 예정_
