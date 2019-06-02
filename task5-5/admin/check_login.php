<?php
//requires php 5.x
$db_host = 'localhost';
$db_user = 'root';
$db_pwd = '';
$database = 'openway';

$mysqli = new mysqli($db_host, $db_user, $db_pwd, $database);
$pass=$_COOKIE["pass"];
$login=$_COOKIE["login"];
$table='users';
$usr_res = $mysqli->query("SELECT * FROM {$table} WHERE login='$login' AND pass='$pass'");
if (!$usr_res) {
    die("-1");
}
$rows_num =  $usr_res->num_rows;
if ($rows_num == 0) {
	die("-1");
}

echo json_encode(0); //success
?>