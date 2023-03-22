const log = console.log;
const nl = () => console.log("===============")

const products = [
    {name: '반팔티', price: 15000, quantity: 1},
    {name: '긴팔티', price: 20000, quantity: 2},
    {name: '핸드폰케이스', price: 15000, quantity: 3},
    {name: '후드티', price: 30000, quantity: 4},
    {name: '바지', price: 25000, quantity: 5}
];

const func = (f) => f(1);
log(func((a) => a + 100));

const addMaker = (a) => (b) => a + b;
const add10 = addMaker(10);

log(add10(20));
log(add10(30))

nl()

const curry = (f) => (arg, ..._) => _.length ? f(arg, ..._) : (..._) => f(arg, ..._);


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
            res.push(a)
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

log(map(product => product.price, products))
log(filter(product => product.quantity > 2, products))
log(reduce((a, b) => a + b, 0, [1, 2, 3, 4, 5]))
log(reduce((a, b) => a + b, [1, 2, 3, 4, 5]))

nl()
log(
    reduce((a, b) => a + b, 
        map(product => product.price, 
                filter(product => product.quantity > 2, products)
            )
    )
)

const go = (...args) => reduce((a, f) => f(a), args);

go(
    0,
    a => a + 1,
    a => a + 10,
    a => a + 100,
    log
);

const pipe = (f, ...fs) => (...args) => go(f(...args), ...fs);

const f = pipe(
    (...args) => reduce((a, b) => a + b, args),
    a => a + 1,
    a => a + 10,
    a => a + 100
)

log(f(0, 1, 2, 3));

nl()
log(
    reduce((a, b) => a + b, 
        map(product => product.price, 
                filter(product => product.quantity > 2, products)
            )
    )
)

go(
    products,
    products => filter(product => product.quantity > 2, products),
    products => map(product => product.price, products),
    products => reduce((a, b) => a + b, products),
    log
)

go(
    products,
    products => filter(product => product.quantity > 2)(products),
    products => map(product => product.price)(products),
    products => reduce((a, b) => a + b)(products),
    log
)

go(
    products,
    filter(product => product.quantity > 2),
    map(product => product.price),
    reduce((a, b) => a + b),
    log
)

nl();

const base_total = pipe(
    map(product => product.price),
    reduce((a, b) => a + b)
)

const calculate_price = (condition) => pipe(
    filter(condition),
)

go(
    products,
    calculate_price(product => product.quantity > 2),
    base_total,
    log
)

go(
    products,
    calculate_price(product => product.quantity <= 2),
    base_total,
    log
)

const sum = curry((condition, iter) => go(
    iter,
    map(condition),
    reduce((a, b) => a + b)
));

// 총 수량
go(
    products,
    map(product => product.quantity),
    reduce((a, b) => a + b),
    log
)

// to-be
const totalQuantityOfProducts = sum(product => product.quantity);
log(totalQuantityOfProducts(products));

// 총 가격
go(
    products,
    map(product => product.price),
    reduce((a, b) => a + b),
    log
)

const totalPriceOfProducts = sum(product=>product.price);
log(totalPriceOfProducts(products))

const users = [
    {name: 'kim', age:20},
    {name: 'yeom', age: 30},
    {name: 'park', age: 15}
]

const totalAgeOfUsers = sum(user => user.age);
log(
    totalAgeOfUsers(users)
)