<?php

$name = $_REQUEST['name'];
$number = $_REQUEST['number'];

if(isset($_REQUEST['message'])){
    $msg = $_REQUEST['message'];
}else{
    $msg = "It's a phone call!";
}
