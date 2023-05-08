// promise의 중요한 규칙

Promise.resolve(Promise.resolve(Promise.resolve(1))).then(function (a){
    console.log(a)
})

new Promise(res => res(new Promise(res => res(1)))).then(console.log)

// Promise가 아무리 중첩되어도, resolve된 값을 가져온다.(1을 가져옴) Promise.resolve가 a에 오는게 아니라..
// 이것은 언어의 약속 규칙이다.
