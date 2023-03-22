const log = console.log

// 일급함수: 함수가 값으로 다루어 질수있다는 개념
// 고차함수: 함수를 값으로 다루어 사용하는 함수

// 1. 함수를 인자로
const doSomethingWithOne = (f) => f(1);
log(doSomethingWithOne((arg) => ++arg));

// 2. 함수를 리턴
const addMaker = (a) => (b) => a + b;
const add2 = addMaker(2);
log(add2(5))
log(add2(10))


const products = [
    {name: '반팔티', price: 15000, quantity: 1},
    {name: '긴팔티', price: 20000, quantity: 2},
    {name: '핸드폰케이스', price: 15000, quantity: 3},
    {name: '후드티', price: 30000, quantity: 4},
    {name: '바지', price: 25000, quantity: 5}
];


const curry = (f) => (arg, ..._) => 
    _.length ? f(arg, ..._) : (..._) => f(arg, ..._);

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

const go = (...args) => reduce((a, f) => f(a), args);
const pipe = (f, ...fs) => (...args) => go(f(...args), ...fs);
const add = (a, b) => a + b;

go(
    add(0, 1),
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
)

const f = pipe(
    (...args) => reduce(add, args),
    a => a + 1,
    a => a + 10,
    a => a + 100
);

log(f(1, 2, 3, 4, 5))

log(
    reduce(add, 
            map(product => product.price, 
                    filter(product => product.quantity > 2, products)
                )
        )
)

go(
    products,
    products => filter(product => product.quantity > 2, products),
    products => map(product => product.price, products),
    products => reduce(add, products),
    log
)

go(
    products,
    products => filter(product => product.quantity > 2)(products),
    products => map(product => product.price)(products),
    products => reduce(add)(products),
    log
)

const calculate_price = (condition) => pipe(
    filter(condition),
    base_total
)

const base_total = pipe(
    map(product => product.price),
    reduce(add)
);

go(
    products,
    calculate_price(product => product.quantity > 2),
    log
)

go(
    products,
    calculate_price(product => product.quantity <= 2),
    log
)


// 1. products에만 한정
// 2. map조건이 한정
// -> 추상화

// 1. 공통의 부분만
// 2. 세부적인 내용 제거

const sum = (f, iter) => go(
    iter,
    map(f),
    reduce(add)
)

// 총 가격
const total_price = sum(product => product.price, products);
go(
    total_price,
    log
)

// 총 수량
const total_quantity = sum(product => product.quantity, products);

go(
    total_quantity,
    log
)

const users = [
    {name: '염재선', age: 20},
    {name: '염재순', age: 30},
    {name: '염재샨', age: 10},
    {name: '염재상', age: 25},
    {name: '염재솔', age: 35},
]
const total_age = sum(user => user.age, users);

log(total_age);
