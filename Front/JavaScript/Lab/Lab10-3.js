const measureBMI = (height, weight) => {
    return weight / ((height/100)**2);
}

const obesityOrNot = (bmi) => {
    if(bmi > 26) console.log("비만 입니다.");
    else if(bmi >= 24 && bmi < 26) console.log("과체중 입니다.");
    else if(bmi >= 18.5 && bmi < 24) console.log("정상 입니다.");
    else console.log("저체중 입니다.");
}

let bmi = measureBMI(178, 85);
console.log(bmi);
obesityOrNot(bmi);
