const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }
    return res;
})

const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]()
        acc = iter.next().value;
    }
    for(const a of iter){
        acc = f(acc, a);
    }

    return acc;
});

const L = {
    map: curry(function *(f, iter){
        for(const a of iter){
            yield f(a);
        }
    }),
    entries: function* (obj){
        for(const k in obj){
            yield [k, obj[k]];
        }
    }
}

const go = (...args) => reduce((a, f) => f(a), args);

const pipe = (f, ...fs) => (...a) => go(f(...a), ...fs);

const join = curry((spec, iter) => reduce((a, b) => `${a}${spec}${b}` ,iter));

const queryStr = pipe(
    L.entries,
    L.map(([k, v]) => `${k}=${v}`),
    join('&'),
)


console.log(queryStr({page: 1, pageSize: 10, type: 'content'}));

function* a(){
    yield 10;
    yield 11;
    yield 12;
    yield 13;
}
console.log(join('&', a()))