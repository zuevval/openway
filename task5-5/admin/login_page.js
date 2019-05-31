window.addEventListener("load", main, false);
function main () {
	check=login_check();
      if (check!=-1) {
            window.alert("You have already logged in");
            window.location.replace("admin.html");
      }
	butt1.onclick = function(){
		var login=document.getElementById('login').value,
		pass=document.getElementById('password').value;
		document.cookie = "pass="+pass;
		document.cookie = "login="+login;
		console.log(login);
		check=login_check();
		if (check!=-1)
			window.location.replace("admin.php");
		else
			window.location.reload();
	}
}