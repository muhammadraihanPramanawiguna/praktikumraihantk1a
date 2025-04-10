package Praktimum10042025;

import java.util.Scanner;

public class TestTabunganNasabah {

    public static void main(String[] args) {
        final int MAKS_NASABAH = 100;
        Nasabah[] nasabahList = new Nasabah[MAKS_NASABAH];
        int jumlahNasabah = 0;

        Scanner input = new Scanner(System.in);
        int pilihan;

        do {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tambah nasabah");
            System.out.println("2. Lihat semua nasabah");
            System.out.println("3. Menabung");
            System.out.println("4. Ambil uang");
            System.out.println("5. Transfer");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = input.nextInt();
            input.nextLine(); 

            switch (pilihan) {
                case 1:
                    if (jumlahNasabah < MAKS_NASABAH) {
                        System.out.print("Masukkan nama depan nasabah: ");
                        String namaDepan = input.nextLine();
                        System.out.print("Masukkan nama belakang nasabah: ");
                        String namaBelakang = input.nextLine();

                        System.out.print("Apakah nasabah memiliki saldo awal? (y/n): ");
                        String adaSaldo = input.nextLine();
                        int saldo;
                        if (adaSaldo.equalsIgnoreCase("y")) {
                            System.out.print("Masukkan saldo awal: ");
                            saldo = input.nextInt();
                            input.nextLine(); 
                        } else {
                            saldo = 0;
                        }

                        Nasabah nasabahBaru = new Nasabah(namaDepan, namaBelakang);
                        Tabungan tabunganBaru = new Tabungan(saldo);
                        nasabahBaru.setTabungan(tabunganBaru);

                        nasabahList[jumlahNasabah] = nasabahBaru;
                        jumlahNasabah++;

                        System.out.println("Nasabah berhasil ditambahkan.");
                    } else {
                        System.out.println("Jumlah nasabah sudah mencapai batas maksimal (100).");
                    }
                    break;

                case 2:
                    if (jumlahNasabah == 0) {
                        System.out.println("Belum ada nasabah.");
                    } else {
                        System.out.println("\n--- Data Nasabah ---");
                        for (int i = 0; i < jumlahNasabah; i++) {
                            System.out.println((i + 1) + ". " + nasabahList[i]);
                        }
                    }
                    break;

                case 3:
                    if (jumlahNasabah == 0) {
                        System.out.println("Belum ada nasabah.");
                        break;
                    }
                    System.out.print("Masukkan nomor nasabah yang ingin menabung: ");
                    int indexTabung = input.nextInt() - 1;
                    if (indexTabung >= 0 && indexTabung < jumlahNasabah) {
                        System.out.print("Masukkan jumlah uang yang ingin ditabung: ");
                        int jml = input.nextInt();
                        nasabahList[indexTabung].getTabungan().simpanUang(jml);
                        System.out.println("Menabung berhasil.");
                    } else {
                        System.out.println("Nomor nasabah tidak valid.");
                    }
                    break;

                case 4:
                    if (jumlahNasabah == 0) {
                        System.out.println("Belum ada nasabah.");
                        break;
                    }
                    System.out.print("Masukkan nomor nasabah yang ingin ambil uang: ");
                    int indexAmbil = input.nextInt() - 1;
                    if (indexAmbil >= 0 && indexAmbil < jumlahNasabah) {
                        System.out.print("Masukkan jumlah uang yang ingin diambil: ");
                        int jml = input.nextInt();
                        boolean berhasil = nasabahList[indexAmbil].getTabungan().ambilUang(jml);
                        System.out.println("Pengambilan uang " + (berhasil ? "berhasil." : "gagal. Saldo tidak cukup."));
                    } else {
                        System.out.println("Nomor nasabah tidak valid.");
                    }
                    break;

                case 5:
                    if (jumlahNasabah < 2) {
                        System.out.println("Minimal harus ada 2 nasabah untuk transfer.");
                        break;
                    }
                    System.out.print("Masukkan nomor nasabah pengirim: ");
                    int dari = input.nextInt() - 1;
                    System.out.print("Masukkan nomor nasabah penerima: ");
                    int ke = input.nextInt() - 1;
                    System.out.print("Masukkan jumlah yang ingin ditransfer: ");
                    int jmlTransfer = input.nextInt();

                    if (dari >= 0 && dari < jumlahNasabah && ke >= 0 && ke < jumlahNasabah && dari != ke) {
                        boolean sukses = nasabahList[dari].getTabungan().transfer(nasabahList[ke].getTabungan(), jmlTransfer);
                        System.out.println("Transfer " + (sukses ? "berhasil." : "gagal. Saldo tidak cukup."));
                    } else {
                        System.out.println("Transfer gagal. Periksa nomor nasabah.");
                    }
                    break;

                case 6:
                    System.out.println("Terima kasih. Program selesai.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
            }

        } while (pilihan != 6);

        input.close();
    }
}