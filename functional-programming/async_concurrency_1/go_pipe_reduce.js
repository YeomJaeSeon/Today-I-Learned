import {log, go, pipe, reduce} from '../fxjs/libs.js'
// go, pipe, reduce 비동기 제어

go(
    Promise.resolve(1),
    a => a + 10,
    a => Promise.reject('error !'),
    // a => Promise.resolve(a + 100),
    a => a + 1000,
    a => a + 10000,
    log
).catch(a => console.log(a));

/**
 * 단순히 Promise로 콜백지옥을 해결하는 용도로만 사용하는 것이아니라 Promise를 이용해 비동기상황을 '값'으로써
 * 내가원하는 로직을 사용한다거나, 내가원하는 시점에 내가원하는 방식으로 적절한 시점에 받아둔 함수를 실행하는 고차함수를 만든다는지 활용이 가능하다.
 * 이것이 바로 "Promise"의 장점이다.
 */