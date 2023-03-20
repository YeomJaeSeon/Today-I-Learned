interface IBurgerRecipe{
    makeBurger(): void;
}

class CheeseBurgerRecipe implements IBurgerRecipe{
    public makeBurger(){
        return 'make cheese burger!'
    }
}

class GalbiBurgerRecipe implements IBurgerRecipe{
    public makeBurger(){
        return 'make galbi burger!';
    }
}

class StrawberryBurgerRecipe implements IBurgerRecipe{
    public makeBurger(){
        return 'make strawberry burger!';
    }
}

class BuregerChef{
    private readonly _buregerRecipe: IBurgerRecipe;

    constructor(burgerRecipe: IBurgerRecipe){
        this._buregerRecipe = burgerRecipe;
    }

    public get burgerRecipe(){
        return this._buregerRecipe;
    }
}

// 외부에서 의존성을 주입해줌. DI
const buregerChef = new BuregerChef(
    new GalbiBurgerRecipe()
);
console.log(buregerChef.burgerRecipe.makeBurger())