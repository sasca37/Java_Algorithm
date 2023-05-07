let Assignment = class {
    constructor(name, start, time) {
        this.name = name;
        this.start = timeToMinute(start);
        this.time = parseInt(time);
    }

};

function timeToMinute(start) {
    const time = start.split(':');
    return parseInt(time[0]) * 60 + parseInt(time[1]);
};


function solution(plans) {
    var answer = [];
    let arr = [];

    for (let [name, start, time] of plans) {
        let ass = new Assignment(name, start, time); 
        arr.push(ass);
    }
    arr.sort((o1, o2)=> {
        return o1.start - o2.start;
    })
    let stack = [];
    let curTime = -1;

    for (let i=0; i < arr.length; i++) {
        if (stack.length == 0) {
            stack.push(arr[i]);
            continue;
        }

        let curAss = stack[stack.length - 1];
        let newAss = arr[i];

        curTime = curAss.start;

        if (curTime + curAss.time <= newAss.start) {
            recursivePop(stack, newAss, curTime, answer);
        } else {
            curAss.time -= newAss.start - curTime;
        }
        stack.push(newAss);
    }

    while (stack.length > 0) {
        const item = stack.pop();
        answer.push(item.name);
    }
    return answer;
};

function recursivePop(stack, newAss, curTime, answer) {
    if (stack.length == 0) {
        return;
    }
    let curAss = stack[stack.length - 1];
    if (curTime + curAss.time <= newAss.start) {
        answer.push(stack.pop().name);
        recursivePop(stack, newAss, curTime + curAss.time, answer);
    } else {
        curAss.time -= newAss.start - curTime;
    }
};
