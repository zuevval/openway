window.addEventListener("load", main, false);
function main () {
	check=login_check();
	if (check==-1)
		window.location.replace("login.html");
}