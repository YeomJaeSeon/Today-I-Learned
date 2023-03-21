export const curry = (f) => (a, ...res) => {
   return res.length ? f(a, ...res) : (...res) => f(a, ...res)
}

export const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }

    return res;
})

export const filter = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        if(f(a)){
            res.push(a)
        }
    }
    return res;
})

export const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const a of iter){
        acc = f(acc, a);
    }

    return acc;
})

export const go = (...args) => reduce((a, f) => f(a), args);

export const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const products = [
    {name: '반팔티', price: 15000, quantity: 1},
    {name: '긴팔티', price: 20000, quantity: 2},
    {name: '핸드폰케이스', price: 15000, quantity: 3},
    {name: '후드티', price: 30000, quantity: 4},
    {name: '바지', price: 25000, quantity: 5}
];