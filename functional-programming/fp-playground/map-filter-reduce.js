const log = console.log
const nl = () => log('======================');

const products = [
    {name: '반팔티', price: 15000},
    {name: '긴팔티', price: 20000},
    {name: '핸드폰케이스', price: 15000, owner: '염재선'},
    {name: '후드티', price: 30000, owner: '염민선'},
    {name: '바지', price: 25000},
]

// fp는 파라미터로받아 return, 함수내에서 외부 변수와 직접적인 관계를 갖지않아 side effect를 줄인다.
const map = (f, iter) => {
    const res = [];
    for(const each of iter){
        res.push(f(each));
    }

    return res;
}

log(map((value) => value.owner, products));

const filter = (f, iter) => {
    const res = [];
    for(const each of iter){
        if(f(each)){
            res.push(each)
        }
    }

    return res;
}

log(filter((value) => value.owner !== undefined, products));

const reduce = (f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const each of iter){
        acc = f(acc, each);
    }

    return acc;
}

log(reduce((a, b) => a + b, 0, [1, 2, 3, 4, 5]));
log(reduce((a, b) => a + b, [1, 2, 3, 4, 5]));

nl();


const pricesOfPublic = map((value) => value.price ,filter((value) => value.owner === undefined, products));
const pricesOfPrivate = map((value) => value.price ,filter((value) => value.owner !== undefined, products));
console.log(pricesOfPrivate)
console.log(pricesOfPublic)

nl();

const privateProducts = filter((value) => value.owner !== undefined, products);
const publicProducts = filter((value) => value.owner === undefined, products);

log('privateProducts', privateProducts);
log('publicProducts', publicProducts);

const namesOfPrivateProducts = map((value) => value.name, privateProducts);
const namesOfPublicProducts = map((value) => value.name, publicProducts);
log('namesOfPrivateProducts', namesOfPrivateProducts);
log('namesOfPublicProducts', namesOfPublicProducts);

nl();

// private한 price 모두 더한 값
log(reduce((a, b) => a + b, 0, map((value) => value.price, filter(value => value.owner !== undefined, products))));

// public한 price 모두 더한 값
log(reduce((a, b) => a + b, 0, map(value => value.price, filter(value => value.owner === undefined, products))));

// 모든 price 모두 더한 값
log(reduce((a, b) => a + b, map(value => value.price, products)));
