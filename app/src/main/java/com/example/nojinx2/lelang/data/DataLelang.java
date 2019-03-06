package com.example.nojinx2.lelang.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataLelang implements Serializable{
    /**
     * id_order : 11
     * id_customer : 1
     * id_kurir : 0
     * id_seller : 2
     * status_penjualan : 0
     * status_pengiriman : 0
     * konfirmasi_kurir : 0
     * konfirmasi_customer : 0
     * detail : {"id_detail":"6","id_order":"11","id_makanan":"5","kuantitas":"1","keterangan":"disini","alamat":"disana","ongkir":"5000","total_harga":"8000.0","makanan":{"id_makanan":"5","id_seller":"2","nama_makanan":"gorengan","harga":"3000","foto":"test.jpg","deskripsi":" Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend metus lectus, nec lobortis sapien vestibulum sit amet. Nulla eget nulla tortor. Duis eget semper odio. Phasellus efficitur tempor ullamcorper. Etiam in diam lorem. Sed tortor est, elementum nec efficitur tincidunt, volutpat eu massa. Etiam commodo justo venenatis lorem posuere, quis venenatis velit consectetur. Curabitur scelerisque, odio nec egestas tempus, mi ante venenatis nulla, ac pellentesque risus augue ac nibh. Proin eu velit magna. "}}
     */

    @SerializedName("id_lelang")
    private String idLelang;
    @SerializedName("id_pembeli")
    private String idPembeli;
    @SerializedName("id_penjual")
    private String idPenjual;
    @SerializedName("status")
    private String status;
    @SerializedName("konfirmasi")
    private String konfirmasi;
    @SerializedName("detail")
    private DetailBean detail;

    public String getIdLelang() {
        return idLelang;
    }

    public void setIdLelang(String idLelang) {
        this.idLelang = idLelang;
    }

    public String getIdPembeli() {
        return idPembeli;
    }

    public void setIdPembeli(String idPembeli) {
        this.idPembeli = idPembeli;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKonfirmasi() {
        return konfirmasi;
    }

    public void setKonfirmasi(String konfirmasi) {
        this.konfirmasi = konfirmasi;
    }

    public DetailBean getDetail() {
        return detail;
    }

    public void setDetail(DetailBean detail) {
        this.detail = detail;
    }

    public static class DetailBean implements Serializable{
        /**
         * id_detail : 6
         * id_order : 11
         * id_makanan : 5
         * kuantitas : 1
         * keterangan : disini
         * alamat : disana
         * ongkir : 5000
         * total_harga : 8000.0
         * makanan : {"id_makanan":"5","id_seller":"2","nama_makanan":"gorengan","harga":"3000","foto":"test.jpg","deskripsi":" Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend metus lectus, nec lobortis sapien vestibulum sit amet. Nulla eget nulla tortor. Duis eget semper odio. Phasellus efficitur tempor ullamcorper. Etiam in diam lorem. Sed tortor est, elementum nec efficitur tincidunt, volutpat eu massa. Etiam commodo justo venenatis lorem posuere, quis venenatis velit consectetur. Curabitur scelerisque, odio nec egestas tempus, mi ante venenatis nulla, ac pellentesque risus augue ac nibh. Proin eu velit magna. "}
         */

        @SerializedName("id_detail")
        private String idDetail;
        @SerializedName("id_lelang")
        private String idLelang;
        @SerializedName("id_barang")
        private String idBarang;
        @SerializedName("harga_awal")
        private String harga_awal;
        @SerializedName("bobot")
        private String bobot;
        @SerializedName("nama_penjual")
        private String nama_penjual;
        @SerializedName("tabel_barang")
        private TabelBarangBean tabel_barang;

        public String getIdDetail() {
            return idDetail;
        }

        public void setIdDetail(String idDetail) {
            this.idDetail = idDetail;
        }

        public String getIdLelang() {
            return idLelang;
        }

        public void setIdLelang(String idLelang) {
            this.idLelang = idLelang;
        }

        public String getIdBarang() {
            return idBarang;
        }

        public void setIdBarang(String idBarang) {
            this.idBarang = idBarang;
        }

        public String getHarga_awal() {
            return harga_awal;
        }

        public void setHarga_awal(String harga_awal) {
            this.harga_awal = harga_awal;
        }

        public String getBobot() {
            return bobot;
        }

        public void setBobot(String bobot) {
            this.bobot = bobot;
        }

        public String getNama_penjual() {
            return nama_penjual;
        }

        public void setNama_penjual(String nama_penjual) {
            this.nama_penjual = nama_penjual;
        }


        public TabelBarangBean getTabel_barang() {
            return tabel_barang;
        }

        public void setTabel_barang(TabelBarangBean tabel_barang) {
            this.tabel_barang = tabel_barang;
        }

        public static class TabelBarangBean implements Serializable{
            /**
             * id_makanan : 5
             * id_seller : 2
             * nama_makanan : gorengan
             * harga : 3000
             * foto : test.jpg
             * deskripsi :  Lorem ipsum dolor sit amet, consectetur adipiscing elit. In eleifend metus lectus, nec lobortis sapien vestibulum sit amet. Nulla eget nulla tortor. Duis eget semper odio. Phasellus efficitur tempor ullamcorper. Etiam in diam lorem. Sed tortor est, elementum nec efficitur tincidunt, volutpat eu massa. Etiam commodo justo venenatis lorem posuere, quis venenatis velit consectetur. Curabitur scelerisque, odio nec egestas tempus, mi ante venenatis nulla, ac pellentesque risus augue ac nibh. Proin eu velit magna.
             */

            @SerializedName("id_barang")
            private String id_barang;
            @SerializedName("id_penjual")
            private String id_penjual;
            @SerializedName("nama_ikan")
            private String nama_ikan;
            @SerializedName("berat_ikan")
            private String berat_ikan;
            @SerializedName("harga_awal")
            private String harga_awal;
            @SerializedName("foto_ikan")
            private String foto_ikan;
            @SerializedName("deskripsi")
            private String deskripsi;

            public String getId_barang() {
                return id_barang;
            }

            public void setId_barang(String id_barang) {
                this.id_barang = id_barang;
            }

            public String getId_penjual() {
                return id_penjual;
            }

            public void setId_penjual(String id_penjual) {
                this.id_penjual = id_penjual;
            }

            public String getNama_ikan() {
                return nama_ikan;
            }

            public void setNama_ikan(String nama_ikan) {
                this.nama_ikan = nama_ikan;
            }

            public String getBerat_ikan() {
                return berat_ikan;
            }

            public void setBerat_ikan(String berat_ikan) {
                this.berat_ikan = berat_ikan;
            }

            public String getHarga_awal() {
                return harga_awal;
            }

            public void setHarga_awal(String harga_awal) {
                this.harga_awal = harga_awal;
            }

            public String getFoto_ikan() {
                return foto_ikan;
            }

            public void setFoto(String foto_ikan) {
                this.foto_ikan = foto_ikan;
            }

            public String getDeskripsi() {
                return deskripsi;
            }

            public void setDeskripsi(String deskripsi) {
                this.deskripsi = deskripsi;
            }
        }
    }
}