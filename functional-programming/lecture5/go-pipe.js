const reduce = (f, acc, iter) => {
    if(iter === undefined){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const n of iter){
        acc = f(acc, n);
    }

    return acc;
}

// 코드(함수)를 값으로 다루어 표현력 높이기

// fp에서는 코드(함수)를 값으로 다루는 아이디어를 많이 사용함
// 코드(함수)를 값으로 다룰수 있기에, 어떤 함수가 코드인 함수를 받아서 평가하는 시점을 원하는 대로, 다룰수 있기 때문에 코드의 표현력을 높일수 있다.
// by 유인동.

// -> 코드(함수)를 값으로 다루어 표현력을 높여보자

const log = console.log;
const add = (a, b) => a + b;

// go
// 곧바로 평가
const go = (...args) => reduce((a, f) => f(a), args);

go(
    add(0, 1),
    a => a + 10,
    a => a + 100,
    log,
)


// pipe
// 함수를 리턴하는 함수 (나열된 함수를 합성한 함수를 만드는 함수)
// 평가를 지연
const pipe1 = (...fs) => (a) => go(a, ...fs);

const f1 = pipe1(
    a => a + 1,
    a => a + 10,
    a => a + 100
);

log(f1(0))

const pipe2 = (f, ...fs) => (...a) => go(f(...a), ...fs);

const f2 = pipe2(
    (...args) => reduce((a, b) => a * b, args),
    a => a + 10,
    a => a + 100
);

log(f2(1, 2, 3, 4, 5, 6))