import {L, log, range, curry, go, take, pipe} from '../fxjs/libs.js'

// L.map으로 map만들기
L.map = curry(function* (f, iter){
    iter = iter[Symbol.iterator]();

    let cur;
    while(!(cur = iter.next()).done){
        const a = cur.value;
        yield f(a);
    }
})

const takeAll = take(Infinity)

const map = curry(pipe(
    L.map, takeAll
))


log(map(a => a + 10, range(4)));

//L.filter로 filter만들기
L.filter = curry(function*(f, iter){
    for(const a of iter){
        if(f(a)) yield a;
    }
});

const filter = curry(pipe(
    L.filter, takeAll
));

log(filter(a => a % 2 === 0, range(4)))