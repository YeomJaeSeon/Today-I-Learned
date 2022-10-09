/**
 * change js array to java array format
 */

const stdin = process.openStdin();

stdin.on('data', function(input) {
    console.log(`input: ${input}`);
    console.log(`change result: ${changeBracket(input)}`)
});

function changeBracket(jsFormatBuffer){
    return jsFormatBuffer.toString('utf-8').split('').map((_d) => {
        if(_d === '['){
            return '{'
        }

        if(_d === ']'){
            return '}'
        }
        return _d;
    }).join('');
}
