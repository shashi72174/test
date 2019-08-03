document.write("done with script tag included")
//alert("hey, It works!!!!")
//console.log("awesome bro!!!!")

var var1 = "hello"
var s = `abc`;
/* function hi() {
    alert("b/w this is called")
    var var1 = "hi";
    return var1;
} */

//console.log(typeof(hi))
//console.log(typeof(var1))
//console.log(typeof(var1))
//alert(`var1 ${var1}`);
//alert(`s ${s}`);


var arr = ['hi','hello']
console.log(typeof(arr))

var newArr = arr.map(function(item) {
    return item+" world";
}).filter(function(item1) {
    if(item1 === "hello world")
        console.log("yes man");
})

//console.log(newArr);