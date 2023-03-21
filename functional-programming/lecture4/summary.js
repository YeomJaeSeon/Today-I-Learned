const log = console.log;
const nl = () => console.log("===================")

const map = (f, iter) => {
    const res = [];
    for(const n of iter){
        res.push(f(n));
    }

    return res;
}

const filter = (f, iter) => {
    const res = [];
    for(const n of iter){
        if(f(n)){
            res.push(n);
        }
    }

    return res;
}

const reduce = (f, acc, iter) => {
    if(iter === undefined){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const n of iter){
        acc = f(acc, n);
    }

    return acc;
}

const products = [
    {name: '반팔티', price: 15000},
    {name: '긴팔티', price: 20000},
    {name: '핸드폰케이스', price: 15000},
    {name: '후드티', price: 30000},
    {name: '바지', price: 25000},
]

const add = (a, b) => a + b;

log(
    reduce(
        add, 
        map(p => p.price, filter(p => p.price < 20000, products))));

log(
    reduce(
        add, 
        filter(n => n >= 20000, map(p => p.price, products))));
