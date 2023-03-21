const curry = (f) => 
(a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }

    return res;
});
const plus1 = map(a => a + 1);
console.log(plus1([1, 2, 3]))


const filter = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        if(f(a)){
            res.push(a);
        }
    }
    return res;
});

const isOdd = filter(a => a % 2 === 1);
console.log(isOdd([1, 2, 3]))
const isEven = filter(a => a % 2 === 0);
console.log(isEven([1, 2, 3, 4]))

const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }
    for(const a of iter){
        acc = f(a, acc);
    }

    return acc;
});

const log = console.log;
const add = (a, b) => a + b;
log(reduce(add, 0, [1, 2, 3]))

const products = [
    {name: '반팔티', price: 15000},
    {name: '긴팔티', price: 20000},
    {name: '핸드폰케이스', price: 15000, owner: '염재선'},
    {name: '후드티', price: 30000, owner: '염민선'},
    {name: '바지', price: 25000},
]

// 

// go
const go = (...args) => reduce((f, a) => f(a), args)
go(
    0,
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
)


log(
    reduce(
        add, 
        map(a => a.price, 
                filter(a => a.owner, products)
            )
    )
)

go(
    products,
    products => filter(a => a.owner, products),
    products => map(a => a.price, products),
    prices => reduce(add, prices),
    log
)

go(
    products,
    products => filter(a => a.owner)(products),
    products => map(a => a.price)(products),
    prices => reduce(add)(prices),
    log
)

go(
    products,
    filter(a => a.owner),
    map(a => a.price),
    reduce(add),
    log
)
