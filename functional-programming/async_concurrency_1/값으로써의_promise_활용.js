import {log} from '../fxjs/libs.js'

// Promise는 비동기상황을 값으로(일급으로) 다룰수 있으므로 다양한걸 할수있다. (함수에게 전달, promise인지 아닌지등 확인가능..)

// 일급활용
const delay100 = a => new Promise(resolve =>
    setTimeout(() => resolve(a), 100));

// Promise를 이용해 비동기상황을 값으로 다루어, 처리할수있다.
const go1 = (a, f) => a instanceof Promise ? a.then(f) : f(a);
const add5 = a => a + 5;


const n1 = 10;
go1(go1(n1, add5), log)
// let r = go1(10, add5);
// log(r)

const n2 = delay100(10)
go1(go1(n2, add5), log);
// let r2 = go1(delay100(10), add5);
// r2.then(log)