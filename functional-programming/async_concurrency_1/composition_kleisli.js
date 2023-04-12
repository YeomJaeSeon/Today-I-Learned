import {log, find} from '../fxjs/libs.js'
// Kleisli Composition 관점에서의 함수합성

// 오류가 있을수있는 상황에서의 함수합성을 안전하게 하는 하나의 규칙

// f.g
// f(g(x)) = f(g(x))
// but 코드에서는, 외부요인과의 의존성이 있으면 위 동등이 아닐 수 있다.
// f(g(x)) = g(x) -> Kleisli Composition

const users = [
    {id: 1, name: 'aa'},
    {id: 2, name: 'bb'},
    {id: 3, name: 'cc'},
];

const getUserById = id => find(u => u.id === id, users) || Promise.reject('없어여!');

const f = ({name}) => name;
const g = getUserById;
// const fg = id => f(g(id));
const fg = id => Promise.resolve(id).then(g).then(f).catch(a => a)

const r1 = fg(2).then(log)
log(r1)

users.pop()
users.pop()

const r2 = fg(2).then(log)
log(r2)

// Promise를 이용해 Kleisli 관점에서의 함수합성
// -> 외부요인의 변경에 따라, 함수합성의 결과가 달라지고 예상치 못한 값이 리턴될수있는데..
// -> Promise를 이용해, 예상치 못할 때, reject을 시키면 then이 아닌 catch가 실행되어, 안전하게 합성할수있다.
// -> 항상 같은 응답을 응답받을수 있다. f(g(x)) = g(x)

// // before
// const r = fg(2);
// log(r)
//
// users.pop()
// users.pop()
//
// //after
// const r2 = fg(2);
// log(r2)