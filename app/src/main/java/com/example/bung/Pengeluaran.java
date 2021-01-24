package com.example.bung;

public class Pengeluaran {
    private String Catatan;
    private String TotalPengeluaran;
    private String TanggalPengeluaran;
    private String dataID;


    public Pengeluaran(){}

    public Pengeluaran(String dataID, String catatan, String totalPengeluaran, String tanggalPengeluaran) {
        this.dataID = dataID;
        this.Catatan = catatan;
        TotalPengeluaran = totalPengeluaran;
        TanggalPengeluaran = tanggalPengeluaran;
    }

    public String getDataID() {
        return dataID;
    }

    public void setDataID(String dataID) {
        this.dataID = dataID;
    }

    public String getCatatan() {
        return Catatan;
    }

    public void setCatatan(String catatan) {
        this.Catatan = catatan;
    }

    public String getTotalPengeluaran() {
        return TotalPengeluaran;
    }

    public void setTotalPengeluaran(String totalPengeluaran) {
        TotalPengeluaran = totalPengeluaran;
    }

    public String getTanggalPengeluaran() {
        return TanggalPengeluaran;
    }

    public void setTanggalPengeluaran(String tanggalPengeluaran) {
        TanggalPengeluaran = tanggalPengeluaran;
    }
}