import {log} from '../fxjs/libs.js'

// Composition 함수 합성
// f . g
// f(g(x))

const g = a => a + 1;
const f = a => a * a;

log(f(g(1)));
log(f(g())); // 함수 합성 결과 예상치 못한 결과 응답 -> 안전하게 함수 합성하지 못했다.

// monad: 안전하게 함수합성할수있는 방법
// -> 박스(컨테이너)를 가지고 있음. 박스안에 연산에 필요한 값들이 존재함

Array.of(1).map(g).map(f).forEach(r => log(r));
[].map(g).map(f).forEach(r => log(r));

// Promise는 비동기상황(대기가 이뤄진 상황)에서의 안전한 합성을 도와주는 '도구' (array와 같음)
// 즉, Promise는 얼마만큼의 대기가 필요한 상태에서도 안전하게 합성하기 위한도구 (대기가 끝나야 함수합성하도록.)
// 안전하다는 것은? 함수 합성도중 예상치 못한상황이 일어나지 않도록 하는 것이다.

Promise.resolve(3).then(g).then(f).then(r => log(r))
Promise.resolve().then(g).then(f).then(r => log(r))
new Promise(
    resolve => setTimeout(() => resolve(2), 100))
    .then(f).then(f).then(r => log(r));