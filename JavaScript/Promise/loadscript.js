// 콜백함수를 이용하여 비동기적인 작업을 기다리지 않고 Promise 객체를 이용하여 비동기작업을 기다려보자.

// 2초 후 스크립트를 로드하는 function
const loadScript = (script)=> {
    return new Promise(function(resolve, reject){
        setTimeout(() => {
            resolve(`${script} load success!`);
        }, 2000)
    })
}

let promise = loadScript('http://dungLab.com');

promise.then(result => {
    console.log(result);
}, error => {
    console.log(error.message)
})
