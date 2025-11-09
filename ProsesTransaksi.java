public class ProsesTransaksi implements Runnable {
    private Produk produk;
    private int jumlah;

    public ProsesTransaksi(Produk produk, int jumlah) {
        this.produk = produk;
        this.jumlah = jumlah;
    }

    @Override
    public void run() {
        produk.beli(jumlah);
    }
}
