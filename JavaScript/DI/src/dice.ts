export interface IDice{
    value(): number;
}

export class StaticDice implements IDice{
    private _min: number;
    private _max: number;
    constructor(_min: number, _max: number){
        this._min = _min;
        this._max = _max;
    }

    public value(){
        return this._max - this._min;
    }
}

export class RandomDice implements IDice{
    private _min: number;
    private _max: number;
    constructor(_min: number, _max: number){
        this._min = _min;
        this._max = _max;
    }

    public value(){
        return Math.floor(Math.random() * this._max) + this._min;
    }
}

export class Game{
    private _dice: IDice;
    private _location = 0;

    // Dependency Injection - 외부에서 의존성을 주입받으면, 테스트코드 쉽게 작성할수있음.
    constructor(_dice: IDice){
        this._dice = _dice;
    }

    public get location(){
        return this._location;
    }

    public move(){
        if(this._dice.value() >= 4) this._location += 1;
        else this._location += 2;
    }
}
