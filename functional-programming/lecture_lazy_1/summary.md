# 제너레이터 & 이터레이터를 이용한 지연평가

## 즉시 평가
```javascript
const range = (l) => {
    const res = [];
    let i = 0;
    while(i < l){
        res.push(i);
    }
    return res;
}
const map = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        res.push(f(a));
    }
    return res;
})

const filter = curry((f, iter) => {
    const res = [];
    for(const a of iter){
        if(f(a)){
            res.push(a);
        }
    }
    return res;
})

const take = (limit, iter) => {
    const res = [];
    for(const a of iter){
        res.push(a)
        if(res.length === limit) return res;
    }
    
    return res;
}
```

```javascript
go(
    range(10),
    map(a => a + 10),
    filter(a => a % 2 === 0),
    take(10),
    log
)
```
- 즉시 평가는 즉시 평가하기에 '가로'로 모두 평가된다.
- 실제로 사용되던 안되던 즉시 평가하기에 효율적이지 못하다.

## 지연평가
```javascript
const L = {
    range: function*(l){
        let i = 0;
        while(i < l){
            yield i;
        }[lazy.js](lazy.js)
    },
    map: curry(function*(f, iter){
        for(const a of iter){
            yield f(a);
        }
    }),
    filter: curry(function*(f, iter){
        for(const a of iter){
            if(f(a)) {yield a;}
        }
    })
}
```

```javascript
go(
    L.range(10),
    L.map(a => a + 10),
    L.filter(a => a % 2 === 0),
    take(10),
    log
)
```

- 지연 평가는 `next`함수가 호출되기 전까지 평가가 지연되고, 해당 함수가 호출되면 '세로'로 평가된다.
- 실제로 사용되면(`next` 호출) 평가되기에 효율적이다.