import {log, curry, reduce, go} from '../fxjs/libs.js'

const range = (l) => {
    let i = 0;
    const res = [];

    while(i < l){
        res.push(i);
        i++;
    }

    return res;
}

const L = {};
L.range = function* (l){
    let i = 0;
    while(i < l){
        yield i;
        i++
    }
}

const take = curry((limit, iter) => {
    const res = [];
    for(const a of iter){
        res.push(a);
        if(res.length === limit) return res;
    }

    return res;
});


// 이터러블 배열 리턴하는 range함수
console.time('range')
const listOfRange = range(500000);
go(
    listOfRange,
    take(6),
    reduce((a, b) => a + b),
    log
)
console.timeEnd('range')

// 이터레이터인 L.range함수 - 느긋함. 평가를 지연함.
console.time('L.range')
const listOfLRange = L.range(500000);
go(
    listOfLRange,
    take(6),
    reduce((a, b) => a + b),
    log
)
console.timeEnd('L.range')

go(
    L.range(Infinity),
    take(10),
    log
)

// go(
//     range(Infinity),
//     take(10),
//     log
// )
/**
 * 실제로 순회할때만 평가가진행되기에 L.ragne의 성능이 좋음.
 * 비즈니스로직에 실제로 사용되기전에는 평가가 지연되어서 그렇다.
 */