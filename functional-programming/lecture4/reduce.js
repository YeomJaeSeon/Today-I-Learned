const nl = () => console.log('================')
const log = console.log;

// reduce
// 축약(누적)하며 하나의 값으로 만드는 함수

const nums = [1, 2, 3, 4, 5, 6];

let total = 0;
for(const n of nums){
    total += n;
}

log(total);
nl()

const reduce = (f, iter, acc) => {
    for(const n of iter){
        if(acc === undefined){
            acc = n;
            continue;
        }
        acc = f(acc, n);
    }

    return acc;
}

const add = (a, b) => a + b;
log(reduce(add, [1, 2, 3, 4, 5, 6], 0));
// log(add(add(add(add(add(0, 1), 2), 3), 4), 5));

log(reduce(add, [1, 2, 3, 4, 5, 6], 0));
log(reduce(add, [1, 2, 3, 4, 5, 6])); 
// -> log(reduce(add, 1, [2, 3, 4, 5, 6]));