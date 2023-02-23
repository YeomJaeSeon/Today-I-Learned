// 함수를 인자로 받고 원하는 개수의 인자가 들어오기 전까지 평가를 지연하는 함수
const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

const mult = curry((a, b) => a * b);
const mult3 = mult(3);

console.log(mult3(2))
console.log(mult3(3))
console.log(mult3(4))
