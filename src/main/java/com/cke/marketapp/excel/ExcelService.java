package com.cke.marketapp.excel;

import com.cke.marketapp.entities.Product;
import com.cke.marketapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@AllArgsConstructor

public class ExcelService {

    private ProductRepository productRepository;

    private ProductExcelHelper productExcelHelper;

    public void saveProduct(MultipartFile file) {
        try {
            List<Product>products = productExcelHelper.excelToProduct(file.getInputStream());
//                    .stream().filter(author -> author.getId() != null
//                    || author.getFirstName() != null
//                                    || author.getLastName() != null
//                    ).collect(Collectors.toList());
            productRepository.saveAll(products);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }


}