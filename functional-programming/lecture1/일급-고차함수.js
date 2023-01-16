// 일급함수 : "함수가 값으로 다루어질수있다." 라는 개념 (JS의 함수는 일급함수이다.)
// 고차함수: "함수를 값으로 다루는 함수"

const nl = () => console.log("---------------------");
/**
 * 고차함수 1: 함수를 인자로 받아서 사용하는 함수
 */
const apply1 = f => f(1);
const add2 = a => a + 2;
console.log(apply1(add2));

const multiply2 = a => a * 2;
console.log(apply1(multiply2))

console.log(apply1(a => a - 1))

const times = (f, n) => {
    let i = -1;
    while(++i < n){
        f(i);
    }
}

nl()
times(console.log, 10);

nl()
times(a => console.log(a +10), 3)

nl()
const apply2 = f => f(2);

console.log(apply2(a => a + 2))
nl()

/**
 * 고차함수 2: 함수를 만들어 리턴하는 함수 (클로져를 만들어 리턴하는 함수)
 */
const addMaker = a => b => a + b;
const add3 = addMaker(3);
console.log(add3.toString())
console.log(add3(2))