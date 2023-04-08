/**
 * 이터러블 중심 프로그래밍에서의 지연평가
 *
 * 제떄 계산법, 느긋한 계산법 (영리한)
 *
 * 제너레이터/이터레이터 프로토콜 기반으로 구현(JS내에서 지원하는 문법으로)
 */
import {log} from '../fxjs/libs.js'
import {L} from './lazy.js'

L.map = function *(f, iter){
    for(const a of iter){
        yield f(a);
    }
}


const it = L.map(a => a + 1, [1, 2, 3, 4]) // 여기선 평가가 되지않음

// 실제로 해당 이터레이터에 접근할때마다 (=next()호출될떄마다) 평가가됨 - 영리한 지연
// log(it.next())
// log(it.next())
// log(it.next())
// log(it.next())
// log(it.next())

// log([...it])
log([it.next().value])
