const log = console.log;

const reduce = (f, acc ,iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    for(const a of iter){
        acc = f(acc, a);
    }

    return acc;
}
log(reduce((a, b) => a + b, 0,[1, 2, 3, 4, 5]));
log(reduce((a, b) => a + b, [1, 2, 3, 4, 5]));

const add = (a, b) => a + b;
// 코드(함수)를 값으로 다루어 표현력 높이기
// 1. go

const go = (...args) => reduce((a, f) => f(a), args);

go(
    add(0, 1),
    a => a + 10,
    a => a + 100,
    log
);

// 2. pipe
const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);

const f = pipe(
    (...args) => args.reduce(add, 100),
    a => a + 10,
    a => a + 100
)

log(f(0, 1, 200, 300));