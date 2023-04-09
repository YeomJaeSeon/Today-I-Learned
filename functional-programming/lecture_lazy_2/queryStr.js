const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }
    for(const a of iter){
        acc = f(acc, a)
    }
    return acc;
});

const L = {
    map: curry(function*(f, iter){
        for(const a of iter){
            yield f(a);
        }
    }),
    entries: function *(obj) {
        for(const k in obj) yield [k, obj[k]];
    }
}

const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }
    return res;
})


const go = (...args) => reduce((a, f) => f(a), args);
const pipe = (f, ...fs) => (...a) => go(f(...a), ...fs);

// 이터러블 프로토콜 따르면 모두 사용가능 - array join함수보다 추상화되어있음
const join = curry((sep = ',', iter) => reduce((a, b) => `${a}${sep}${b}`, iter));

const queryStr = pipe(
    L.entries,
    L.map(([k, v]) => `${k}=${v}`),
    join('&')
)

console.log(queryStr({limit: 10, offset: 10, type: 'notice'}))