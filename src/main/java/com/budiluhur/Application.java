package com.budiluhur;

/**
 * Hello world!
 *
 */
public class Application {
    public static void main(String[] args) {

        System.out.println("======");
        System.out.println(" SISTEM KATALOG PRODUK ENTERPRISE v1.0 ");

        System.out.println("======");

        // Deklarasi variabel produk
        String productName = "Mouse Wireless Silent";
        double unitPrice = 175000.0;
        int stockQuantity = 12;

        // Memanggil method untuk menampilkan informasi
        printProductDetails(productName, unitPrice, stockQuantity);
        
        // Memeriksa status ketersediaan
        boolean isAvailable = checkStockStatus(stockQuantity);
        System.out.println("Status Ketersediaan: " + (isAvailable ? "Tersedia" : "Stok Habis"));
    }

    public static void printProductDetails(String name, double price, int stock) {
        System.out.println("Nama Produk : " + name);
        System.out.println("Harga Satuan: Rp" + price);
        System.out.println("Jumlah Stok : " + stock + " unit");
    }

    public static boolean checkStockStatus(int stock) {
        return stock > 0;
    }
}
