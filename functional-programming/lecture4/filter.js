const products = [
    {
        name: '책상',
        price: 1000
    },
    {
        name: '의자',
        price: 2000
    },
    {
        name: '가방',
        price: 1500
    },
];

const customFilter1 = () => {
    const res = [];
    for(const a of products){
        if(a.price === 2000) continue;
        res.push(a.price)
    }

    return res;
}

const filter = (f ,iter) => {
    const res = [];
    for(const v of iter){
        if(f(v)){
            res.push(v);
        }
    }
    return res;
}

console.log(filter(f => f.price !== 1000, products));
console.log(filter(f => f.name !== '책상', products));
console.log(filter(f => f.price  > 1500, products));
console.log(filter(f => f % 2, function *(){
    yield 1;
    yield 2;
    yield 3;
    yield 4;
    yield 5;
}()));