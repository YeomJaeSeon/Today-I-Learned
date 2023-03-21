const promise1 = new Promise((res, rej) => {
    setTimeout(() => {
        return res(1);
    }, 1000);
})

/**
 * promise 체이닝
 * then은 Promise 반환
 */

promise1
    .then(res => {
        console.log(res)

        return res * 2;
    })
    .then(res => {
        console.log(res)
    })
/**
 * promsie chaining
 * 
 * 하나의 promise의 result를 계속사용.
 */
// promise1
//     .then(res => {
//         console.log(res);

//         return res * 2;
//     })

// promise1.then(res => {
//     console.log(res)

//     return res * 2
// })

/**
 * promise chaining
 * 
 * 새로운 프라미스 객체 반환
 */

// promise1.then(result => {
//     console.log(result); // 1

//     return new Promise((res, rej) => {
//         setTimeout(() => {
//             res(result * 2);
//         }, 1000);
//     })
// }).then(result => {
//     console.log(result); // 2

//     return new Promise((res, rej) => {
//         setTimeout(() => {
//             res(result * 2);
//         }, 1000)
//     })
// }).then(result => {
//     console.log(result); // 4

//     return new Promise((res, rej) => {
//         setTimeout(() =>{
//             res(result * 2);
//         }, 1000)
//     })
// })

