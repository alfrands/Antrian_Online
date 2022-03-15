<?php
header("Content-type:application/json");
date_default_timezone_set('Asia/Jakarta');
$format_tanggal = date('Y-m-d');
require("connect.php");

    $CekDataAntrian = mysqli_query($conn,"SELECT MAX(no_antrian) as no_antrianserv, waktu FROM tbl_antrian WHERE status_antrian='Dilayani' AND tanggal='$format_tanggal'");
    if($CekDataAntrian->num_rows>0){
        $HasilCekAntrian = mysqli_fetch_array($CekDataAntrian);
        $no_antrian = $HasilCekAntrian['no_antrianserv'];
        $waktu = $HasilCekAntrian['waktu'];
        echo json_encode(array('no_antrianserv'=>$no_antrian, 'waktu'=>$waktu),JSON_FORCE_OBJECT);
    }else{
        $message = "Something Wrong With This Action";
        echo json_encode(array('message'=>$message),JSON_FORCE_OBJECT);
    }
?>
