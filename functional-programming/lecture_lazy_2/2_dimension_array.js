import {map, go, flatten, log, L, flatMap, filter, take} from '../fxjs/libs.js'

const arr = [
    [1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [9, 10]
]

// 즉시평가
go(
    arr,
    flatten,
    filter(a => a % 2 === 1),
    map(a => a * a),
    log
)

// 지연평가
go(
    arr,
    L.flatten,
    L.filter(a => a % 2 === 1),
    L.map(a => a * a),
    take(3), // 이터레이터 순회하는 (소비하는) 함수 - next()호출함.
    log
)

// const it = L.flatMap(map(a => a * a), arr);
// log([...it])