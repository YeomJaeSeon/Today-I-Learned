import { BuregerChef, GalbiBurgerRecipe } from "./burger";
import { Game, RandomDice } from "./dice";

// 외부에서 의존성을 주입해줌. DI 
const buregerChef = new BuregerChef(
    new GalbiBurgerRecipe() 
);
console.log(buregerChef.burgerRecipe.makeBurger())


//random dice
const game = new Game(new RandomDice(1, 7));
game.move();
console.log(game.location)