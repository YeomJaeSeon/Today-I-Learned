// 함수(코드)를 값으로 다루기 때문에, 함수(코드)를 인자로 받고, 원하는 인자가 오기 전까지 받은 함수(코드)의 평가를 지연하는 함수
// 원하는 인자가 와야 값으로 다루는 함수(코드)를 평가하겠다.!!!

const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

const mult = curry((a, b) => a * b);
const mult3 = mult(3);

console.log(mult3(2))
console.log(mult3(3))
console.log(mult3(4))
