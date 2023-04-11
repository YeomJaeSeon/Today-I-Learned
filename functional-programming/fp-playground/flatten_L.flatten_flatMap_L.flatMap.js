import {log, L, take, go, pipe, map} from '../fxjs/libs.js'

const takeAll = take(Infinity);

// L.flatten
L.flatten = function* (iter){
    for(const a of iter){
        if(a && a[Symbol.iterator]){
            for(const b of a){
                yield b;
            }
        }else{
            yield a;
        }
    }
}

// const it = L.flatten([[1,2, 3], 4, [5], [6, 7, 8]]);
// log(
//     take(Infinity, it)
// )
go(
    [[1,2, 3], 4, [5], [6, 7, 8]],
    L.flatten,
    take(Infinity),
    log
)


// log(
//     take(3, L.flatten([1,2, 3], 4, [5], [6, 7, 8]))
// )


// flatten - 지연평가하는 L.flatten을 이용한 즉시평가 함수
const flatten = pipe(
    L.flatten,
    takeAll
)

let it = flatten([[1,2, 3], 4, [5], [6, 7, 8]]);
log(it)

// L.flatMap
log([[1, 2], [3], [4, 5, 6]].flatMap(a => a.map(a => a * a)))

L.flatMap = pipe(
    L.map,
    L.flatten
)

const it3 = L.flatMap(map(a => a * a), [[1, 2], [3], [4, 5, 6]]);
log([...it3])

// 즉시평가하는 flatMap
const flatMap = pipe(
    L.map,
    flatten
)

log(flatMap(map(a => a * a), [[1, 2], [3], [4, 5, 6]])); // 즉시평가