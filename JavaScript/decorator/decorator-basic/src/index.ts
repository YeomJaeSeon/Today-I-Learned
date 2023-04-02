// class decorator
function ClassDecorator(constructor: typeof Product){
    const method = constructor.prototype.repair;

    constructor.prototype.repair = (part1: string, part2: string, part3: string) => {
        console.log('@ClassDecorator start')
        method(part1, part2, part3);
        console.log('@ClassDecorator end')
    }
}

// method decorator
function LogError(message: string){
    return function (prototype: any, key: string, desc: PropertyDescriptor){
        const method= desc.value;

        desc.value = function(part1: string, part2: string, part3: string){
            try{
                method(part1, part2, part3);
            }catch(err){
                console.log(message)
            }
        }
    }
}

// param decorator
function ParameterDecorator(prototype: any, key: string, index: number){
}

// property decorator
function PropertyDecorator(prototype: any, key: string){
    console.log(prototype)
    console.log(key)
}


// accessor decorator
function AccessorDecorator(prototype:any, key: string){
    console.log(prototype)
    console.log(key)
}

// TODO: class decorator
@ClassDecorator
class Product{
    @PropertyDecorator
    name: string = 'computer';

    @AccessorDecorator
    get getName(): string{
        return `${this.name} (a+)`
    }

    @LogError('error handled')
    repair(
        @ParameterDecorator
        part1: string,
        @ParameterDecorator
        part2: string,
        @ParameterDecorator
        part3: string
    ): void{
        console.log('[', part1, part2, part3, ']', '수리 완료')
        throw new Error('error 발생!')
    }
}

const computer = new Product();
computer.repair('메모리', '디스크', '마더보드')