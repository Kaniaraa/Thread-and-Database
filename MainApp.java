/**
 * TUGAS PBO - Simulasi Toko Online dengan Thread dan Database
 * 
 * Kasus:
 * - Dua pelanggan (Rara dan Diva) beli barang yang sama di waktu hampir bersamaan.
 * - Stok awal: 5
 * - Rara beli 3 unit
 * - Diva beli 4 unit
 * 
 * Dengan synchronized: hanya satu yang berhasil beli semua, yang lain gagal (stok tidak cukup).
 * Tanpa synchronized: dua-duanya bisa beli -> stok jadi minus!
 */
public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== SIMULASI PEMBELIAN BARANG ONLINE ===\n");

        DatabaseManager db = new DatabaseManager();
        Produk laptop = db.getProduk(1); // id 1 = Laptop ASUS

        if (laptop == null) {
            System.out.println("❌ Produk tidak ditemukan!");
            return;
        }

        System.out.println("Produk: " + laptop.getNama());
        System.out.println("Stok awal: " + laptop.getStok() + "\n");

        // Dua pembeli beli bersamaan
        Thread rara = new Thread(new ProsesTransaksi(laptop, 3, "Rara"));
        Thread diva = new Thread(new ProsesTransaksi(laptop, 4, "Diva"));

        rara.start();
        diva.start();

        try {
            rara.join();
            diva.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== HASIL AKHIR ===");
        System.out.println("Sisa stok: " + laptop.getStok());

        db.close();
    }
}