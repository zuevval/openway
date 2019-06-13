<!DOCTYPE html>
<html lang="ru">
<!--there is no content in Russian yet, but later some may be added-->

<head>
	<meta charset = "UTF-8">
	<title>OpenWay summer school</title>
	<link rel="shortcut icon" type="image/x-icon" href="https://static1.squarespace.com/static/55473fe6e4b079a47a7498d1/t/5b0821942b6a284718c96755/favicon.ico">
</head>

<body>
	This is an application form for the <a href="https://www.openwaygroup.com/openway-summer-school-spb"> OpenWay summer school 2019</a><br><br>
	<form>
		<table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
			<tr>
				<td>Your name* </td>
				<td><input type="text" name="name" placeholder = "Jane" onkeyup='saveValue(this);'></td>
			</tr>
			<tr>
				<td>Your last name </td>
				<td><input type="text" name="name2" placeholder = "J."></td>
			</tr>
			<tr>
				<td>Your surname* </td>
				<td><input type="text" name="surn" placeholder = "Doe"></td>
			</tr>
			<tr>
				<td>email </td>
				<td><input type="text" name="email" placeholder = "jane.doe@example.com"></td>
			</tr>
			<tr>
				<td>How did you find an information <br> about the OpenWay school? </td>
				<td><textarea name="howFound" rows="2"></textarea></td>
			</tr>
			<tr>
				<td>Tell us a few words about yourself</td>
				<td><textarea name="aboutSelf" rows="2"></textarea></td>
			</tr>
			<tr>
				<td>Leave here your comments if you want</td>
				<td><textarea name="comments" rows="2"></textarea></td>
			</tr>
		</table>
		<br>
		<div>
		<input type="submit" value="Submit" style="margin:auto;
  display:block;">
		</div>
		<br>
		All the fields marked with a star (*) are obligatory<br>
	</form>
	<?php
		$formFilled = false;
		$obligatoryFilled = true;
		$ObligatoryFields = array('name', 'surn');
		$auxiliaryFields = array('name2', 'aboutSelf', 'comments', 'email');
		foreach($ObligatoryFields as $field){
			$formFilled |= isset($_GET[$field]);
			$obligatoryFilled &= isset($_GET[$field]);
			if(isset($_GET[$field])) $obligatoryFilled &= (strlen($_GET[$field]) > 0);
		}
		foreach($auxiliaryFields as $field) $formFilled |= isset($_GET[$field]);
		if(!$formFilled) die;
		if(!$obligatoryFilled){
			echo ("<script type='text/javascript'>
				window.addEventListener('load', main, false);
				function main () {
					window.alert('Please fill in all fields with (*)');
				}
			</script>");
			die();
			
		}
		
		$q_name=$_GET['name'];
		$q_name2=$_GET['name2'];
		$q_surn=$_GET['surn'];
		$q_email=$_GET['email'];
		$q_how=$_GET['howFound'];
		$q_about=$_GET['aboutSelf'];
		$q_comments=$_GET['comments'];
		$query = "INSERT INTO openway.applications 
					(appl_name, appl_name2, appl_surn, appl_email, appl_how, appl_about, appl_comment)
					VALUES ('$q_name', '$q_name2', '$q_surn', '$q_email', '$q_how', '$q_about', '$q_comments');";
				
		$ini = parse_ini_file('config_db.ini');
		$mysqli = new mysqli($ini['db_host'], $ini['db_user'], $ini['db_pwd'], $ini['db_name']);
		$result=$mysqli->query($query);
		$mysqli->close();
		if($result){
			echo ("<script type='text/javascript'>
				window.addEventListener('load', main, false);
				function main () {
					window.location.replace('success.htm');
				}
			</script>");
			exit();
		} else{
			echo ("<script type='text/javascript'>
				window.addEventListener('load', main, false);
				function main () {
					window.alert('An error occured while uploading your answer');
				}
			</script>");
		}
	?>
</body>