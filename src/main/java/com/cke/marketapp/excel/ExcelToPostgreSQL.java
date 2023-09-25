package com.cke.marketapp.excel;


import com.cke.marketapp.entities.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelToPostgreSQL {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/marketapp";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        TypeDataImporter importer = new TypeDataImporter();
        importer.importData();
    }

    private static class TypeDataImporter {
        public void importData() {
            List<Product> productsList = readProductDataFromExcel();

            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                String insertQuery = "INSERT INTO type_table (id, name) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    for (Product product :productsList) {
                        preparedStatement.setLong(1, product.getId());
                        preparedStatement.setString(2, product.getProductName());

                        preparedStatement.executeUpdate();
                    }
                }

                System.out.println("Type Sheet verileri PostgreSQL veritabanına aktarıldı.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private List<Product> readProductDataFromExcel() {
            List<Product> productList = new ArrayList<>();

            try {
                FileInputStream file = new FileInputStream("path/to/your/excel/file.xlsx");
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheet("Type"); // Sayfa adını "Type" olarak düzenledik

                Iterator<Row> rowIterator = sheet.iterator();

                // Skip header row
                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Product product = new Product();

                    product.setId((long) row.getCell(0).getNumericCellValue());
                    product.getProductName();

                    productList.add(product);
                }

                workbook.close();
                file.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return productList;
        }

        private String getCellValue(Cell cell) {
            if (cell == null) {
                return "";
            }

            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue().trim();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                default:
                    return "";
            }
        }
    }
}
