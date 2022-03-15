<?php
header("Content-type:application/json");
if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id_nasabah = $_POST["id_nasabah"];
    require("connect.php");
    $CekDataNasabah = mysqli_query($conn,"SELECT * FROM tbl_nasabah WHERE id_nasabah='$id_nasabah'");
    if($CekDataNasabah->num_rows>0){
        $HasilCekNasabah = mysqli_fetch_array($CekDataNasabah);
        $no_rekening = $HasilCekNasabah['no_rekening'];
        $nama_lengkap = $HasilCekNasabah['nama_lengkap'];
        $no_handphone = $HasilCekNasabah['no_handphone'];
        echo json_encode(array('no_rekening'=>$no_rekening,'nama_lengkap'=>$nama_lengkap,'no_handphone'=>$no_handphone),JSON_FORCE_OBJECT);
    }else{
        $message = "Something Wrong With This Action";
        echo json_encode(array('message'=>$message),JSON_FORCE_OBJECT);
    }
}else{
        $message = "Something Wrong With This Action";
        echo json_encode(array('message'=>$message),JSON_FORCE_OBJECT);
    }

?>
