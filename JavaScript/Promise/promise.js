// let fulfilledPromise = new Promise(function(resolve, reject){
//     // executor - promise 객체가 만들어지면 executor 함수는 자동으로(즉각적) 실행됨.

//     setTimeout(() => resolve("완료 ㅎㅎ"), 1000);
//     // 1초 뒤 state: fulfilled, result: "완료"(value)

//     // resolve, reject는 자바스크립트 엔진이 미리 정의한 빌트인 함수라 따로 정의할필욘없지만 개발자는 둘중 하나는 "반드시" 호출해줘야 한다.
// })

// let rejectedPromise = new Promise(function(resolve, reject){

//     setTimeout(() => reject(new Error("에러 발생!")), 1000)
// })

// const immadiateSettledPromise = new Promise((res, rej) => {
//     res('resolve@@');
// })

// // let preResolvePromise = new Promise(function(resolve, reject){
// //     resolve("hello");

// //     setTimeout(() => reject(new Error("씸각한 에러 발생!")), 1000)
// // })

// // console.log(fulfilledPromise.state); // 해당 프로퍼티에 직접 접근할수 없다.
// // .then, .catch, .finally 메서드를 사용하면 접근가능 ... why?

// // promise는 가수와 팬을 이어주는 역할만할뿐 (제작에 성공 - fulfilled, 제작에 실패 - rejected)
// // .then, .catch, .finally메서드를 이용하여 소비함수는 등록할수있다.

// // rejectedPromise.then(function(result){
// //     console.log(result);
// // }, function(error){
// //     console.log(error)
// // })
// // // then의 첫번째 콜백함수는 fulfilled되었을대 실행되는 함수, 두번째 콜백함수는 rejected되었을 때 실행하는 함수

// // rejectedPromise.then(null, console.log)
// // rejectedPromise.catch(console.log).finally(() => {
// //     console.log("hi")
// // })
// // // 두 구문은 완벽히 같다

// fulfilledPromise
//     .finally(() => console.log("promise settled(fulfilled)"))
//     .then(console.log) // finally를 거쳐 result가 then메서드까지옴

// rejectedPromise
//     .finally((_ => console.log("promise settled(rejected)")))
//     .catch(err => console.log(err.message))

// immadiateSettledPromise.then(console.log);
// // 결과가 나와있는 상태에서 팬은 구독을 바로 받을수있다! (현실과 달리 유연한 promise)

// // then, catch, finally 소비 메서드는 프라미스가 settled되기가지 기다림. 즉, 즉시 settled되면 기다릴 필요가 없음!

/**
 * problems
 */

// 1
// 1이 alert됨. 그리고 1초기다리고 아무것도안함
const p1 = new Promise((resolve, reject) => {
    resolve(1);

    setTimeout(() => resolve(2), 1000);
})

p1.then(console.log)

// 2
function delay(ms){
    return new Promise((resolve, reject) => {
        setTimeout(resolve, ms);
    })
}

delay(3000).then(() => console.log('3초 후 실행'));

// 3
// 콜백 -> 프라미스 객체 이용하긔
function startDelivery(food, callback){
    setTimeout(() => {
        callback(food);
    }, 3000);
}

startDelivery('짜장면', function (food){
    console.log(`${food} 배달 성공`)
})

function startDevliveryWithPromise(food){
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve(`${food} 배달 성공`);
        }, 3000);
    })
}


startDevliveryWithPromise('짜장면')
    .then(result => {
        console.log(result);
    })
    .finally(() => {
        console.log("끄읏~")
    })