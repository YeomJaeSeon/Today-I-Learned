import {map, go, reduce, pipe, curry} from './fxjs/libs.js'

const log = console.log;

log(map(a => a + 1, [1, 2, 3]));
log(map(a => a + 1)([1, 2, 3]));

const products = [
    {name: '반팔티', price: 15000, quantity: 1},
    {name: '긴팔티', price: 20000, quantity: 2},
    {name: '핸드폰케이스', price: 15000, quantity: 3},
    {name: '후드티', price: 30000, quantity: 4},
    {name: '바지', price: 25000, quantity: 5}
]

const add = (a, b) => a + b;

const sum = (f, iter) => go(
    iter,
    map(f),
    reduce(add)
)

// 총 수량
const total_quantity = (products) => sum(p => p.quantity ,products);

log(total_quantity(products))

// 총 가격
const total_price = products => sum(p => p.quantity * p.price, products)

log(total_price(products))