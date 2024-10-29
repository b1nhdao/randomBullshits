package com.example.myapplication;

public class Lop {
    private String Malop;
    private String TenLop;
    private int SiSo;

    public Lop(String malop, String tenLop, int siSo) {
        Malop = malop;
        TenLop = tenLop;
        SiSo = siSo;
    }

    @Override
    public String toString() {
        return "Lop{" +
                "Malop='" + Malop + '\'' +
                ", TenLop='" + TenLop + '\'' +
                ", SiSo=" + SiSo +
                '}';
    }

    public String getMalop() {
        return Malop;
    }

    public void setMalop(String malop) {
        Malop = malop;
    }

    public String getTenLop() {
        return TenLop;
    }

    public void setTenLop(String tenLop) {
        TenLop = tenLop;
    }

    public int getSiSo() {
        return SiSo;
    }

    public void setSiSo(int siSo) {
        SiSo = siSo;
    }
}
