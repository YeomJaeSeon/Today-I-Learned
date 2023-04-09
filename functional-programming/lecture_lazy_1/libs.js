import {go, log} from '../fxjs/libs.js'

// range, map, filter, take, reduce
const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

export const range = l => {
    let i = 0;
    const res = [];
    while(i < l){
        res.push(i);
        i++;
    }

    return res;
}

export const map = curry((f, iter) => {
    const res = [];
    iter = iter[Symbol.iterator]();
    let cur;
    while (!(cur = iter.next()).done){
        const a = cur.value;
        res.push(f(a));
    }
    //
    // for(const a of iter){
    //     res.push(f(a));
    // }

    return res;
});

export const filter = curry((f, iter) => {
    const res = [];

    iter = iter[Symbol.iterator]();
    let cur;
    while (!(cur = iter.next()).done){
        const a = cur.value;
        if(f(a)) res.push(a)
    }

    // for(const a of iter){
    //     if(f(a)) res.push(a);
    // }

    return res;
});

export const take = curry((limit, iter) => {
    const res = [];


    iter = iter[Symbol.iterator]();
    let cur;
    while (!(cur = iter.next()).done){
        const a = cur.value;

        res.push(a);
        if(res.length === limit) return res;
    }

    // for(const a of iter){
    //     res.push(a);
    //     if(res.length === limit) return res;
    // }

    return res;
});

export const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const a of iter){
        acc = f(acc, a);
    }

    return acc;
});

// 즉시 평가 (엄격한 계산) - 가로로 평가됨
// console.time('immediate')
// go(
//     range(5),
//     map(n => n + 10),
//     filter(n => n % 2),
//     take(2),
//     log
// );
// console.timeEnd('immediate')

// L.range, L.map, L.filter
export const L = {
    range: function* (l){
        let i = 0;
        while(i < l){
            yield i;
            i++;
        }
    },
    map: curry(function*(f, iter){
        iter = iter[Symbol.iterator]();

        let cur;
        while(!(cur = iter.next()).done){
            const a = cur.value;
            yield f(a);
        }

        // for(const a of iter){
        //     yield f(a);
        // }
    }),
    filter: curry(function*(f, iter){
        iter = iter[Symbol.iterator]();

        let cur;
        while(!(cur = iter.next()).done){
            const a = cur.value;
            if(f(a))
                yield a;
        }
    })
}

// 느긋한 계산 - 세로로 평가됨
console.time('lazy')
go(
    L.range(5),
    L.map(n => n + 10),
    L.filter(n => n % 2 === 0),
    take(2),
    log
);
console.timeEnd('lazy')

/**
 * 엄격한 평가는 가로로 평가됨.
 * 즉시 평가하기에, 모두다 평가한다.
 *
 * 느긋한 평가는 세로로 평가됨.
 * next 함수를 호출할때, 실제로 평가가 되고, 평가를 지연하기에 모두다 평가하지 않는다.
 *
 * 효율적인측면에서도 느긋한 평가가 더 효율적이다.
 */