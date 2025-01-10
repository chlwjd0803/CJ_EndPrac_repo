function getArrayMaxNumber(arr){
    let max = -1;
    for(let i=0; i<arr.length; i++)
        if(max < arr[i])
            max = arr[i];
    return max;
}

const max = getArrayMaxNumber([10, 50, 30]);
console.log(max);