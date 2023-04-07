// range 함수: 숫자 하나 받고 숫자의 크기의 배열을 리턴
import {log, reduce} from '../fxjs/libs.js';

const add = (a, b) => a + b;

export const range = l => {
    let i = -1;
    const res = [];
    while(++i < l){
        res.push(i);
    }

    return res;
};

const list = range(4);
// log(list)
// log(reduce(add, list));

function test(name, time, f){
    console.time(name);
    while (time--) {
        f();
    }
    console.timeEnd(name)
}

// test('range', 100, () => reduce(add, range(1000000)))