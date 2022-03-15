<?php
$response = array();
date_default_timezone_set('Asia/Jakarta');
$waktu = date("H:i:s");
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id_user = $_POST["id_user"];
    $password = $_POST["password"];
    
    require("connect.php");

    $query = "UPDATE tbl_user set password = '$password' WHERE id_user='$id_user'";
    

    if ( mysqli_query($conn, $query)){
        $response['success'] = true;
        $response['message'] = "Password Berhasil Diproses";
    } else { 
        $response['success'] = false;
        $response['message'] = "Password Gagal Diproses";
    }
} else {
        $response['success'] = false;
        $response['message'] = "Error!";
}

echo json_encode($response);
