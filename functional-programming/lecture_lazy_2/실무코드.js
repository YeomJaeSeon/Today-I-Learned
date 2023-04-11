import {reduce, map, go, flatten, log, L, flatMap, filter, take, pipe, curry} from '../fxjs/libs.js'

const users = [
    {
        name: 'a', age: 21, family: [
            {name: 'a1', age: 53}, {name: 'a2', age: 47},
            {name: 'a3', age: 16}, {name: 'a4', age: 15}
        ]
    },
    {
        name: 'b', age: 24, family: [
            {name: 'b1', age: 58}, {name: 'b2', age: 51},
            {name: 'b3', age: 19}, {name: 'b4', age: 22}
        ]
    },
    {
        name: 'c', age: 31, family: [
            {name: 'c1', age: 64}, {name: 'c2', age: 62}
        ]
    },
    {
        name: 'd', age: 20, family: [
            {name: 'd1', age: 42}, {name: 'd2', age: 42},
            {name: 'd3', age: 11}, {name: 'd4', age: 7}
        ]
    }
];

L.flatMap = curry(pipe(
    L.map,
    L.flatten
))

// 함수의 조합으로.. 데이터 완성 -> 함수형 프로그래밍
go(
    users,
    L.flatMap(user => user.family),
    L.filter(user => user.age > 20),
    L.map(user => user.age),
    take(3),
    reduce((a, b) => a + b),
    log
)
