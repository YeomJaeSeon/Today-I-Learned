import {reduce, log} from '../fxjs/libs.js'
// take
const take = (limit, iter) => {
    const res = [];
    for(const a of iter){
        res.push(a);
        if(res.length === limit) return res;
    }

    return res;
}

// range
const add = (a, b) => a + b;
const range = l => {
    const res = [];
    let i = 0;
    while(i < l){
        res.push(i);
        i++;
    }

    return res;
}

console.time('range')
const list = range(1000000);
log(list)
log(take(2, list))
// log(reduce(add, list))
console.timeEnd('range')

// Lazy range
const L = {}
L.range = function*(l){
    let i = 0;
    while(i < l){
        yield i;
        i++;
    }
}
console.time('L.range')
const lazyList = L.range(1000000);
log(lazyList)
log(take(2, lazyList));
// log(reduce(add, lazyList))
console.timeEnd('L.range')
