<?php
header("Content-type:application/json");
date_default_timezone_set('Asia/Jakarta');
$format_tanggal = date('Y-m-d');
require("connect.php");
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id_nasabah = $_POST["id_nasabah"];
    $CekDataAntrian = mysqli_query($conn,"SELECT MAX(no_antrian) as no_antrian, status_antrian FROM tbl_antrian WHERE NOT status_antrian IN ('Selesai','Tidak Hadir') AND id_nasabah='$id_nasabah' AND tanggal='$format_tanggal'");
    if($CekDataAntrian->num_rows>0){
        $HasilCekAntrian = mysqli_fetch_array($CekDataAntrian);
        $no_antrian = $HasilCekAntrian['no_antrian'];
        $status_antrian = $HasilCekAntrian['status_antrian'];
        echo json_encode(array('no_antrian'=>$no_antrian,'status_antrian'=>$status_antrian),JSON_FORCE_OBJECT);
    }else{
        $message = "Something Wrong With This Action";
        echo json_encode(array('message'=>$message),JSON_FORCE_OBJECT);
    }
}else{
    
}
?>
