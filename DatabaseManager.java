import java.sql.*;

/**
 * Class ini untuk mengatur semua koneksi dan operasi database.
 * Tugasnya:
 * - Koneksi ke MySQL (via XAMPP)
 * - Mengambil data produk
 * - Update stok produk
 */
public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/db_toko";
    private static final String USER = "root";
    private static final String PASS = "";

    private Connection connection;

    public DatabaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Koneksi database berhasil!");
        } catch (Exception e) {
            System.err.println("❌ Gagal koneksi database: " + e.getMessage());
        }
    }

    // Ambil data produk berdasarkan ID
    public Produk getProduk(int id) {
        String sql = "SELECT * FROM produk WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String nama = rs.getString("nama");
                int stok = rs.getInt("stok");
                double harga = rs.getDouble("harga");
                return new Produk(id, nama, stok, harga, this);
            }
        } catch (SQLException e) {
            System.err.println("❌ Gagal ambil produk: " + e.getMessage());
        }
        return null;
    }

    // Update stok produk
    public synchronized boolean updateStok(int id, int stokBaru) {
        String sql = "UPDATE produk SET stok = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, stokBaru);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("❌ Gagal update stok: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✅ Koneksi database ditutup.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Error saat menutup koneksi: " + e.getMessage());
        }
    }
}