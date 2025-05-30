package com.elyes.ecommerce.product;


import com.elyes.ecommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;


    public Integer createProduct(ProductRequest request) {
        var product = mapper.toProduct(request);
        return repository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
    var productIds = request.stream().map(ProductPurchaseRequest::productId).toList(); //return just the id
    var storedProducts = repository.findAllByIdInOrderById(productIds); //from the product entity
    if (productIds.size() != storedProducts.size()) {
        throw new ProductPurchaseException("Product not found");
      }
    var storesRequest = request
            .stream()
            .sorted(Comparator.comparing(ProductPurchaseRequest::productId)) //return the hole products from the request without data base
            .toList();

    var purshasedProducts = new ArrayList<ProductPurchaseResponse>();

    for (int i = 0; i < storesRequest.size(); i++) {
        var product = storedProducts.get(i);
        var productRequest = storesRequest.get(i);
        if (product.getAvailableQuantity() < productRequest.quantity()){
            throw new ProductPurchaseException("Insufficient quantity for product with id " + productRequest.productId());
        }
        var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
        product.setAvailableQuantity(newAvailableQuantity);
        repository.save(product);
        purshasedProducts.add(mapper.toProductPurchaseResponse(product,productRequest.quantity()));
    }
    return purshasedProducts;

    }

    public ProductResponse findById(Integer productId) {
    return repository.findById(productId).map(mapper::toProdcutResponse)
            .orElseThrow(()-> new EntityNotFoundException("product not  found with the Id:: "+productId));
    }

    public List<ProductResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toProdcutResponse)
                .collect(Collectors.toList());
    }
}
