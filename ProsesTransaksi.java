/**
 * Class ini adalah "pekerjaan" yang dijalankan oleh thread.
 * Tiap pembeli punya thread sendiri untuk beli barang.
 */
public class ProsesTransaksi implements Runnable {
    private final Produk produk;
    private final int jumlah;
    private final String pembeli;

    public ProsesTransaksi(Produk produk, int jumlah, String pembeli) {
        this.produk = produk;
        this.jumlah = jumlah;
        this.pembeli = pembeli;
    }

    @Override
    public void run() {
        produk.beli(jumlah, pembeli);
    }
}
