<?php
	session_start();
	require("function.php");
	if(isset($_SESSION['username'])){
		$usersData = getUsersData(getId($_SESSION['username']));		
		echo $usersData['lastname']." ".$usersData['firstname']." you last login date is ".$usersData['last_login_date']." and you had login ".$usersData['login_times']." times"; ?>
		
	}
?>
