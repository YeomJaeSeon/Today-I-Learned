// 2초 후 스크립트를 로드하는 function
const loadScript = (script, callback)=> {
    setTimeout(() => {
        console.log(`${script} load success!`)
        callback(); // 여기서 실행해야 비동기적인 작업을 기다린후 실행할수있다.
    }, 2000)
} 


loadScript('/my/script.js', function() {
    loadScript('/my/script2.js', function() {
      loadScript('/my/script3.js', function() {
        // 세 스크립트 로딩이 끝난 후 실행됨
      });
    })
});

// callback X
const loadScript2 = (script)=> {
    setTimeout(() => {
        console.log(`${script} load success!`)
    }, 2000)
} 

loadScript2('/my/script.js');
loadScript2('/my/script2.js'); 
loadScript2('/my/script3.js');
// loadScript2 함수들은 동기적으로 기다리지않음. 모두 비동기함수임
