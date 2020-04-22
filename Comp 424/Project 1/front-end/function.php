<?php

require("connect.php");

function getUsersData($id){
	$array = array();
	$q = mysql_query("SELECT * FROM 't_user' WHERE 'id' =".$id);
	while($r = mysql_fetch_assoc($q)){
		$array['id'] = $r['id'];
		$array['firstname'] = $r['firstname'];
		$array['lastname'] = $r['lastname'];
		$array['last_login_date'] = $r['last_login_date'];
		$array['login_times'] = $r['login_times'];
	}
	return $array;
}

function getId($username){
	$q = mysql_query("SELECT 'id' FROM 't_user' WHERE 'username' ='".$username."'");
	while($r = mysql_fetch_assoc($q)){
		return $r['id'];
	}
}
?>