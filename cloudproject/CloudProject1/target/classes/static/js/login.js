
var attempt = 3; // Variable to count number of attempts.
// Below function Executes on click of login button.
function validate(){
	
var emailid = document.getElementById("emailid").value;
var password = document.getElementById("psw").value;
if ( emailid == "demo.user@gmail.com" && password == "1234ryt"){
alert ("Login successfully");
window.location = "welcome.html"; // Redirecting to other page.
return false;
}
else{
attempt --;// Decrementing by one.
alert("You have left "+attempt+" attempt;");
// Disabling fields after 3 attempts.
if( attempt == 0){
document.getElementById("emailid").disabled = true;
document.getElementById("psw").disabled = true;
document.getElementById("submit").disabled = true;
alert("Invalid Credentials, Please try again!!!");
return false;
}
}
}

