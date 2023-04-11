import {curry, log, L, flatten, deepFlatten, pipe, map, take, range} from '../fxjs/libs.js'

// flatMap
//map + flatten

// log([[1, 2], [3, 4], [5, 6, 7], [10]].flatMap(a => a));
// log([[1, 2], [3, 4], [5, 6, 7], [10]].flatMap(a => a.map(a => a * a)));
// log(
//     flatten([[1, 2], [3, 4], [5, 6, 7], [10]].map(a => a.map(a => a * a)))
// );

// 지연평가하는 L.flatMap
L.flatMap = curry(pipe(L.map, L.flatten));

// 즉시평가하는 flatMap
const flatMap = pipe(
    L.map,
    flatten
)

const it = flatMap(map(a => a * a), [[1, 2], [3, 4], [5, 6, 7], [10]]);
log([...it])


// const it = L.flatMap(map(a => a * a), [[1, 2], [3, 4], [5, 6, 7], [10]]);
// log([...it])

const it2 = L.flatMap(L.range, map(a => a + 1, [1, 2, 3]));
// log([...it2])

log(take(3, it2))

// go(
//     [[1, 2], [3, 4], [5, 6, 7], [10]],
//     L.flatMap(map(a => a * a))
// )
//flattenMap의 등장은 비효율적으로 동작하는 로직때문

// L.flatMap = function*(f, iter){
//     for(const a of iter){
//         if(a && a[Symbol.iterator]){
//             for(const b of a){
//                 yield f(b);
//             }
//         }else{
//             yield f(a);
//         }
//     }
// }

// L.flatMap = pipe(
//     L.map,
//     L.flatten
// )
//
// const it = L.flatMap(map(a => a), [[1, 2], [3, 4], [5, 6, 7], [10]]);
// log([...it])
