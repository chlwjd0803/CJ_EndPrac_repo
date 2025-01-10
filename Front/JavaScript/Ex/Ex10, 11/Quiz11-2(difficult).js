const arr = [10, 120, 30, 50, 20];

let max = -1;

arr.forEach(function(v){ //생소한 형태이므로 헷갈리면 보기
    if(max<v) max = v;
});

console.log(max);