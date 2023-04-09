import {go, filter, take, log, pipe, L, curry, map} from '../fxjs/libs.js'
// find - take를 이용해서 결론을 만듬(join, reduce처럼 지연된 평가된 이터레이터의 결과를 이용하는, 소비하는 함수)
const users = [
    {age: 32},
    {age: 31},
    {age: 37},
    {age: 28},
    {age: 25},
    {age: 32},
    {age: 31},
    {age: 37},
    {age: 28},
    {age: 25},
];

const find = curry((f, iter) => go(
    iter,
    L.filter(f),
    take(1),
    ([a]) => a
))

log(find(user => user.age < 30)(users));

go(
    users,
    L.map(u => u.age),
    find(n => n < 30),
    log
)