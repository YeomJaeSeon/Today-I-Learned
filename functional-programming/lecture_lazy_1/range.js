// range 함수: 숫자 하나 받고 숫자의 크기의 배열을 리턴
import {log, reduce} from '../fxjs/libs.js';

const add = (a, b) => a + b;

const range = l => {
    let i = -1;
    const res = [];
    while(++i < l){
        log(i, 'range')
        res.push(i);
    }

    return res;
};

const list = range(4);
// log(list)
// log(reduce(add, list));