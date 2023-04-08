import {log} from '../fxjs/libs.js'
import {L} from './lazy.js'

L.filter = function* (f, iter){
    for(const a of iter){
        if(f(a)) yield a;
    }
}

const iter = L.filter((a) => a > 1,[1, 2, 3]); // 이터레이터를 이용하여 지연평가하여 이떈 평가가 되지 않음
log(iter.next())
log(iter.next())
log(iter.next())
