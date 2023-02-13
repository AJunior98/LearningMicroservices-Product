package org.acme.service;

import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream().forEach(item -> {
            products.add(mapProductEntityToProductDTO(item));
        });

        return products;
    }

    public void createNewProduct(ProductDTO product) {
        productRepository.persist(mapProductEntityToProductDTO(product));
    }

    public void changeProduct(Long id, ProductDTO product) {
        productRepository.persist(mapProductEntityToProductDTO(product));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapProductEntityToProductDTO(ProductEntity productEntity) {
        ProductDTO product = new ProductDTO();

        product.setName(productEntity.getName());
        product.setDescription(productEntity.getDescription());
        product.setCategory(productEntity.getCategory());
        product.setPrice(productEntity.getPrice());
        product.setModel(product.getModel());

        return product;
    }

    private ProductEntity mapProductEntityToProductDTO(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setModel(productEntity.getModel());

        return productEntity;
    }



}
