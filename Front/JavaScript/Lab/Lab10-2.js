const findMax = (arr) => {
    let max = -1;
    for(let i=0; i<arr.length; i++)
        if(max < arr[i])
            max = arr[i];
    return max;
}

console.log(findMax([1, 2, 3]));