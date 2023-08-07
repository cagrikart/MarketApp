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
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderDetailsMapperUtil {

    private OrderDetailsRepository orderDetailsRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderDetails postOrderDetails(OrderDetailsRequest request) {
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

                Order order = new Order();
                OrderDetails orderDetails = new OrderDetails();
                orderDetails.setQuantity(requestedQuantity);
                orderDetails.setProduct(product);

                // Calculate total price
                double totalPrice = product.getPrice() * requestedQuantity;
                orderDetails.setTotalPrice(totalPrice);

                // Add order details to the order
                order.addOrderDetails(orderDetails);

                // Save order and its details
                order = orderRepository.save(order);

                return orderDetails;
            } else {
                // Yeterli stok yoksa buraya düşecek işlemler
                // Örneğin, bir hata işleme mekanizması kullanabilir veya null dönebilirsiniz.
                // Örnek olarak:
                throw new RuntimeException("Yeterli stok yok.");
            }
        }

        return null;  // Ürün bulunamadıysa null dönebilir veya uygun bir hata işleme yapılabilir.
    }

}
