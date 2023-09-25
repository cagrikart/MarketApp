package com.cke.marketapp.util;

import com.cke.marketapp.dto.request.OrderDetailsRequest;
import com.cke.marketapp.dto.request.OrderRequest;
import com.cke.marketapp.dto.response.OrderDateResponse;
import com.cke.marketapp.dto.response.OrderResponse;
import com.cke.marketapp.entities.Employee;
import com.cke.marketapp.entities.Order;
import com.cke.marketapp.entities.OrderDetails;
import com.cke.marketapp.entities.Product;
import com.cke.marketapp.repository.EmployeeRepository;
import com.cke.marketapp.repository.OrderRepository;
import com.cke.marketapp.repository.ProductRepository;
import com.cke.marketapp.service.abstracts.OrderDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderMapperUtil {
    private EmployeeRepository employeeRepository;
    private OrderDetailsService orderDetailsService;
    private OrderRepository orderRepository;

    private ProductRepository productRepository;
    public OrderResponse listOrder(Order order) {
        OrderResponse response = new OrderResponse();
        response.setEmployeeId(order.getEmployee().getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());

        // OrderDetails'ların ID'lerini alın
        List<Long> orderDetailsIds = order.getOrderDetailsList()
                .stream()
                .map(orderDetails -> orderDetails.getId())
                .collect(Collectors.toList());

        response.setOrderDetailsIds(orderDetailsIds);
        return response;
    }
    public OrderDateResponse listDateOrder(LocalDate orderDate) {
        List<Order> orders = orderRepository.getOrderByOrderDate(orderDate);

        double totalAmount = orders.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();

        OrderDateResponse response = new OrderDateResponse();
        response.setOrderDate(orderDate);
        response.setTotalAmount(totalAmount);

        return response;
    }
    public OrderResponse postOrder(OrderRequest request) {
        // Order nesnesini oluşturun
        Order order = new Order();
        Optional<Employee> employeeOptional = employeeRepository.findById(request.getEmployeeId());

        if (employeeOptional.isPresent()) {
            order.setEmployee(employeeOptional.get());
        } else {
            throw new RuntimeException("Geçersiz çalışan ID: " + request.getEmployeeId());
        }

        order.setOrderDate(LocalDate.now()); // Sipariş tarihi

        // OrderDetailsRequest listesini alın
        List<OrderDetailsRequest> detailsRequests = request.getOrderDetailsList();
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        for (OrderDetailsRequest detailsRequest : detailsRequests) {
            Optional<Product> productOptional = productRepository.findById(detailsRequest.getProductId());

            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                int requestedQuantity = detailsRequest.getQuantity();

                if (product.getQuantity() >= requestedQuantity) {
                    // Stoktan düşülecek miktarı hesaplayın
                    int remainingStock = product.getQuantity() - requestedQuantity;
                    product.setQuantity(remainingStock);

                    // Stok güncellemesini kaydedin
                    productRepository.save(product);

                    // OrderDetails nesnesini oluşturun
                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setQuantity(requestedQuantity);
                    orderDetails.setProduct(product);
                    orderDetails.setOrder(order); // İlişkilendirilmiş Order'ı ayarlayın

                    // Toplam fiyatı hesaplayın ve ayarlayın
                    double totalPrice = product.getPrice() * requestedQuantity;
                    orderDetails.setTotalPrice(totalPrice);

                    orderDetailsList.add(orderDetails);
                } else {
                    throw new RuntimeException("Yetersiz stok: Ürün ID - " + product.getId());
                }
            } else {
                throw new RuntimeException("Ürün bulunamadı: Ürün ID - " + detailsRequest.getProductId());
            }
        }

        // OrderDetails listesini siparişe ekleyin
        order.setOrderDetailsList(orderDetailsList);

        // Toplam miktarı hesapla ve ayarla
        double totalAmount = orderDetailsList.stream().mapToDouble(OrderDetails::getTotalPrice).sum();
        order.setTotalAmount(totalAmount);

        // Siparişi kaydedin
        order = orderRepository.save(order);

        // OrderResponse nesnesini doldurun
        OrderResponse response = new OrderResponse();
        response.setEmployeeId(order.getEmployee().getId());
        response.setOrderDate(order.getOrderDate());
        response.setTotalAmount(order.getTotalAmount());

        // İlişkili OrderDetails'ların ID'lerini toplayın
        List<Long> orderDetailsIds = orderDetailsList.stream().map(OrderDetails::getId).collect(Collectors.toList());
        response.setOrderDetailsIds(orderDetailsIds);

        return response;
    }

}
