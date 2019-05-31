function login_check(){
	var login=getCookie('login');
	var pass=getCookie('pass');
	console.log(login);
	var check=0;
	if(login==-1||pass==-1){
		check = -1;
	}
    else {
    	var oReq = new XMLHttpRequest();
	    oReq.onload = function() {
			//console.log(this.responseText);
			var resp=this.responseText;
			resp = resp.replace('0', '');
			console.log(resp);
			check = Number(resp);
	    };
	    oReq.open("get", "check_login.php", false);
	    oReq.send();
	    if(check==-1){
	    	document.cookie = "pass="+ '-1';
			document.cookie = "login="+ '-1';
	    }
	}
    return check;
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return -1;
}