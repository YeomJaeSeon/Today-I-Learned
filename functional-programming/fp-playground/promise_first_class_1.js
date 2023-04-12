import {log} from '../fxjs/libs.js'

// Promise를 이용해 비동기상황을 값으로 다루어 표현력 높이기

const delay100 = (a) => new Promise(res =>
    setTimeout(() => res(a), 100));

const go1 = (a, f) => a instanceof Promise ? a.then(f) : f(a);
const add5 = a => a + 5;

const n1 = 5;
go1(go1(n1, add5), log);

const n2 = delay100(5)
go1(go1(n2, add5), log)

