
package Praktimum10042025;

public class Bank {
    private Nasabah[] nasabah;
    private int jumlahNasabah;
    
    public Bank() {
        nasabah = new Nasabah[100]; //maksimum 100 nasabah
        jumlahNasabah = 0;
    }
    public void tambahNasabah(String namaAwal, String namaAkhir) {
        nasabah[jumlahNasabah++] = new Nasabah(namaAwal, namaAkhir);
    }
    public void tambahNasabah(String namaAwal, String namaAkhir, Tabungan tabungan){
        Nasabah n = new Nasabah(namaAwal, namaAkhir);
        n.setTabungan(tabungan);
        nasabah[jumlahNasabah++] = n;
    }
    public int getJumlahNasabah(){
        return jumlahNasabah;
    }
    public Nasabah getNasabah(int indeks){
        return (indeks >= 0 && indeks < jumlahNasabah) ? nasabah[indeks] : null;
    }
    public int searchNasabah(String namaAwal, String namaAkhir){
        for (int i = 0; i < jumlahNasabah; i++){
            if (nasabah[i].toString().contains(namaAwal + " " + namaAkhir)){
                return i;
            }
        }
        return -1;
    }
    public int[] searchNasabah(String namaAwal){
        int[] hasil = new int [jumlahNasabah];
        int count = 0;
        for (int i = 0; i < jumlahNasabah; i++){
        if (nasabah[i].toString().contains(namaAwal)){
            hasil[count++] = i;
        }
    }
        return java.util.Arrays.copyOf(hasil, count);
    }
}
