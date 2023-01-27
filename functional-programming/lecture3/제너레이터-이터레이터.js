/**
 * 제너레이터: 이터레이터이자 이터러블을 생성(반환)하는 함수
 */

// 제너레이터는 일반함수에 *을 붙인다.
// js의 제너레이터는 어떠한 값도 이터러블을 생성할수있고, 즉, 어떠한 값도 순회할수 있는 형태로 조작 가능.
function *gen(){
    yield 1;
    if(false) yield 2;
    yield 3;

    return 100; // done: true일 경우 value값
}

const iter = gen()
console.log(iter[Symbol.iterator]() === iter)
console.log(iter.next())
console.log(iter.next())
console.log(iter.next())
console.log(iter.next())
console.log(iter.next())


for(const a of gen()){
    console.log(a)
}

