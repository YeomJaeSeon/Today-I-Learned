const log = console.log

// curry
const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

const mult3 = curry((a, b) => a * b)(3);
log(mult3(10))
log(mult3(20))
log(mult3(5))

// map filter reduce - iterator/iterable protocol 따르는
const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a))
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
    {name: '반팔티', price: 15000, quantity: 1},
    {name: '긴팔티', price: 20000, quantity: 2},
    {name: '핸드폰케이스', price: 15000, quantity: 3},
    {name: '후드티', price: 30000, quantity: 4},
    {name: '바지', price: 25000, quantity: 5}
]

const add = (a, b) => a + b;

log(
    reduce(add, 
            map(product => product.price, 
                    filter(product => product.price > 20000, products)
                )
        )
);

// go, pipe -> 함수(코드)를 값으로 표현력 높이기
const go = (...args) => reduce((a, f) => f(a), args);

go(
    add(1, 2),
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
)

const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const f = pipe(
    add,
    a => a + 1,
    a => a + 10,
    a => a + 100
);

log(f(0, 5));
const nl = () => console.log('==============');

nl()
// level 1
log(
    reduce(add, 
            map(product => product.price, 
                    filter(product => product.price > 20000, products)
                )
        )
);

// level 2 with go -> 코드(함수)를 값으로 사용하는 고차함수 go를 이용해 표현력 높이기
go(
    products,
    products => filter(product => product.price > 20000, products),
    products => map(product => product.price, products),
    products => reduce(add, products),
    log
)

// level3 curry(함수를 인자로 받고, 원하는 인자가 올 때까지 평가를 지연.)
go(
    products,
    filter(product => product.price > 20000),
    map(product => product.price),
    reduce(add),
    log
)

// level4 함수(코드)를 값으로 사용하니 함수조합으로 중복 줄일수 있다.
const calculate_price = (predi) => pipe(
    filter(predi),
    base_total,
)

const base_total = pipe(
    map(product => product.price),
    reduce(add),
)

go(
    products,
    calculate_price(product => product.price > 20000),
    log
)

go(
    products,
    calculate_price(product => product.price <= 20000),
    log
)

// 추상화 (products한정, 특정 조건에서 범용적으로 적용되게 수정)
const sum = (f, iter) => go(
    iter,
    map(f),
    reduce(add)
)

// 총 수량 
go(
    products,
    map(product => product.quantity),
    reduce(add),
    log
)

// with sum
const total_quantity = (products) => sum(product => product.quantity, products)
log(total_quantity(products))

// 총 가격
go(
    products,
    map(product => product.price),
    reduce(add),
    log
)

// with sum
const total_price = (products) => sum(product => product.price, products);
log(total_price(products))

const users = [{
        name: 'yeom',
        age: 20
    },
    {
        name: 'kim',
        age: 30
    },
    {
        name: 'park',
        age: 24
}];

const total_age = (users) => sum(user => user.age, users);
log(total_age(users))