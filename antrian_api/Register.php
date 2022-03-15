<?php
header("Content-type:application/json");
$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $no_rekening = $_POST["no_rekening"];
    $nama_lengkap = $_POST["nama_lengkap"];
    $no_handphone = $_POST["no_handphone"];
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    require("connect.php");
    $id_nasabah = "USR-" . $no_rekening;
    $cekUsername = mysqli_query($conn,"SELECT username FROM tbl_user WHERE username = '$username'");
    if($cekUsername->num_rows>0){
        $response['success'] = true;
        $response['message'] = "Username Ini Sudah Digunakan";
    } else{
        $simpan_nasabah = "INSERT INTO tbl_nasabah (id_nasabah, no_rekening, nama_lengkap, no_handphone) VALUES ('$id_nasabah','$no_rekening', '$nama_lengkap', '$no_handphone')";
        $simpan_user = "INSERT INTO tbl_user (id_user, nama, username, password, level_akses) VALUES ('$id_nasabah', '$nama_lengkap', '$username', '$password', 'User')";
        mysqli_query($conn, $simpan_user);
        if (mysqli_query($conn, $simpan_nasabah)){
            $response['success'] = true;
            $response['message'] = "Akun Berhasil Dibuat";
        } else { 
            $response['success'] = false;
            $response['message'] = "Akun Gagal Dibuat";
        }
    }
} else {
    $response['success'] = false;
    $response['message'] = "Error!";
}

echo json_encode($response);
?>