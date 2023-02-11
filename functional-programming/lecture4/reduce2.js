const nl = () => console.log('================')
const log = console.log;

// with yooindong

const arr = [1, 2, 3, 4, 5];

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

log(
    reduce((a, b) => a + b, 0, [1, 2, 3, 4, 5])
)

nl();

log(
    reduce((a, b) => a + b, [1, 2, 3, 4, 5])
)