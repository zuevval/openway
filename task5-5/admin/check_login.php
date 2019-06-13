<?php
//requires php 5.x
$ini = parse_ini_file('../config_db.ini');
$mysqli = new mysqli($ini['db_host'], $ini['db_user'], $ini['db_pwd'], $ini['db_name']);
$pass=$_COOKIE["pass"];
$login=$_COOKIE["login"];
$table='users';
$usr_res = $mysqli->query("SELECT * FROM {$table} WHERE login='$login' AND pass='$pass'");
$mysqli->close();
if (!$usr_res) {
    die("-1");
}
$rows_num =  $usr_res->num_rows;
if ($rows_num == 0) {
	die("-1");
}

echo json_encode(0); //success
?>