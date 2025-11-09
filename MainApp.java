public class MainApp {
    public static void main(String[] args) {
        System.out.println("=== SIMULASI PEMBELIAN BARANG TANPA DATABASE ===\n");

        Produk mawar = new Produk("Bunga Mawar", 10);

        // Dua pembeli beli bersamaan
        Thread pembeli1 = new Thread(new ProsesTransaksi(mawar, 6), "Lisa");
        Thread pembeli2 = new Thread(new ProsesTransaksi(mawar, 5), "Rara");

        pembeli1.start();
        pembeli2.start();

        try {
            pembeli1.join();
            pembeli2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n🌷 Stok akhir " + mawar.getNama() + ": " + mawar.getStok());
    }
}