package com.budiluhur.catalog;

import com.budiluhur.catalog.servlet.CategoryServlet;
import com.budiluhur.catalog.servlet.ProductServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Menjalankan Embedded Tomcat Server...");

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir("target/tomcat");
        tomcat.getConnector();

        Context ctx = tomcat.addContext("", new File(".").getAbsolutePath());

        Tomcat.addServlet(ctx, "ProductServlet", new ProductServlet());
        ctx.addServletMappingDecoded("/products", "ProductServlet");

        Tomcat.addServlet(ctx, "CategoryServlet", new CategoryServlet());
        ctx.addServletMappingDecoded("/categories", "CategoryServlet");

        tomcat.start();
        System.out.println("Produk   : http://localhost:8080/products");
        System.out.println("Kategori : http://localhost:8080/categories");
        tomcat.getServer().await();
    }
}