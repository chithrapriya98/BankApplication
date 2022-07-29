/**
 * 
 */
function validate(){
    var acc=document.getElementById("targetAccount").value;
    var type=document.getElementById("accounttype").value;
    var amt=document.getElementById("amount").value;
	console.log(acc,type,amt);
    if(isNaN(acc)){
        document.getElementById("zero").innerHTML="Please Enter Correct Account Number";
        console.log("string");
        return false;
    }
    if(amt==0){
        document.getElementById("zero").innerHTML="Please Enter Amount to Transfer";
        console.log("zero");
        return false;
    }else if(amt >25000 && type==="Savings Account"){
        document.getElementById("zero").innerHTML="You can only transfer amount less than 25000";
        console.log("Savings");
        return false;
    }else if(amt >50000 && type==="Current Account"){
        document.getElementById("zero").innerHTML="You can only transfer amount less than 50000";
        console.log("current");
        return false;
    }
    console.log("true");
    return true;
}