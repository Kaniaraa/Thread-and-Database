public class Produk {
    private String nama;
    private int stok;

    public Produk(String nama, int stok) {
        this.nama = nama;
        this.stok = stok;
    }

    // synchronized supaya thread lain gak bisa ubah stok di waktu bersamaan
    public synchronized void beli(int jumlah) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] mencoba beli " + jumlah + " " + nama);

        if (jumlah <= 0) {
            System.out.println("❌ [" + threadName + "] jumlah tidak valid!");
            return;
        }

        if (stok >= jumlah) {
            try {
                Thread.sleep(100); // simulasi delay transaksi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stok -= jumlah;
            System.out.println("✅ [" + threadName + "] berhasil beli! Stok sisa: " + stok);
        } else {
            System.out.println("❌ [" + threadName + "] stok tidak cukup!");
        }
    }

    public int getStok() {
        return stok;
    }

    public String getNama() {
        return nama;
    }
}