<?php
header("Content-type:application/json");
date_default_timezone_set('Asia/Jakarta');
$format_tanggal = date('Y-m-d');
require("connect.php");

$query = mysqli_query($conn,"SELECT * FROM tbl_antrian WHERE status_antrian ='Tidak Hadir' AND tanggal='$format_tanggal'");
$response = array();

while($row = mysqli_fetch_assoc($query)){
    array_push($response,array(
        'no_antrian' => $row['no_antrian'],
        'nama_lengkap' => $row['nama_lengkap'],
        'no_handphone' => $row['no_handphone'],
        'status_antrian' => $row['status_antrian']
    ));
}
echo json_encode($response);

?>