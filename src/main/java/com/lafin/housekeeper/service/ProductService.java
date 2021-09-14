package com.lafin.housekeeper.service;

import com.lafin.housekeeper.constant.ProductStatus;
import com.lafin.housekeeper.dto.request.ProductAddRequest;
import com.lafin.housekeeper.dto.request.ProductModifyRequest;
import com.lafin.housekeeper.entity.Product;
import com.lafin.housekeeper.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final OrderService orderService;

    private final OrderMarketService orderMarketService;

    private final ProductRepository productRepository;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product add(ProductAddRequest productAddRequest) {
        var product = new Product();
        product.setRoomId(productAddRequest.getRoomId());
        product.setName(productAddRequest.getName());
        product.setCount(productAddRequest.getCount());
        product.setMinimumCount(productAddRequest.getMinimumCount());
        product.setOrderCount(productAddRequest.getOrderCount());

        return productRepository.save(product);
    }

    public Product modify(Long productId, ProductModifyRequest productModifyRequest) {
        var product = new Product();
        product.setId(productId);
        product.setName(productModifyRequest.getName());
        product.setCount(productModifyRequest.getCount());
        product.setMinimumCount(productModifyRequest.getMinimumCount());
        product.setOrderCount(productModifyRequest.getOrderCount());

        return productRepository.save(product);
    }

    public Product useProduct(Long productId) throws Exception {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("물건 정보를 찾을 수 없습니다."));

        int remainProductCount = product.getCount() - 1;
        int minimumProductCount = product.getMinimumCount();
        var productStatus = getProductCountStatus(remainProductCount, minimumProductCount);

        // 사용처리
        product.setCount(remainProductCount);
        orderService.addUseLog(product);

        // 재고가 없거나 위험 상태 일 떄 주문 처리
        if (productStatus == ProductStatus.EMPTY || productStatus == ProductStatus.WARN) {
            orderMarketService.doOrder(product);
            product.setCount(remainProductCount + product.getOrderCount());
        }

        return productRepository.save(product);
    }

    public ProductStatus getProductCountStatus(int productCount, int productMinimumCount) throws Exception {
        if (productCount <= 0) return ProductStatus.EMPTY;
        if (productCount <= productMinimumCount) return ProductStatus.WARN;

        return ProductStatus.STABLE;
    }

    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}
