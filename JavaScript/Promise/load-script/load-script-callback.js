/**
 * callback함수를 이용해 비동기 작업을 동기적으로 기다려보자아~
 */

function loadScript(url, callback){
    setTimeout(() => {
        console.log(`${url} load success!`)
        callback();
    }, 1000)
}

// use callback
loadScript('www.naver.com', () => {
    console.log('execute after load script')
    loadScript('www.daum.net', () => {
        console.log('execute after load script')
        loadScript('www.google.com', () => {
            console.log('execute after load script')
        })
    })
})



// fetch('https://api.github.com/users/YeomJaeSeon', (response) => {
//     console.log(response)
// });