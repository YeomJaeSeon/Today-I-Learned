# 콜백

- 시간이 오래걸리는, 언제 끝날지 모르는 함수를 동기적으로 기다리기만하면 효율적이지 못하다.
- 그래서 기다리지 않는 비동기 함수가 등장하였다.
- 그런데 비동기 함수가 끝나야 실행되는 함수는 어떻게 "정의"할까?
-> **비동기 함수를 동기적으로 기다리려면 어떻게 해야할까?**

```javascript
const loadScript = (script)=> {
    setTimeout(() => {
        console.log(`${script} load success!`)
    }, 2000)
} 

loadScript('/my/script.js');
console.log('로드되고 꼭 실행되어야하는 구문임!')
```
- loadScript는 언제 끝날지 모르는 함수이기에 비동기함수로 정의하였다. 또한, console.log구문은 해당 비동기 함수가 끝나고 꼭 동작해야하는 함수이다. 결과는?

```
로드되고 꼭 실행되어야하는 구문임!
/my/script.js load success!
```
- 비동기 함수를 기다리지않고 그다음 구문이 실행되는 걸 확인할수있다.

-> 어떻게하지?

## 콜백함수 이용
- 콜백함수를 이용하면 비동기함수가 끝나고 실행되는 구문을 만들수있다.

```javascript
const loadScript = (script, callback)=> {
    setTimeout(() => {
        console.log(`${script} load success!`)
        callback();
    }, 2000)
} 

loadScript('/my/script.js', function(){
    console.log('로드되고 꼭 실행되어야하는 구문임!')
});
```
- 결과는?
```
/my/script.js load success!
로드되고 꼭 실행되어야하는 구문임!
```

- 음.. 비동기 함수 인자에 비동기함수가 끝나고 실행되어야하는 동작을 정의한 함수를 넣으면 비동기 함수를 동기적으로 기다린다음 실행하는 구문을 만들수있구나..
- 그럼이게 왜 콜백인가?
-> '나중에 호출할 함수'이기에 콜백함수라고한다. (아~ 비동기함수가 끝나고 나중에 호출할거닌까?, 이름이 되게 직관적이다.)

- 그럼 비동기함수를 동기적으로 기다리게 도와주는 콜백함수는 무조건 좋을까?

## 콜백지옥
- 가독성 측면에서 최악이다.

```javascript
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
```
- 코드가 옆으로 점점 길어지는걸 볼수있다.
- 매우 간단한 예시라 이정도이지 콜백함수 내부에 여러 에러처리하는 로직과 더 복잡한 로직이 있다면 가독성은 매우안좋을것이다.
(비동기 함수도 실패할수있다. 항상 http를 이용한 api가 성공을 뱉는가? 특히 회사 외부의 서버의 api 를 이용하면 더 주의해야할것.!)

## 콜백지옥 벗어나기
- 콜백함수들을 익명의 함수로써 정의하지말고 이름이 있는 함수로 정의한뒤 호출하면 좀 깔끔해진다.

```javascript
const loadScript = (script, callback)=> {
    setTimeout(() => {
        console.log(`${script} load success!`)
        callback(); // 여기서 실행해야 비동기적인 작업을 기다린후 실행할수있다.
    }, 2000)
} 


function load1(){
    loadScript('/my/script2.js', load2);
}

function load2(){
    loadScript('/my/script3.js', load3);
}

function load3(){
}

loadScript('/my/script.js', load1);
```
- 오른쪽으로 길어지는 콜백지옥에선 벗어났지만 함수끼리 의존성이 너무 많아 다른곳에서 사용하기도 어렵고, 어떻게 동작하는지 보려면 함수가 호출됨에 따라 따라가야하는 단점이있다.
- 어떻게할까?

## 정리
- 비동기적인 동작을 동기적으로 기다리게끔하려고 콜백지옥이 있는데 가독성측면에서 좋지 못하다. 
- Promise라는 것을 이용하면 비동기적인 동작을 기다린 후에 동작하는 구문을 만들수도있고 콜백함수의 단점인 가독성 문제도 해결할수있다.