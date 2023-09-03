package com.cke.marketapp.util;

import com.cke.marketapp.dto.OrderDetailsRequest;
import com.cke.marketapp.entities.Order;
import com.cke.marketapp.entities.OrderDetails;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.repository.OrderDetailsRepository;
import com.cke.marketapp.repository.OrderRepository;
import com.cke.marketapp.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderDetailsMapperUtil {

    private OrderDetailsRepository orderDetailsRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public List<OrderDetails> postOrderDetails(List<OrderDetailsRequest> requests) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDetailsRequest request : requests) {
            Optional<Product> productDb = this.productRepository.findById(request.getProductId());

            if (productDb.isPresent()) {
                Product product = productDb.get();
                int requestedQuantity = request.getQuantity();

                if (product.getQuantity() >= requestedQuantity) {
                    // Stoktan düşülecek miktarı hesaplayalım
                    int remainingStock = product.getQuantity() - requestedQuantity;
                    product.setQuantity(remainingStock);

                    // Stok güncellemesini kaydedelim
                    this.productRepository.save(product);

                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setQuantity(requestedQuantity);
                    orderDetails.setProduct(product);

                    // Calculate total price
                    double totalPrice = product.getPrice() * requestedQuantity;
                    orderDetails.setTotalPrice(totalPrice);

                    // OrderDetails listesine ekle
                    orderDetailsList.add(orderDetails);
                } else {
                    throw new RuntimeException("Yetersiz stok: Ürün ID - " + product.getId());
                }
            } else {
                throw new RuntimeException("Ürün bulunamadı: Ürün ID - " + request.getProductId());
            }
        }

        // Tüm sipariş detaylarını kaydet
        Order order = new Order();
        order.setOrderDetailsList(orderDetailsList);
        order = orderRepository.save(order);

        return orderDetailsList;
    }


}
