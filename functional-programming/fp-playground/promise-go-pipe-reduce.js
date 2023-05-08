const delay1000 = a => new Promise(resolve => setTimeout(() => resolve(a), 1000));

const add5 = a => a + 5;

const go1 = (a, f) => a instanceof Promise ? a.then(f) : f(a);

// go1(
//     go1(
//         10,
//         add5
//     ),
//     console.log
// );
//
// go1(
//     go1(
//         delay1000(10),
//         add5
//     ),
//     console.log
// )

const curry = (f) => (a, ..._) => _.length ? f(a, ..._) : (..._) => f(a, ..._);

export const reduce = curry((f, acc, iter) => {
    if(!iter){
        iter = acc[Symbol.iterator]();
        acc = iter.next().value;
    }

    // for(const a of iter){
    //     acc = acc instanceof Promise ? acc.then(acc => f(acc, a)) : f(acc, a);
    // }

    return go1(acc, function recur(acc){
        for(const a of iter){
            acc = f(acc, a);
            if(acc instanceof Promise) return acc.then(recur)
        }

        return acc;
    })
});

export const go = (...args) => reduce((a, f) => f(a), args);

export const pipe = (f, ...fs) => (...as) => go(f(...as), ...fs);


go(
    Promise.resolve(1),
    a => a + 10,
    a => Promise.resolve(a + 100),
    a => a + 1000,
    a => a + 10000,
    console.log
)