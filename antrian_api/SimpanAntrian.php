<?php
header("Content-type:application/json");
$response = array();
date_default_timezone_set('Asia/Jakarta');
$format_tanggal = date('Y-m-d');
$huruf = "AK-";
require("connect.php");


if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $no_rekening = $_POST["no_rekening"];
    $nama_lengkap = $_POST["nama_lengkap"];
    $no_handphone = $_POST["no_handphone"];
    $id_nasabah = $_POST["id_nasabah"];

    $CekTanggal = mysqli_query($conn,"SELECT tanggal FROM tbl_antrian ORDER BY id DESC LIMIT 1");
    if($CekTanggal->num_rows>0){
        $dataTgl = mysqli_fetch_array($CekTanggal);
        $hasilTanggal = $dataTgl['tanggal'];
        if($hasilTanggal == $format_tanggal){
            $CekNoAntrian = mysqli_query($conn,"SELECT MAX(no_antrian) as no_antri FROM tbl_antrian WHERE tanggal = '$format_tanggal'");
            if($CekNoAntrian->num_rows>0){
                $dataNoAntri = mysqli_fetch_array($CekNoAntrian);
                $HasilCek = $dataNoAntri['no_antri'];
                $urutan = (int) substr($HasilCek, 4, 3);
                $urutan++;
                $no_antrian = $huruf.sprintf("%03s", $urutan); 
            }
        }else{
            $no_antrian = $huruf . "001";
        }
    }else{
        $no_antrian = $huruf . "001";
    }
        $simpan_antrian = "INSERT INTO tbl_antrian (id_nasabah,no_antrian, no_rekening, nama_lengkap, no_handphone,status_antrian) VALUES ('$id_nasabah', '$no_antrian', '$no_rekening', '$nama_lengkap', '$no_handphone', 'Menunggu Panggilan')";
        if (mysqli_query($conn, $simpan_antrian)){
            $response['success'] = true;
            $response['message'] = "Nomor Antrian Berhasil Dibuat";
        } else { 
            $response['success'] = false;
            $response['message'] = "Nomor Antrian Gagal Dibuat";
        }
    }else {
    $response['success'] = false;
    $response['message'] = "Error!";
}

echo json_encode($response);
?>