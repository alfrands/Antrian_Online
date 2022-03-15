<?php
$response = array();
date_default_timezone_set('Asia/Jakarta');
$waktu = date("H:i:s");
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id = $_POST["id"];
    $id_user = $_POST["id_user"];
    
    require("connect.php");

    $query = "UPDATE tbl_antrian set waktu = '$waktu', status_antrian='Dipanggil',id_user='$id_user' WHERE id='$id'";
    

    if ( mysqli_query($conn, $query)){
        $response['success'] = true;
        $response['message'] = "Antrian Berhasil Dipanggil";
    } else { 
        $response['success'] = false;
        $response['message'] = "Antrian Gagal Dipanggil";
    }
} else {
        $response['success'] = false;
        $response['message'] = "Error!";
}

echo json_encode($response);
