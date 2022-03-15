<?php
header("Content-type:application/json");
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $username = $_POST["username"];
    $password = $_POST["password"];
}
require("connect.php");

if($conn)
{
    $query = "SELECT id_user, nama, level_akses FROM tbl_user WHERE username='$username' and password='$password'";
    $result = mysqli_query($conn,$query);
    if(mysqli_num_rows($result)>0)
    {
        $row = mysqli_fetch_assoc($result);
        $status = "OK";
        $result_code = 1;
        $level_akses = $row['level_akses'];
        $nama = $row['nama'];
        $id_user = $row['id_user'];
        echo json_encode(array('status'=>$status,'result_code'=> $result_code,'level_akses'=>$level_akses,'nama'=>$nama,'id_user'=>$id_user));

    }
    else{
        $status = "OK";
        $result_code = 0;
        echo json_encode(array('status'=>$status,'result_code'=> $result_code));
    }
}
else
{
$status ="FAILED";
echo json_encode(array('status'=>$status),JSON_FORCE_OBJECT);
}

?>