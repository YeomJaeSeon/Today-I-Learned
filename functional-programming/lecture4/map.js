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

// 함수형 프로그래밍은 param으로 받아 return하는 것이 정석
// input -> output만 신경쓰면 외부 요인을 직접적으로 변경해 발생하는 side effect를 막을 수 있다.
const map = (f, iter) => {
    const res = [];
    for(const v of iter){
        res.push(f(v));
    }
    return res;
}

const names = map(f => f.name, products);
console.log(names)

// array의 map함수가 아닌 이터러블/이터레이터 프로토콜을 따르는 map은 array의 map보다 활용도가 높다.
const m = new Map();
m.set('a', 1);
m.set('b', 2);

const iter = m[Symbol.iterator]();

// console.log(m.map(([k, v]) => [k, v * 2])); -> m은 array가 아니라 map을 사용할수없다. 그러나 우리가 만든 이터러블/이터레이터 프로토콜을 따르는 map을 이용할수있다.
// m은 이터러블이닌까.

console.log(map(([k, v]) => [k, v * 2], m))
const m2 = new Map(map(([k, v]) => [k, v * 2], m));
console.log(m2)

console.log(map(k => k + 100, function *(){
    yield 1;
    if(false) yield 2;
    yield 3;
}()))
