for(var i=100; i<=999; i++){
    var t = Math.floor(i/100);
    var o = Math.floor(i%100 / 10); //이 방법밖엔....
    var p = i%10;

    if(t**3 + o**3 + p**3 == i)
        console.log("" + i);
}