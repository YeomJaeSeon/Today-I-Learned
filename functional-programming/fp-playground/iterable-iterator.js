const nl = () => console.log('--------------------');
const log = console.log;

const arr = [1, 2, 3];

const arrIter = arr[Symbol.iterator]();

console.log(arrIter.next())
console.log(arrIter.next())
console.log(arrIter.next())
console.log(arrIter.next())
nl()

const arrIter2 = arr[Symbol.iterator]();
for(const a of arrIter2){
    console.log(a)
}
nl()
for(const a of arr){
    console.log(a)
}
nl()

const iterOfArrIter = arrIter[Symbol.iterator]()
console.log(iterOfArrIter.next())
for(const a of iterOfArrIter){
    console.log(a)
}
console.log(iterOfArrIter === arrIter)

nl()
const myIterable = {
    [Symbol.iterator]:function(){
        i = 3;
        return {
            next: function(){
                return i === 0 ? {
                    value: undefined,
                    done: true 
                } : {
                    value: i--,
                    done: false
                }
            },
            [Symbol.iterator]: function(){
                return this;
            }
        }
    }
}

// const myIter = myIterable[Symbol.iterator]()

const myIter = myIterable[Symbol.iterator]()
console.log(...myIter)