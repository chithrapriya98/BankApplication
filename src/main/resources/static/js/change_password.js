/**
 * 
 */
 function change() {
	console.log("hello");
	var pass1 = document.getElementById("new_password").value;
	var pass2 = document.getElementById("re_password").value;
	var s = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{8,15}$/;
	if (pass1.match(s)) {
		if (!(pass1 === pass2)) {
			document.getElementById("error").innerHTML = "Both password didn't matched \n please enter same password in two fields";
			return false;
		}
	}
	else {
		document.getElementById("error").innerHTML = "password should have atleast onedigit,one uppercase,one special character";
		return false;
	}
	document.getElementById("changeForm").reset();
	return true;
}