
package Praktimum10042025;
import java.util.Scanner;
public class TestBankNasabahTabungan {
    private static Scanner scanner = new Scanner(System.in);
    private static Bank bank = new Bank();
 public static void main(String[] args) {
        System.out.println("=== SISTEM MANAJEMEN BANK ===");
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu Utama:");
            System.out.println("1. Kelola Nasabah");
            System.out.println("2. Kelola Tabungan");
            System.out.println("3. Cari Nasabah");
            System.out.println("4. Tampilkan Semua Nasabah");
            System.out.println("5. Keluar");
            System.out.print("Pilihan Anda: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    kelolaNasabah();
                    break;
                case 2:
                    kelolaTabungan();
                    break;
                case 3:
                    cariNasabah();
                    break;
                case 4:
                    tampilkanSemuaNasabah();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Terima kasih telah menggunakan sistem kami.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private static void kelolaNasabah() {
        System.out.println("\nMenu Kelola Nasabah:");
        System.out.println("1. Tambah Nasabah");
        System.out.println("2. Lihat Nasabah");
        System.out.println("3. Kembali ke Menu Utama");
        System.out.print("Pilihan Anda: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        switch (choice) {
            case 1:
                tambahNasabah();
                break;
            case 2:
                lihatNasabah();
                break;
            case 3:
                return;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void tambahNasabah() {
        System.out.print("\nMasukkan nama depan nasabah: ");
        String namaAwal = scanner.nextLine();
        
        System.out.print("Masukkan nama belakang nasabah: ");
        String namaAkhir = scanner.nextLine();
        
        System.out.print("Apakah nasabah memiliki tabungan? (y/n): ");
        String memilikiTabungan = scanner.nextLine();
        
        if (memilikiTabungan.equalsIgnoreCase("y")) {
            System.out.print("Masukkan saldo awal tabungan: ");
            int saldo = scanner.nextInt();
            scanner.nextLine(); 
            
            Tabungan tabungan = new Tabungan(saldo);
            bank.tambahNasabah(namaAwal, namaAkhir, tabungan);
            System.out.println("Nasabah dengan tabungan berhasil ditambahkan!");
        } else {
            bank.tambahNasabah(namaAwal, namaAkhir);
            System.out.println("Nasabah tanpa tabungan berhasil ditambahkan!");
        }
    }

    private static void lihatNasabah() {
        System.out.print("\nMasukkan indeks nasabah (0-" + (bank.getJumlahNasabah()-1) + "): ");
        int index = scanner.nextInt();
        scanner.nextLine();
        
        Nasabah nasabah = bank.getNasabah(index);
        if (nasabah != null) {
            System.out.println("\nDetail Nasabah:");
            System.out.println(nasabah.toString());
        } else {
            System.out.println("Nasabah dengan indeks tersebut tidak ditemukan!");
        }
    }

    private static void kelolaTabungan() {
        System.out.print("\nMasukkan indeks nasabah (0-" + (bank.getJumlahNasabah()-1) + "): ");
        int index = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        Nasabah nasabah = bank.getNasabah(index);
        if (nasabah == null || nasabah.getTabungan() == null) {
            System.out.println("Nasabah tidak ditemukan atau tidak memiliki tabungan!");
            return;
        }
        
        Tabungan tabungan = nasabah.getTabungan();
        
        System.out.println("\nMenu Kelola Tabungan:");
        System.out.println("1. Cek Saldo");
        System.out.println("2. Simpan Uang");
        System.out.println("3. Ambil Uang");
        System.out.println("4. Transfer ke Nasabah Lain");
        System.out.println("5. Kembali ke Menu Utama");
        System.out.print("Pilihan Anda: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        switch (choice) {
            case 1:
                System.out.println("Saldo saat ini: " + tabungan.getSaldo());
                break;
            case 2:
                System.out.print("Masukkan jumlah uang yang akan disimpan: ");
                int jumlahSimpan = scanner.nextInt();
                scanner.nextLine(); 
                tabungan.simpanUang(jumlahSimpan);
                System.out.println("Uang berhasil disimpan. Saldo baru: " + tabungan.getSaldo());
                break;
            case 3:
                System.out.print("Masukkan jumlah uang yang akan diambil: ");
                int jumlahAmbil = scanner.nextInt();
                scanner.nextLine(); 
                boolean berhasil = tabungan.ambilUang(jumlahAmbil);
                if (berhasil) {
                    System.out.println("Uang berhasil diambil. Saldo baru: " + tabungan.getSaldo());
                } else {
                    System.out.println("Gagal mengambil uang. Saldo tidak mencukupi!");
                }
                break;
            case 4:
                System.out.print("Masukkan indeks nasabah tujuan: ");
                int indexTujuan = scanner.nextInt();
                scanner.nextLine(); 
                
                Nasabah nasabahTujuan = bank.getNasabah(indexTujuan);
                if (nasabahTujuan == null || nasabahTujuan.getTabungan() == null) {
                    System.out.println("Nasabah tujuan tidak valid atau tidak memiliki tabungan!");
                    break;
                }
                
                System.out.print("Masukkan jumlah transfer: ");
                int jumlahTransfer = scanner.nextInt();
                scanner.nextLine();
                
                boolean transferBerhasil = tabungan.transfer(nasabahTujuan.getTabungan(), jumlahTransfer);
                if (transferBerhasil) {
                    System.out.println("Transfer berhasil!");
                    System.out.println("Saldo Anda sekarang: " + tabungan.getSaldo());
                } else {
                    System.out.println("Transfer gagal. Saldo tidak mencukupi!");
                }
                break;
            case 5:
                return;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void cariNasabah() {
        System.out.println("\nMenu Cari Nasabah:");
        System.out.println("1. Cari dengan Nama Lengkap");
        System.out.println("2. Cari dengan Nama Depan");
        System.out.println("3. Kembali ke Menu Utama");
        System.out.print("Pilihan Anda: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                System.out.print("Masukkan nama depan: ");
                String namaAwal = scanner.nextLine();
                
                System.out.print("Masukkan nama belakang: ");
                String namaAkhir = scanner.nextLine();
                
                int index = bank.searchNasabah(namaAwal, namaAkhir);
                if (index != -1) {
                    System.out.println("\nNasabah ditemukan:");
                    System.out.println(bank.getNasabah(index).toString());
                } else {
                    System.out.println("Nasabah tidak ditemukan!");
                }
                break;
            case 2:
                System.out.print("Masukkan nama depan: ");
                String namaDepan = scanner.nextLine();
                
                int[] hasil = bank.searchNasabah(namaDepan);
                if (hasil.length > 0) {
                    System.out.println("\nDaftar Nasabah dengan nama depan '" + namaDepan + "':");
                    for (int i : hasil) {
                        System.out.println("- " + bank.getNasabah(i).toString());
                    }
                } else {
                    System.out.println("Tidak ada nasabah dengan nama depan tersebut!");
                }
                break;
            case 3:
                return;
            default:
                System.out.println("Pilihan tidak valid!");
        }
    }

    private static void tampilkanSemuaNasabah() {
        System.out.println("\nDaftar Semua Nasabah:");
        for (int i = 0; i < bank.getJumlahNasabah(); i++) {
            System.out.println(i + ". " + bank.getNasabah(i).toString());
        }
    }
}