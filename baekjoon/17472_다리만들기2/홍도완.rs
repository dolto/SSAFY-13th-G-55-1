use std::{
    collections::{HashMap, HashSet, VecDeque},
    io::{stdin, stdout, BufReader, BufWriter, Read, Write},
    mem::take,
};

/// 아이디어
// 일단 섬들을 분류한다. 땅이 사방으로 붙어있는 뭉탱이가 섬이다.
// 분류한 섬들의 외각과 방향을 저장하여, 다리를 지을 수 있는 후보를 결정한다.
// 각 섬의 다리 후보에 다리를 쭉 지어본다. 제약사항에 다리는 꺾일 수 없으며, 옆으로 붙어있어도 연결된 것으로 인정되지 않으므로, 섬을 만나지 않으면 실패, 만난다면 다리를 지을 수 있다.
// 각 섬의 다리를 지을 수 있는 최소 거리를 구한다.
// 이후 각 섬의 다리를 list로 전환한 다음, 모두 연결되었으면 리턴하는 백트레킹을 구현하면 끝
fn main() {
    let mut input = String::new();
    let mut br = BufReader::new(stdin());
    let mut bw = BufWriter::new(stdout());
    let offset = [(0, 1), (1, 0), (0, -1), (-1, 0)];

    br.read_to_string(&mut input).unwrap();
    let mut input = input
        .split_ascii_whitespace()
        .flat_map(|s| s.parse::<i32>());

    let n = input.next().unwrap() as usize;
    let m = input.next().unwrap() as usize;

    let mut map = vec![Vec::with_capacity(m); n];
    let mut ilands: Vec<Iland> = Vec::with_capacity(6);
    for i in 0..n {
        for _ in 0..m {
            map[i].push(input.next().unwrap());
        }
    }

    for i in 0..n {
        for j in 0..m {
            if map[i][j] == 1 {
                let mut queue = VecDeque::new();
                queue.push_back((i as i32, j as i32));
                let mut iland = Iland {
                    can_britge: Vec::new(),
                    britges: Some(Britges::HashMode(HashMap::with_capacity(6))),
                };
                map[i][j] = ilands.len() as i32 + 2;
                while !queue.is_empty() {
                    let (r, c) = queue.pop_front().unwrap();
                    for (or, oc) in offset {
                        let tr = r + or;
                        let tc = c + oc;
                        if tr >= 0 && tr < n as i32 && tc >= 0 && tc < m as i32 {
                            if map[tr as usize][tc as usize] == 0 {
                                // 섬이 다리를 지을 수 있는 표면과 방향
                                let temp_point = Bpoint {
                                    r: tr as usize,
                                    c: tc as usize,
                                    d: (or, oc),
                                };
                                // writeln!(bw, "{:?}", temp_point).unwrap();
                                iland.can_britge.push(temp_point);
                            } else if map[tr as usize][tc as usize] == 1 {
                                map[tr as usize][tc as usize] = map[i][j];
                                queue.push_back((tr, tc));
                            }
                        }
                    }
                }
                ilands.push(iland);
            }
        }
    }

    // for i in 0..n {
    //     for j in 0..m {
    //         write!(bw, "{} ", map[i][j]).unwrap();
    //     }
    //     writeln!(bw).unwrap();
    // }

    for sindex in 0..ilands.len() {
        let iland = &mut ilands[sindex];
        for b in iland.can_britge.iter() {
            let mut count = 0;
            let mut r = b.r as i32;
            let mut c = b.c as i32;
            while r >= 0 && r < n as i32 && c >= 0 && c < m as i32 {
                let tr = r as usize;
                let tc = c as usize;
                if map[tr][tc] != 0 {
                    if count > 1 {
                        let index = map[tr][tc] as usize - 2_usize;
                        if let Britges::HashMode(hash) = &mut iland.britges.as_mut().unwrap() {
                            let britge = hash.entry(index).or_insert(i32::MAX);
                            if *britge > count {
                                *britge = count;
                            }
                        }
                    }
                    break;
                }
                count += 1;
                r += b.d.0;
                c += b.d.1;
            }
        }
    }

    for index in 0..ilands.len() {
        let btemp = take(&mut ilands[index].britges);
        if let Britges::HashMode(hash) = btemp.unwrap() {
            let temp: Vec<(usize, i32)> = hash.into_iter().collect();
            ilands[index].britges = Some(Britges::VecMode(temp));
        }
    }

    let mut connect = vec![
        Connect {
            connect: HashSet::new(),
            visited: false
        };
        ilands.len()
    ];
    let answer = min_connect(0, 0, 0, &mut ilands, &mut connect);
    if answer == i32::MAX {
        write!(bw, "-1").unwrap();
    } else {
        write!(bw, "{}", answer).unwrap();
    }
    bw.flush().unwrap();
}

fn min_connect(
    icnt: usize,
    jcnt: usize,
    answer: i32,
    ilands: &mut Vec<Iland>,
    connect: &mut Vec<Connect>,
) -> i32 {
    let mut is_connect = true;
    for c in connect.iter_mut().skip(1) {
        c.visited = false;
    }
    connect[0].visited = true;
    let mut queue = VecDeque::new();
    queue.push_back(0_usize);
    while !queue.is_empty() {
        let c = queue.pop_front().unwrap();

        let set = connect[c].connect.clone();
        for s in set {
            if !connect[s].visited {
                connect[s].visited = true;
                queue.push_back(s);
            }
        }
    }
    for c in connect.iter() {
        if !c.visited {
            is_connect = false;
            break;
        }
    }
    if is_connect {
        return answer;
    }
    if icnt == ilands.len() {
        return i32::MAX;
    }
    let mut ret = i32::MAX;
    let iland = &ilands[icnt];
    if let Some(Britges::VecMode(vec)) = &iland.britges.clone() {
        if jcnt == vec.len() {
            ret = i32::min(ret, min_connect(icnt + 1, 0, answer, ilands, connect));
        } else {
            let (bindex, value) = vec[jcnt];
            if !connect[icnt].connect.contains(&bindex) {
                connect[icnt].connect.insert(bindex);
                connect[bindex].connect.insert(icnt);
                ret = i32::min(
                    ret,
                    min_connect(icnt, jcnt + 1, answer + value, ilands, connect),
                );
                connect[icnt].connect.remove(&bindex);
                connect[bindex].connect.remove(&icnt);
                ret = i32::min(ret, min_connect(icnt, jcnt + 1, answer, ilands, connect));
            }
        }
    }

    ret
}

#[derive(Debug, Clone)]
struct Connect {
    connect: HashSet<usize>,
    visited: bool,
}

#[derive(Clone)]
enum Britges {
    HashMode(HashMap<usize, i32>),
    VecMode(Vec<(usize, i32)>),
}
struct Iland {
    can_britge: Vec<Bpoint>,
    britges: Option<Britges>,
}

#[derive(Debug)]
struct Bpoint {
    r: usize,
    c: usize,
    d: (i32, i32),
}
