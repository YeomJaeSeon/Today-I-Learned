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
            res.push(a)
        }
    }
    return res;
});

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

const products = [
    {name: '반팔티', price: 15000},
    {name: '긴팔티', price: 20000},
    {name: '핸드폰케이스', price: 15000, owner: '염재선'},
    {name: '후드티', price: 30000, owner: '염민선'},
    {name: '바지', price: 25000},
]

const log = console.log;

log(
    reduce((a, b) => a + b, 
        map((a) => a.price, 
            filter(a => !a.owner, products)
        )
    )
)

// go로 위 코드 표현력 높이기
const go = (...args) => reduce((a, f) => f(a), args)

go(
    0,
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
)

const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const f = pipe(
    (...args) => reduce((a, b) => a + b, args),
    a => a + 1,
    a => a + 10,
    a => a + 100
)

log(f(0, 1, 2, 3));

log(
    reduce((a, b) => a + b, 
        map((a) => a.price, 
            filter(a => !a.owner, products)
        )
    )
)

// 코드를 값으로 사용하는 go를 통해서 표현력을 높임. (가독성이 좋아짐)
go(
    products,
    products => filter(a => !a.owner, products),
    products => map(a => a.price, products),
    prices => reduce((a, b) => a + b, prices),
    log
)

go(
    products,
    products => filter(a => !a.owner)(products),
    products => map(a => a.price)(products),
    prices => reduce((a, b) => a + b)(prices),
    log
)

go(
    products,
    filter(a => !a.owner),
    map(a => a.price),
    reduce((a, b) => a + b),
    log
)


