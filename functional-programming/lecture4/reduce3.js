const log = console.log;
const nl = () => console.log('=================');

const products = [
    {name: '반팔티', price: 15000},
    {name: '긴팔티', price: 20000},
    {name: '핸드폰케이스', price: 15000},
    {name: '후드티', price: 30000},
    {name: '바지', price: 25000},
]

const reduce = (f, acc, iter) => {
    if(iter === undefined){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const num of iter){
        acc = f(acc, num);
    }

    return acc;
}

log(
    reduce((totalPrice, product) => totalPrice + product.price ,0 ,products)
)