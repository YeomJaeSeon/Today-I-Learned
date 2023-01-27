/**
 * 제너레이터 odd 함수를 이용해 순회하기
 */

function *limit(i, iter){
    for(const a of iter){
        yield a;

        if(a === i){
            return;
        }
    }
}


function *infinity(i = 0){
    while(true) yield i++;
}

function *odds(l){ 
    for(const i of limit(l, infinity(1))){
        if(i % 2 === 1){
            yield i;
        }

        if(i === l){
            return;
        }
    }
}

const iterator = odds(9);
console.log(iterator.next())
console.log(iterator.next())
console.log(iterator.next())
console.log(iterator.next())

for(const a of odds(10)){
    console.log(a)
}

/**
 * 전개연산자, 구조분해할당
 */

console.log(...odds(10));
console.log([...odds(10)])

const [a, b, ...rest] = odds(20);
console.log(a);
console.log(b)
console.log(rest)




