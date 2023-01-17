// es6 순회
const nl = () => console.log('================')

// 1. 리스트
const arr = [1, 2, 3];
for(const a of arr){
    console.log(a);
}

console.log(arr[Symbol.iterator]);
console.log(arr[Symbol.iterator]());
const arrIterator = arr[Symbol.iterator]();
console.log(arrIterator.next()); // 이터레이터의 next함수는 key, done의 키를 가지고있는 오브젝트를 리턴한다.

for(const a of arrIterator){
    console.log(a)
}
nl()

// 2. set
const set = new Set([1, 2, 3]);
for(const a of set){
    console.log(a);
}

console.log(set[Symbol.iterator])
console.log(set[Symbol.iterator]())
const setIterator = set[Symbol.iterator]()
console.log(setIterator.next())
for(const a of setIterator){
    console.log(a)
}

nl()

// 3. map
const map = new Map([['a', 1], ['b', 2], ['c', 3]]);
for(const a of map){
    console.log(a)
}
console.log(map[Symbol.iterator])
console.log(map[Symbol.iterator]())
const mapIterator = map[Symbol.iterator]();
console.log(mapIterator.next())
for(const a of mapIterator){
    console.log(a)
}
nl()

/**
 * ES6의 for..of 는 이터러블/이터레이터 프로토콜을 따르기에 가능하다.
 * 
 * arr, set, map 객체는 모두 이터러블이다.
 * 
 * - 이터러블: 이터레이터를 리턴하는 [Symbol.iterator]() 를 가진 값.
 * - 이터레이터: {value, done} 객체를 리턴하는 next()를 가진 값
 * - 이터러블/이터레이터 프로토콜: 이터러블을 for...of, spread operator 등과 함께 동작하도록 규약
 */