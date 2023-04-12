import {log} from '../fxjs/libs.js'

// 1. callback방식 vs promise방식
// 2. 함수형프로그래밍과 연관해서 프라미스가 어떻게 다른지, 프라미스를 어떻게 활용해야하는지.?

function add10(a, callback){
    setTimeout(() => callback(a + 10), 1000);
}

const a = add10(5, res => {
    add10(res, res => {
        add10(res , res => {
            log(res)
        })
    })
})
log(a)

function add20(a){
    // promise 만들어서 "return" -> return이 중요함 add10과의 큰 차별점
    return new Promise(resolve => setTimeout(() => resolve(a + 20), 1000));
}
function add30(a){
    return new Promise(res => setTimeout(() => res(a + 30), 1000));
}

const b = add20(5)
    .then(res => add20(res))
    .then(add20)
    .then(log)

log(b) // Promise { <pending> } - 대기되고있다는 비동기 상황을 값으로 만들어 리턴하는 것이 Promise의 중요한 점! 콜백함수와의 중요한 차이점

add30(5)
    .then(add30)
    .then(add30)
    .then(log)

// Promise와 callback의 가장 큰 차이점은 단순히 then메서드들로 비동기 결과값을 꺼낼수있는게 아니라... 콜백지옥 해결할수있다는게 아니라..
// -> Promise는 비동기상황을 1급값으로 다룬다. (**중요!**)
// -> Promise라는 클래스를통해서 인스턴스를 반환하는데, 그 인스턴스는 '대기', '성공', '실패'(비동기 상황)를 다루는 일급값으로 이루어져있다. (대기되고있다는 값, 성공했다는 값, 실패했다는 값)
// -> 비동기상황에 대한 값을 만들어 리턴을한다. (return new Promise(resolve => ~) - 콜백함수는 하지 못하는것

//why? 비동기상황을 값으로 다루는게 왜 중요한데?
// 값으로 다룬다는 것은 어떤 변수에 할당, 함수에 전달, 함수에서 리턴등 다양한 일을 할 수 있고 계속해서 사용할 수 있다. 그래서 중요하다.
// 콜백함수는 비동기 상황이 값이 아닌 코드로써 존재하기에 계속해서 사용하기가 어렵다. 해당 코드 컨텍스트 내에서만 사용이 가능하다.
// (일급이니, promise를 통해 값으로 사용해서, 표현력 높일수도 있을듯.)
