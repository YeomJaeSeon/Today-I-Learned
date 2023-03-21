"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var globals_1 = require("@jest/globals");
var dice_1 = require("../../src/dice");
(0, globals_1.describe)('game move test', function () {
    (0, globals_1.describe)('random dice test', function () {
        (0, globals_1.test)('move 1', function () {
            //given
            var game = new dice_1.Game(new dice_1.RandomDice(4, 7));
            //when
            game.move();
            //then
            (0, globals_1.expect)(game.location).toEqual(1);
        });
        (0, globals_1.test)('move 2', function () {
            //given
            var game = new dice_1.Game(new dice_1.RandomDice(1, 3));
            //when
            game.move();
            //then
            (0, globals_1.expect)(game.location).toEqual(2);
        });
    });
    (0, globals_1.describe)('static dice test', function () {
        (0, globals_1.test)('move 3', function () {
            //given
            var game = new dice_1.Game(new dice_1.StaticDice(1, 7));
            //when
            game.move();
            //then
            (0, globals_1.expect)(game.location).toEqual(1);
        });
        (0, globals_1.test)('move 2', function () {
            //given
            var game = new dice_1.Game(new dice_1.StaticDice(1, 3));
            //when
            game.move();
            //then
            (0, globals_1.expect)(game.location).toEqual(2);
        });
    });
});
