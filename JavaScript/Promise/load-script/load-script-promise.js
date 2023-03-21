function loadScript(url){
    return new Promise((res, rej) => {
        setTimeout(() => {
            res(`${url} load success!`)
        }, 1000)
    })
}

loadScript('www.naver.com')
    .then((result) => {
        console.log(result)
        console.log('execute after load script')

        return loadScript('www.daum.net');
    })
    .then((result) => {
        console.log(result)
        console.log('execute after load script')

        return loadScript('www.google.com')
    })
    .then((result) => {
        console.log(result)
        console.log('execute after load script')
    })

    /**
     * then은 Promise 객체를 반환함.
     * 
     * 그래서 then() 리턴값은 then메서드 사용할수있음
     */
