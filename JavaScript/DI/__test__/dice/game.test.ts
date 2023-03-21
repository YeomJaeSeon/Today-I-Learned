import { describe, expect, test } from "@jest/globals";
import { Game, RandomDice, StaticDice } from "../../src/dice";

describe('game move test', () => {
    describe('random dice test', () =>{
        test('move 1', () =>{
            //given
            const game = new Game(new RandomDice(4, 7));

            //when
            game.move();

            //then
            expect(game.location).toEqual(1)
        })
        test('move 2', () =>{
            //given
            const game = new Game(new RandomDice(1, 3));

            //when
            game.move();
            
            //then
            expect(game.location).toEqual(2)
        })
    })
    describe('static dice test',()=> {
        test('move 3', () =>{
            //given
            const game = new Game(new StaticDice(1, 7));

            //when
            game.move();

            //then
            expect(game.location).toEqual(1)
        })
        test('move 2', () =>{
            //given
            const game = new Game(new StaticDice(1, 3));

            //when
            game.move();
            
            //then
            expect(game.location).toEqual(2)
        })
    })
})