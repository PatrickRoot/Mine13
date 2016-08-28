
function test(){
    return 1;
}

function test2(){
    test.call(this);
}

console.log(test2());
