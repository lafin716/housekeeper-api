package com.lafin.housekeeper.service;

import com.lafin.housekeeper.constant.ProductStatus;
import com.lafin.housekeeper.dto.request.ProductAddRequest;
import com.lafin.housekeeper.dto.request.ProductModifyRequest;
import com.lafin.housekeeper.dto.response.ProductUseResponse;
import com.lafin.housekeeper.entity.OrderMarket;
import com.lafin.housekeeper.entity.Product;
import com.lafin.housekeeper.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final OrderMarketService orderMarketService;

    private final InventoryLogService inventoryLogService;

    private final ProductRepository productRepository;

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Product add(ProductAddRequest productAddRequest) {
        var product = new Product();
        product.setRoomId(productAddRequest.getRoomId());
        product.setName(productAddRequest.getName());
        product.setStock(productAddRequest.getCount());
        product.setMinimumStock(productAddRequest.getMinimumCount());
        product.setOrderStock(productAddRequest.getOrderCount());

        return productRepository.save(product);
    }

    public Product modify(Long productId, ProductModifyRequest productModifyRequest) {
        var product = new Product();
        product.setId(productId);
        product.setName(productModifyRequest.getName());
        product.setStock(productModifyRequest.getCount());
        product.setMinimumStock(productModifyRequest.getMinimumCount());
        product.setOrderStock(productModifyRequest.getOrderCount());

        return productRepository.save(product);
    }
    
    public boolean isUsableProduct(Product product) {
        return product.getStock() <= 0;
    }

    public ProductUseResponse useProduct(Long productId) throws Exception {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("물건 정보를 찾을 수 없습니다."));
        
        if (isUsableProduct(product)) {
            throw new Exception("사용할 수 있는 재고가 없습니다");
        }

        int remainProductCount = product.getStock() - 1;
        int minimumProductCount = product.getMinimumStock();
        var productStatus = getProductCountStatus(remainProductCount, minimumProductCount);
        var orderMarketUrl = orderMarketService.getFirstMarketUrl(product.getId());

        // 사용처리
        product.setStock(remainProductCount);
        product = productRepository.save(product);
        inventoryLogService.addUseLog(product);

        return ProductUseResponse.builder()
                .productStatus(productStatus)
                .message(productStatus.getMessage())
                .url(orderMarketUrl)
                .name(product.getName())
                .orderStock(product.getOrderStock())
                .remainStock(product.getStock())
                .build();
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
