const nl = () => console.log('=============');

function *gen(){
    yield 1;
    yield 2;
    yield 3;
}

const iterable1 = gen()

console.log(...iterable1)

const iterable2 = gen()
for(const a of iterable2){
    console.log(a)
}
nl()

function* square(a){
    for(let i = 1; i <= a; i++){
        yield i * i;
    }
}

for(const a of square(10)){
    console.log(a)
}

const [head, ...tail] = square(20)
console.log(head)
console.log(tail)

nl()