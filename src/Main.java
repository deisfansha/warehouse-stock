import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Membuat inisialisasi array 3 dimensi
        ArrayList<ArrayList<ArrayList<String>>> warehouse = new ArrayList<>();
        int menu;

        while (true){
            System.out.println("\n=========Menu======");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Tambah Stok Barang");
            System.out.println("3. Kurangi Stok Barang");
            System.out.println("4. Lihat Stok Barang");
            System.out.println("0. Exit");
            System.out.print("\nPilih Menu: ");

            if (input.hasNextInt()) {
                menu = input.nextInt();

                switch (menu) {
                    case 1:
                        System.out.print("Masukkan Kategori Barang: ");
                        String inputKategori = input.next();
                        System.out.print("Masukkan Nama Barang: ");
                        String inputNama = input.next();

                        int inputStock;
                        while (true){
                            System.out.print("Masukkan Input Stok: ");
                            if (input.hasNextInt()) {
                                inputStock = input.nextInt();
                                if (inputStock >= 0) { // Memastikan input stok tidak negatif
                                    break;
                                } else {
                                    System.out.println("Stok tidak boleh kurang dari 0");
                                }
                            }
                            else {
                                System.out.println("Hanya boleh memasukkan bilangan bulat");
                                input.next();
                            }
                        }

                        // Memasukkan data ke dalam ArrayList warehouse
                        ArrayList<String> newItem = new ArrayList<>();
                        newItem.add(inputKategori);
                        newItem.add(inputNama);
                        newItem.add(String.valueOf(inputStock));

                        // Cek apakah dimensi ke-2 sudah ada
                        int dimensi2Index = -1;
                        for (int i = 0; i < warehouse.size(); i++) {
                            ArrayList<String> dimensi2Item = warehouse.get(i).get(0);
                            if (dimensi2Item.get(0).equals(inputKategori)) {
                                dimensi2Index = i;
                                break;
                            }
                        }

                        // Jika dimensi ke-2 belum ada, tambahkan baru
                        if (dimensi2Index == -1) {
                            ArrayList<ArrayList<String>> dimensi2 = new ArrayList<>();
                            dimensi2.add(newItem);
                            warehouse.add(dimensi2);
                        } else {
                            // Jika dimensi ke-2 sudah ada, tambahkan item ke dalamnya
                            warehouse.get(dimensi2Index).add(newItem);
                        }

                        System.out.println("Barang berhasil ditambahkan ke dalam warehouse.");
                        break;

                    case 2:
                        // Menampilkan isi dari ArrayList warehouse
                        if (warehouse.isEmpty()) {
                            System.out.println("Data masih kosong."); // Jika gudang kosong, tampilkan pesan ini
                        } else {
                            System.out.print("Masukkan Nama Barang: "); // Minta pengguna memasukkan nama barang yang akan dicari
                            String cariNama = input.next();

                            boolean found = false;
                            // Mencari barang berdasarkan nama di dalam gudang
                            for (ArrayList<ArrayList<String>> dimensi2 : warehouse) { // Loop dimensi ke-2 (kategori barang)
                                for (ArrayList<String> item : dimensi2) { // Loop item-item barang di dalam dimensi ke-2
                                    if (item.get(1).equals(cariNama)) { // Jika nama barang cocok dengan yang dicari
                                        found = true;
                                        System.out.println("\nKategori Barang: " + item.get(0)); // Tampilkan kategori barang
                                        System.out.println("Nama Barang: " + item.get(1)); // Tampilkan nama barang
                                        System.out.println("Stok Barang: " + item.get(2)); // Tampilkan stok barang

                                        // Minta pengguna memasukkan jumlah stok yang ingin dikurangi
                                        int reduceStock;
                                        while (true) {
                                            System.out.print("Masukkan jumlah Stok Barang yang akan ditambahkan (bilangan bulat): ");
                                            if (input.hasNextInt()) {
                                                reduceStock = input.nextInt();
                                                input.nextLine(); // Mengonsumsi karakter newline setelah membaca integer
                                                break;
                                            } else {
                                                System.out.println("Hanya boleh memasukkan bilangan bulat");
                                                input.nextLine(); // Mengonsumsi input yang tidak valid
                                            }
                                        }

                                        int currentStock = Integer.parseInt(item.get(2)); // Stok saat ini
                                        int newStock = currentStock + reduceStock; // Menghitung stok baru setelah penambahan
                                        item.set(2, String.valueOf(newStock)); // Menyimpan stok baru ke dalam ArrayList
                                        System.out.println("Stok Barang berhasil ditambahkan. Stok sekarang: " + newStock);

                                        break; // Keluar dari loop setelah menemukan barang yang sesuai
                                    }
                                }
                            }

                            if (!found) {
                                System.out.println("Barang dengan nama " + cariNama + " tidak ditemukan di dalam warehouse.");
                            }
                        }

                        break; // Keluar dari blok kondisi utama
                    case 3:
                        // Menampilkan isi dari ArrayList warehouse
                        if (warehouse.isEmpty()) {
                            System.out.println("Data masih kosong."); // Jika gudang kosong, tampilkan pesan ini
                        } else {
                            System.out.print("Masukkan Nama Barang: "); // Minta pengguna memasukkan nama barang yang akan dicari
                            String cariNama = input.next();

                            boolean found = false;
                            // Mencari barang berdasarkan nama di dalam gudang
                            for (ArrayList<ArrayList<String>> dimensi2 : warehouse) { // Loop dimensi ke-2 (kategori barang)
                                for (ArrayList<String> item : dimensi2) { // Loop item-item barang di dalam dimensi ke-2
                                    if (item.get(1).equals(cariNama)) { // Jika nama barang cocok dengan yang dicari
                                        found = true;
                                        System.out.println("Kategori Barang: " + item.get(0)); // Tampilkan kategori barang
                                        System.out.println("Nama Barang: " + item.get(1)); // Tampilkan nama barang
                                        System.out.println("Stok Barang: " + item.get(2)); // Tampilkan stok barang

                                        // Minta pengguna memasukkan jumlah stok yang ingin dikurangi
                                        int reduceStock;
                                        while (true) {
                                            System.out.print("Masukkan jumlah Stok Barang yang akan dikurangi (bilangan bulat): ");
                                            if (input.hasNextInt()) {
                                                reduceStock = input.nextInt();
                                                input.nextLine(); // Mengonsumsi karakter newline setelah membaca integer
                                                break;
                                            } else {
                                                System.out.println("Hanya boleh memasukkan bilangan bulat");
                                                input.nextLine(); // Mengonsumsi input yang tidak valid
                                            }
                                        }

                                        int currentStock = Integer.parseInt(item.get(2)); // Stok saat ini
                                        int newStock = currentStock - reduceStock; // Menghitung stok baru setelah pengurangan
                                        if (newStock >= 0) {
                                            item.set(2, String.valueOf(newStock)); // Menyimpan stok baru ke dalam ArrayList
                                            System.out.println("Stok Barang berhasil dikurangi. Stok sekarang: " + newStock);
                                        } else {
                                            System.out.println("Stok Barang tidak cukup untuk dikurangi sebanyak itu.");
                                        }
                                        break; // Keluar dari loop setelah menemukan barang yang sesuai
                                    }
                                }
                            }

                            if (!found) {
                                System.out.println("Barang dengan nama " + cariNama + " tidak ditemukan di dalam warehouse.");
                            }
                        }

                        break; // Keluar dari blok kondisi utama
                    case 4:
                        // Menampilkan isi dari ArrayList warehouse
                        if (warehouse.size() == 0) {
                            System.out.println("Warehouse masih kosong.");
                        } else {
                            for (ArrayList<ArrayList<String>> dimensi2 : warehouse) {
                                for (ArrayList<String> item : dimensi2) {
                                    System.out.println("Kategori Barang: " + item.get(0));
                                    System.out.println("Nama Barang: " + item.get(1));
                                    System.out.println("Stok Barang: " + item.get(2));
                                    System.out.println();
                                }
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Anda telah keluar dari program");
                        input.close(); // Menutup Scanner
                        return; // Keluar dari Program
                    default:
                        System.out.println("Maaf Format tidak Sesuai");
                        // Code for handling invalid menu choice
                        break;
                }
            } else {
                System.out.println("Maaf, input harus berupa angka.");
                input.next();
            }

        }
    }
}