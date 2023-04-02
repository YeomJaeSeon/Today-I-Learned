import 'reflect-metadata';

/**
 * literal
 */

const user = {
    firstName: 'yeom'
}
console.log(Object.getPrototypeOf(user))

Reflect.defineMetadata('lastName', 'jae seon', user)
Reflect.defineMetadata('phoneNumber', '010-8954-1457', user)

console.log(user) // metadata는 보여지지 않는다.

/**
 * metadata는 명시적으로 출력해야 볼수있따. 
 */
console.log(Reflect.getMetadata('lastName', user)); 
console.log(Reflect.getMetadata('phoneNumber', user))


/**
 * class
 */

class Sample{
    constructor(){
        /**
         * params: (metadata key, metadata value, prototype(instance.__proto__), property name)
         */
        Reflect.defineMetadata('hidden1', 'one', this, 'print')
    }

    /**
     * params: (metadata key, metadata value)
     */
    @Reflect.metadata('hidden2', 'two')
    print(){
        console.log('print')
    }
}

const instance = new Sample();
// metadata 는 명시적으로 출력해야알수있다.
console.log(instance)
console.log(Reflect.getMetadata('hidden1', instance, 'print'))
console.log(Reflect.getMetadata('hidden2', instance, 'print'))