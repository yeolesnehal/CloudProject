function LoginWithCredentials(){
	
var emailid = document.getElementById("emailId").value;
var password = document.getElementById("passwd").value;
if !( emailid == "snehal.yeole92@gmail.com" && password == "Shreeniwas"){
	alert("Successfully created the account !!!");
	window.location = "index.html";
	return false;
}

else{
	
alert("User with this Email Id already exists !!!");
return false;
}
}	