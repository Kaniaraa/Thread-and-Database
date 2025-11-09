# Thread-and-Database

**Nama:** `Kania Putri Rohimatuz Azzahra Hamdani`
**NIM:** `F1D02310065`
Program ini mensimulasikan toko online sederhana di mana dua pelanggan (Rara dan Diva) melakukan pembelian secara bersamaan (multithreaded) terhadap produk yang sama dari database MySQL.

Simulasi ini menunjukkan pentingnya sinkronisasi (synchronized) untuk mencegah terjadinya race condition saat dua proses mengakses dan memodifikasi data yang sama.

**Thread (`implements Runnable`)** : Setiap pembeli dijalankan pada thread terpisah agar bisa melakukan transaksi bersamaan (concurrent).
**Synchronized Method** : Menjamin hanya satu thread yang dapat memproses pembelian pada satu waktu untuk mencegah race condition
**Race Condition** : Situasi di mana dua thread mencoba mengubah data yang sama pada waktu bersamaan sehingga hasilnya salah.
**`Thread.sleep()`** : Menyimulasikan waktu proses transaksi supaya efek multithreading terlihat.
**Integrasi Database (MySQL)** : Data produk (stok dan harga) diambil dan diperbarui dari database `db_toko`.
**`PreparedStatement`** : Digunakan untuk keamanan (mencegah SQL Injection) dan efisiensi query.
**`join()`** : Memastikan thread utama menunggu semua transaksi selesai sebelum menampilkan hasil akhir.

## Kesimpulan

1. Thread memungkinkan dua pembeli membeli bersamaan.
2. Synchronized mencegah stok produk dikurangi dua kali secara bersamaan.
3. Database menjaga agar data stok tetap konsisten.
4. Tanpa synchronized, stok bisa jadi negatif (data race terjadi).
