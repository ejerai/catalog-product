package com.budiluhur.catalog.servlet;

import com.budiluhur.catalog.exception.ProductNotFoundException;
import com.budiluhur.catalog.model.Product;
import com.budiluhur.catalog.repository.ProductRepository;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() {
        productRepository = new ProductRepository();
        productRepository.addProduct(new Product("PRD-01", "Keyboard Mechanical", 450000.0));
        productRepository.addProduct(new Product("PRD-02", "Mouse Wireless Silent", 175000.0));
        productRepository.addProduct(new Product("PRD-03", "Monitor Gaming 24 Inch", 2100000.0));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String id = request.getParameter("id");

        if (id == null || id.isBlank()) {
            printProductList(out);
            return;
        }

        try {
            Product product = productRepository.findById(id.trim());
            printProductDetail(out, product);
        } catch (ProductNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            printNotFound(out, e.getMessage());
        }
    }

    private void printProductList(PrintWriter out) {
        List<Product> products = productRepository.findAll();

        printHeader(out, "Katalog Produk Web");
        out.println("<h2>=== DAFTAR KATALOG PRODUK (WEB) ===</h2>");
        out.println("<table border='1' cellpadding='8'>");
        out.println("<tr><th>ID Produk</th><th>Nama Produk</th><th>Harga Satuan</th></tr>");

        for (Product p : products) {
            out.println("<tr>");
            out.println("<td>" + p.getId() + "</td>");
            out.println("<td>" + p.getName() + "</td>");
            out.println("<td>Rp" + p.getPrice() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        printFooter(out);
    }

    private void printProductDetail(PrintWriter out, Product p) {
        printHeader(out, "Detail Produk");
        out.println("<h2>=== DETAIL PRODUK ===</h2>");
        out.println("<table border='1' cellpadding='8'>");
        out.println("<tr><th>ID Produk</th><td>" + p.getId() + "</td></tr>");
        out.println("<tr><th>Nama Produk</th><td>" + p.getName() + "</td></tr>");
        out.println("<tr><th>Harga Satuan</th><td>Rp" + p.getPrice() + "</td></tr>");
        out.println("</table>");
        out.println("<p><a href='products'>&laquo; Kembali ke daftar produk</a></p>");
        printFooter(out);
    }

    private void printNotFound(PrintWriter out, String message) {
        printHeader(out, "Produk Tidak Ditemukan");
        out.println("<h2 style='color:red;'>Produk dengan ID 'PRD-99' tidak ditemukan!</h2>");
        out.println("<p style='color:red;'>" + escapeHtml(message) + "</p>");
        out.println("<p><a href='products'>&laquo; Kembali ke daftar</a></p>");
        printFooter(out);
    }

    private void printHeader(PrintWriter out, String title) {
        out.println("<html>");
        out.println("<head><title>" + title + "</title></head>");
        out.println("<body>");
    }

    private void printFooter(PrintWriter out) {
        out.println("</body>");
        out.println("</html>");
    }

    private String escapeHtml(String text) {
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
}