export interface IBurgerRecipe{
    makeBurger(): void;
}

export class CheeseBurgerRecipe implements IBurgerRecipe{
    public makeBurger(){
        return 'make cheese burger!'
    }
}

export class GalbiBurgerRecipe implements IBurgerRecipe{
    public makeBurger(){
        return 'make galbi burger!';
    }
}

export class StrawberryBurgerRecipe implements IBurgerRecipe{
    public makeBurger(){
        return 'make strawberry burger!';
    }
}

export class BuregerChef{
    private readonly _buregerRecipe: IBurgerRecipe;

    constructor(burgerRecipe: IBurgerRecipe){
        this._buregerRecipe = burgerRecipe;
    }

    public get burgerRecipe(){
        return this._buregerRecipe;
    }
}
