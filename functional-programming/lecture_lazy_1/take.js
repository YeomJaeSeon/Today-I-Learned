import {log, reduce, curry, go} from '../fxjs/libs.js';
import {L} from './lazy.js';
import {range} from './range.js'

const take = curry((l, iter) => {
    const res = [];
    for(const a of iter){
        res.push(a);
        if(res.length == l) return res;
    }

    return res;
});

console.time('')
go(
    range(1_000_000),
    take(5),
    log
)
// log(take(5, range(1_000_000_000))); // 비효율적. why? 해당 크기만큼의 배열을 만들기 때문. (이터러블인 배열을 만듬)
console.timeEnd('')
console.time('')
go(
    L.range(1_000_000),
    take(5),
    log
)
// log(take(5, L.range(1_000_000))); // 효율적. why? 바로 평가가 지연되어, 5개만 가져올때만 실제로 가져오기에 효율적 (바로 이터레이터)
// log(take(5, L.range(Infinity))); // 이것도 가능하다. Infinity에 대한 이터레이터는 해당 이터레이터를 순회할때 평가가 되기 때문이다.때문이다
console.timeEnd('')

/**
 * 이터레이터를 통한 지연성
 */
// 이터레이터를 이용: 이터러블이 아닌 이터레이터를 사용하는 지연성 기법은 연산을 계쏙 미루다가, 실제로 이터레이터를 받아 순회하는 작업을 할때서야 평가가된다. - 효율적
// 이터러블을 이용: 배열과같은 이터러블은 배열이 생성되자마자 평가가 되기 떄문에, 지연성에 비해서 효율적이지 않다.