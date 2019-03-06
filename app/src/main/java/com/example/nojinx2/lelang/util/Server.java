package com.example.nojinx2.lelang.util;

public class Server {
    public static final String URL = "http://192.168.43.63/api/";
    public static final String URL_Foto = URL+"data/image/";
    public static final int TIMEOUT_ACCESS = 5000;
    public static String get_baranglelang = URL+"master/get_baranglelang.php";
    public static String get_penawaran = URL+"master/get_penawaran.php";
    public static String get_paket_byid = URL+"master/get_paket_byid.php";
    public static String input_pesanan=URL+"master/input_pesanan.php";
    public static String get_pesanan_customer = URL+"master/get_pesanan_customer.php";
    public static String get_pesanan_byseller = URL+"master/select_pesanan_bytoko.php";
    public static String get_kurir = URL+"master/select_kurir.php";
    public static String konfirmasi_pesanan = URL+"master/konfirmasi_pesanan.php";
    public static String get_pesanan_kurir = URL+"master/get_pesanan_kurir.php";
    public static String konfirmasi_pengiriman=URL+"master/konfirmasi_pengiriman.php";
    public static String konfirmasi_penerimaan=URL+"master/konfirmasi_customer.php";
}
