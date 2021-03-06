<!DOCTYPE html>
<html lang="ru">
<!--there is no content in Russian yet, but later some may be added-->

<head>
	<meta charset = "UTF-8">
	<link rel="shortcut icon" type="image/x-icon" href="https://static1.squarespace.com/static/55473fe6e4b079a47a7498d1/t/5b0821942b6a284718c96755/favicon.ico">
	<title>OpenWay:admin</title>
	<script src="functions.js"></script>
	<script src="admin-page.js"></script>
</head>

<body>
	Applications sent via an application form: <br>
	<table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
		<tr>
			<td><b>name</td>
			<td><b>last name</td>
			<td><b>surname</td>
			<td><b>email</td>
			<td><b>how applicant got acknowledged about school</td>
			<td><b>about applicant</td>
			<td><b>comments</td>
		</tr>
		<?php
			$ini = parse_ini_file('../config_db.ini');
			$mysqli = new mysqli($ini['db_host'], $ini['db_user'], $ini['db_pwd'], $ini['db_name']);
			$query = "SELECT *
				FROM applications
				ORDER BY appl_surn, appl_name, appl_name2";
			
			$result = $mysqli->query($query);
			$columns = ["appl_name", "appl_name2", "appl_surn", "appl_email", "appl_how", "appl_about", "appl_comment"];
			 
			while ($row = $result->fetch_assoc()) {
				echo "<tr>";
				foreach ($columns as $col)
					echo "<td>$row[$col] </td>";
				echo "</tr>";
			}
			$result->free();
			$mysqli->close();
		?>
	</table>
</body>