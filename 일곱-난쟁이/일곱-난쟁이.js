const fs = require('fs');
let dwarfs = fs.readFileSync('/dev/stdin').toString().trim().split('\n');
dwarfs = dwarfs.map(el => parseInt(el));

main(dwarfs);

function main(dwarfs) {
    const combs = getComb(dwarfs, 7);
    const answerArr = solution(combs);
}

function getComb (arr, num) {
    const results = [];
    if(num === 1){
        return arr.map(el => [el]);
    }
    arr.forEach((fixed, index, origin) => {
        const rest = origin.slice(index + 1);
        const combs = getComb(rest, num - 1);
        const attached = combs.map(comb => [fixed, ...comb]);
        results.push(...attached)
    });
    return results;
}

function solution(combs) {
    let answer = [];
    for (let i = 0; i < combs.length; i++) {
        const sum = combs[i].reduce((acc, curr) => {
            return acc + curr;
        })
        if (sum === 100) {
            answer = combs[i];
            break;
        }
    }
    answer.sort((a, b) => {
        return a - b;
    });
    console.log(answer.join('\n'));
}