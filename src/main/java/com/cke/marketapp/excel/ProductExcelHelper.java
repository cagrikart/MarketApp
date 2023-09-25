package com.cke.marketapp.excel;

import com.cke.marketapp.entities.Department;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequiredArgsConstructor
@Component
@AllArgsConstructor
public class ProductExcelHelper {
    private DepartmentRepository departmentRepository;
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = {"id", "barkodCode", "productName", "purchasePrice", "price", "categoryId", "stockGroupId", "groupName", "quantity", "department_id"};
    static String SHEET = "Product";


    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public List<Product> excelToProduct(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Product> products = new ArrayList<Product>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Product product = new Product();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            product.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            product.setBarkodCode(currentCell.getStringCellValue());
                            break;

                        case 2:
                            product.setProductName(currentCell.getStringCellValue());

                            break;

                        case 3:
                            product.setPurchasePrice(Double.parseDouble(currentCell.getStringCellValue()));
                            // author.setAbout((currentCell.getStringCellValue()));
                            break;

                        case 4:
                            product.setPrice(Double.parseDouble(currentCell.getStringCellValue()));
                            break;

                        case 5:
                            //year
                            product.setCategoryId(Double.parseDouble(currentCell.getStringCellValue()));
                            break;

                        case 6:
                            //imagesurl
                            product.setStockGroupId(Double.parseDouble(currentCell.getStringCellValue()));
                            break;
                        case 7:
                            //STOCK
                            product.setGroupName(currentCell.getStringCellValue());
                            break;
                        case 8:
                            //price
                            product.setPrice(currentCell.getNumericCellValue());
                            break;
                        case 9:
                            //price
                            product.setQuantity((int) currentCell.getNumericCellValue());
                            break;
                        case 10:
                            //price
                            int departmentId = (int) currentCell.getNumericCellValue();
                            Department department = this.departmentRepository.findById((long) departmentId).get();
                            if (department != null) {
                                product.setDepartment(department);
                            } else {
                                // Hata işleme: Geçerli bir departman bulunamadığında ne yapılacağını belirleyin.
                            }
                            break;
                        default:
                            break;
                    }

                    cellIdx++;
                }

                products.add(product);
            }

            workbook.close();

            return products;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}

