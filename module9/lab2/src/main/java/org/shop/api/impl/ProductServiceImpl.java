package org.shop.api.impl;

import org.shop.api.ProductService;
import org.shop.data.Product;
import org.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    //+ ProductService: использовать autowiring by constructor
    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        super();
        this.repository = repository;
    }
    
    /* (non-Javadoc)
     * @see org.shop.api.ProductService#getProductById(java.lang.Long)
     */
    @Override
    public Product getProductById(Long id) {
        return repository.getProductById(id);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProductService#getProducts()
     */
    @Override
    public List<Product> getProducts() {
        return repository.getProducts();
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProductService#getProductsByName(java.lang.String)
     */
    @Override
    public List<Product> getProductsByName(String name) {
        return repository.getProductsByName(name);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProductService#createProduct(org.shop.data.Product)
     */
    @Override
    public Long createProduct(Product product) {
        return repository.createProduct(product);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProductService#updateProduct(org.shop.data.Product)
     */
    @Override
    public void updateProduct(Product product) {
        repository.updateProduct(product);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProductService#deleteProduct(java.lang.Long)
     */
    @Override
    public void deleteProduct(Long productId) {
        repository.deleteProduct(productId);
    }
}
