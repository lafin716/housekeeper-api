package com.lafin.housekeeper.service;

import com.lafin.housekeeper.constant.ProductStatus;
import com.lafin.housekeeper.dto.request.ProductAddRequest;
import com.lafin.housekeeper.dto.request.ProductModifyRequest;
import com.lafin.housekeeper.dto.request.RoomAddRequest;
import com.lafin.housekeeper.entity.Product;
import com.lafin.housekeeper.entity.Room;
import com.lafin.housekeeper.repository.ProductRepository;
import com.lafin.housekeeper.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

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

    public ProductStatus getProductCountStatus(int productCount, int productMinimumCount) throws Exception {
        if (productCount < 0) return ProductStatus.EMPTY;
        if (productCount <= productMinimumCount) return ProductStatus.WARN;

        return ProductStatus.STABLE;
    }

    public Product useProduct(Long productId) throws Exception {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new Exception("물건 정보를 찾을 수 없습니다."));

        int remainProductCount = product.getCount() - 1;
        int minimumProductCount = product.getMinimumCount();
        var productStatus = getProductCountStatus(remainProductCount, minimumProductCount);

        if (productStatus == ProductStatus.EMPTY) {

        }

        product.setCount(remainProductCount);

        return productRepository.save(product);
    }



    public void delete(Long productId) {
        productRepository.deleteById(productId);
    }
}
