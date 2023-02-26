const log = console.log;
const log2 = (a) => console.log(a)
//same..
log(3);
log2(3);

const curry = (f) => (a, ...res) => res.length ? f(a, ...res) : (...res) => f(a, ...res);
// f함수의 인자에 두개이상의 인자가 오면 바로 실행, 그렇지 않으면 평가를 지연했다가 인자가 추가로 오면 f평가
// 클로져덕에 가능

const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }

    return res;
})

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
    }

    for(const a of iter){
        acc = f(acc, a);
    }
    return acc;
})

const products = [
    {name: '반팔티', price: 15000},
    {name: '긴팔티', price: 20000},
    {name: '핸드폰케이스', price: 15000, owner: '염재선'},
    {name: '후드티', price: 30000, owner: '염민선'},
    {name: '바지', price: 25000},
]

const mult = (a, b) => a * b;

log(
    reduce(mult, 
        map(product => product.price, 
                filter(product => !product.owner, products)
            )
    )
);

// go로 표현력 높이기
const go = (...args) => reduce((a, f) => f(a), args);

go(
    0,
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
)

// 함수를 리턴하는 pipe -> 원하는 시점에 인자로 받은 함수(코드)를 평가
const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const f = pipe(
    (...as) => reduce((a, b) => a + b, as),
    a => a + 10,
    a => a + 100,
)

log(f(1, 2, 3, 4));

// 위에서 아래로 함수가 실행되는 go를 통해 표현력 높이기(가독성 향상)
go(
    products,
    products => filter(product => !product.owner, products),
    products => map(product => product.price, products),
    prices => reduce(mult, prices),
    log
);

go(
    products,
    products => filter(product => !product.owner)(products),
    products => map(product => product.price)(products),
    prices => reduce(mult)(prices),
    log
);
// go -curry 가독성 더 향상 f === a => f(a)
go(
    products,
    filter(product => !product.owner),
    map(product => product.price),
    reduce(mult),
    log
);

const total_price = pipe(
    map(p => p.price),
    reduce((a, b) => a + b)
)

const base_total_price = (predi) => pipe(
    filter(predi),
    total_price
)

go(
    products,
    base_total_price(product => !product.owner),
    log
);

go(
    products,
    base_total_price(product => product.owner),
    log
);
