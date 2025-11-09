/**
 * Class Produk merepresentasikan barang di toko online.
 * 
 * Fitur penting:
 * - Synchronized di method `beli()` supaya dua pelanggan ga bisa beli bersamaan.
 * - Integrasi dengan DatabaseManager biar stok selalu update di database.
 */
public class Produk {
    private final int id;
    private final String nama;
    private int stok;
    private final double harga;
    private final DatabaseManager dbManager;

    public Produk(int id, String nama, int stok, double harga, DatabaseManager dbManager) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.dbManager = dbManager;
    }

    public synchronized boolean beli(int jumlah, String namaPembeli) {
        System.out.println("[" + namaPembeli + "] mencoba beli " + jumlah + " unit " + nama);

        if (jumlah <= 0) {
            System.out.println("❌ Jumlah pembelian tidak valid!");
            return false;
        }

        if (stok >= jumlah) {
            try {
                Thread.sleep(500); // simulasi waktu proses pembayaran
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }

            int stokSebelum = stok;
            stok -= jumlah;
            boolean sukses = dbManager.updateStok(id, stok);

            if (sukses) {
                System.out.println("✅ [" + namaPembeli + "] berhasil beli " + jumlah + " unit. Stok: " + stokSebelum + " -> " + stok);
                return true;
            } else {
                stok = stokSebelum; // rollback
                System.out.println("⚠️ [" + namaPembeli + "] gagal update database, stok dikembalikan.");
            }

        } else {
            System.out.println("🚫 [" + namaPembeli + "] gagal beli. Stok tidak cukup!");
        }

        return false;
    }

    public String getNama() { return nama; }
    public int getStok() { return stok; }
}