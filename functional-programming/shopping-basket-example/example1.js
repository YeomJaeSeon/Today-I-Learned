// 총 수량 구하기

const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);
const add = (a, b) => a + b;
const log = console.log

const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }

    return res;
})

// const filter = (f, iter)

const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const a of iter){
        acc = f(acc, a);
    }

    return acc;
});

const go = (...args) => reduce((a, f) => f(a), args);
const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const products = [
    {name: '반팔티', price: 15000, quantity: 1},
    {name: '긴팔티', price: 20000, quantity: 2},
    {name: '핸드폰케이스', price: 15000, quantity: 3},
    {name: '후드티', price: 30000, quantity: 4},
    {name: '바지', price: 25000, quantity: 5}
]

const sum = curry((f, iter) => go(
    iter,
    map(f),
    reduce(add)
))

const total_quantity = sum(p => p.quantity);


log(total_quantity(products))

const total_price = sum(p => p.quantity * p.price);

log(total_price(products))

log(
sum(u => u.age, [
    { age: 30},
    { age: 20},
    { age: 15},
])
)