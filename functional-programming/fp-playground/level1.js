const log = console.log;
const add = (a, b) => a + b;

const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);


const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }
    return res;
});

const filter = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        if(f(a)){
            res.push(a);
        }
    }
    return res;
})

const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
        if(!acc){
            acc = 0;
        }
    }

    for(const a of iter){
        acc = f(acc, a);
    }

    return acc;
});

const products = [
    {name: '반팔티', price: 15000},
    {name: '긴팔티', price: 20000},
    {name: '핸드폰케이스', price: 15000, owner: '염재선'},
    {name: '후드티', price: 30000, owner: '염민선'},
    {name: '바지', price: 25000},
]


// 함수(코드)를 값으로 다루어 표현력 높이기
const go = (...args) => reduce((a, f) => f(a), args);

go(
    0,
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
);

go(
    0,
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
);

const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const f = pipe(
    (a, b) => a + b,
    a => a + 10,
    a => a + 100
)

log(f(3, 5));

log(
    reduce((a, b) => a + b,
        map(product => product.price, 
                filter(product => product.owner !== undefined, products)
            )
    )
);

go(
    products,
    products => filter(product => product.owner !== undefined, products),
    products => map(product => product.price, products),
    prices => reduce((a, b) => a + b, prices),
    log
)
console.log('go')

go(
    products,
    products => filter(product => product.owner !== undefined)(products),
    products => map(product => product.price)(products),
    prices => reduce((a, b) => a + b)(prices),
    log
)

go(
    products,
    filter(product => product.owner !== undefined),
    map(product => product.price),
    reduce((a, b) => a + b),
    log
)


log('function combination');

// 함수 조합
const total_price = pipe(
    map(p => p.price),
    reduce(add)
)

const base_total_price = predi => pipe(
    filter(predi),
    total_price
)

go(
    products,
    base_total_price(p => p.price >= 20000),
    log
)

go(
    products,
    base_total_price(p => p.price < 20000),
    log
)