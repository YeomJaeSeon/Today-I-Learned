import {log, L, go, take, pipe, join} from '../fxjs/libs.js'

const isIterable = (a) => a && a[Symbol.iterator];

// L.flatten
L.flatten = function* f(iter){
    for(const a of iter){
        if(isIterable(a)){
            // for(const b of a){
            //     yield b;
            // }
            yield *a;
        }else{
            yield a;
        }
    }
}

const flatten = pipe(
    L.flatten,
    take(Infinity)
)
log('flatten', flatten([[1, 2], 3, 4, [5, 6], [7, 8, 9]]))

// L.deepFlatten
L.deepFlatten = function* f(iter){
    for(const a of iter){
        if(isIterable(a)){
            yield *f(a)
        }else{
            yield a;
        }
    }
}

const deepFlatten = pipe(
    L.deepFlatten,
    take(Infinity),
)

log('deepFlatten', deepFlatten([[[[[1, 2]]]], 3, 4, [5, 6], [7, 8, 9]]));

const it = L.flatten([[1, 2], 3, 4, [5, 6], [7, 8, 9]]);
log([...it])
go(
    [[1, 2], 3, 4, [5, 6], [7, 8, 9]],
    (iter) => L.flatten(iter),
    // L.flatten,
    take(Infinity),
    log
)

const deepIt = L.deepFlatten([[[1, 2]], 3, 4, [5, 6], [7, 8, 9]])
log([...deepIt])
go(
    [[1, 2], 3, 4, [5, 6], [7, 8, 9]],
    L.deepFlatten,
    take(Infinity),
    log
)
// go(
//     [[1, 2], 3, 4, [5, 6], [7, 8, 9]],
//     L.flatten,
//     take(Infinity),
//     log
// )

//take L.flatten
go(
    [[1, 2], 3, 4, [5, 6], [7, 8, 9]],
    L.flatten,
    take(2),
    log
)
//join L.flatten
go(
    [[1, 2], 3, 4, [5, 6], [7, 8, 9]],
    L.flatten,
    join(','),
    log
)

//join L.deepFlatten
go(
    [[[[1, 2]]], 3, [[[[[4]]]]], [5, 6], [7, 8, 9]],
    L.deepFlatten,
    join('='),
    log
)