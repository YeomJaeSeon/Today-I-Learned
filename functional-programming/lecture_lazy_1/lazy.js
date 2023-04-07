// 느긋한 L.range
import {log, reduce} from '../fxjs/libs.js';

const add = (a, b) => a + b;
export const L = {};

L.range = function *(l) {
    let i = -1;
    while(++i < l){
        yield i;
    }
};

const list = L.range(4);
// log(list) // 콘솔에 로그 출력되지 않는다. -> 이터레이터 내부를 순회할때마다 평가가된다. 그전엔 평가가 되지않는다. range와 다른점
log(reduce(add, list));

/**
 * range vs L.range
 *
 * - range
 * range 리턴값은 이터러블인 배열
 * 순회하기 전에도 이미 평가가 되어 배열이 만들어져있음. 이는 메모리상에 해당 배열이 만들어져 있다는 의미.
 * reduce함수를 호출 할때도, 내부적으로 이터러블인 Array는 이터레이터를 만드는 작업도함. 이는 또 메모리에 해당 이터레이터가 생성이된다는 의미
 *
 * - L.range
 * L.range 리턴값은 그 자체로 이터레이터.
 * 순회하기 전엔, 평가가 되지 않음.
 * reduce함수 호출 할대도, well formed 이터레이터이기 때문에 자신을 리턴하여 새롭게 메모리에 이터레이터를 올리지 않음
 */

function test(name, time, f){
    console.time(name);
    while (time--) {
        f();
    }
    console.timeEnd(name)
}

// test('L.range', 100, () => reduce(add, L.range(1000000)))