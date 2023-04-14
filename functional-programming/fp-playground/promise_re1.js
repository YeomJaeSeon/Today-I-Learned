import {log} from '../fxjs/libs.js'

// 비동기 상황을 값으로써 다룰수 있게 해주는 Promise

const delayCallback1000 = (a, callback) => {
    setTimeout(() => callback(a + 10), 1000)
}
const delay1000 = (a) => new Promise(res => setTimeout(() => res(a + 10), 1000));

// 비동기 상황을 콜백은 값으로써 다룰 수없다.
// delayCallback1000(10, function (res){
//     console.log(res)
// })

// Promise는 비동기 상황을 값으로써 다룰 수있음
const r = delay1000(10);
// log(r)

const go1 = (a, f) => a instanceof Promise ? a.then(f) : f(a);

const add5 = (a) => a + 5;

const n1 = 7;
go1(go1(n1, add5), log);
// const r1 = go1(7, add5)
// log(r1)

const delay100 = (a) => new Promise(res => setTimeout(() => res(a), 100));

const n2 = delay100(7);
go1(go1(n2, add5), log);
// const r2 = go1(delay100(7), add5);
// r2.then(log)