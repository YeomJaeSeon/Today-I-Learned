const nl = () => console.log('==============')
// 사용자 정의 이터러블 만들어보기

const iterable = {
    [Symbol.iterator](){
        let i = 3;
        return {
            next(){
                return i === 0 ? {done: true, value: undefined} : {value: i--, done: false}
            },
            [Symbol.iterator]() { return this; } // well formed iterator가 되기 위해선, 이터레이터도 이터러블이어야한다.
        }
    }
}

const iterator = iterable[Symbol.iterator]();
// console.log(iterator.next());
// console.log(iterator.next());
console.log(iterator.next());
console.log(iterator.next());
for(const a of iterable){
    console.log(a)
}

nl()

const arr = [1, 2, 3];
// arr[Symbol.iterator] = null

// spread 연산자도 이터러블이어야 가능 (for...of와 동일하다)
console.log(...arr, ...[3, 4]);
const set = new Set([1, 2, 3, 3]);
const map = new Map([['a', 1], ['b', 2], ['b', 3]]);
const newArr = [...[1, 2, 3], ...set, ...map, ...map.keys()];
console.log(newArr)