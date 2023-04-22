function solution(today, terms, privacies) {
    var answer = [];
    
    let map = {};
    
    for (let i=0; i < terms.length; i++) {
        const line = terms[i].split(" ");
        map[line[0]] = line[1];
    }
    
    const todayTotal = getTotalDay(today);
    
    for (let i=0; i < privacies.length; i++) {
        const line = privacies[i].split(" ");
        const curDay = line[0];
        const term = parseInt(map[line[1]]) * 28;
        const curTotal = getTotalDay(curDay);
        if (todayTotal >= curTotal + term) {
            answer.push(i + 1);
        }
    }
    
    return answer;
}

function getTotalDay(day) {
    const y = parseInt(day.substring(0,4)) * 28 * 12;
    const m = parseInt(day.substring(5,7)) * 28;
    const d = parseInt(day.substring(8, 10));
    return y + m + d;
}