function loadScript(src){
    return new Promise((res, rej) => {
        setTimeout(() => {
            res(`${src} load success!`);
        }, 1000)
    })
}

// 1
loadScript('www.naver.com')
    .then((result) => {
        console.log(result)

        return new Promise((res,rej) => {
            rej(new Error('not found!'));
        })
    }, (err) => {
        console.log(err.message)
    })

// 2
loadScript('www.naver.com')
    .then((result) => {
        console.log(result)

        return new Promise((res,rej) => {
            rej(new Error('not found!'));
        })
    })
    .catch((err) => {
        console.log(err.message)
    })

    /**
     * 1과 2의 차이는
     * 
     * 1은 then의 콜백함수에서 발생하는 에러에 대해 예외처리 하지못하지만
     * 
     * 2는 then의 콜백함수에서 발생하는 에러에 대해 예외처리 가능하다.
     * 
     * -> then() 메서드는 Promise 객체를 반환하기 때문에.
     */