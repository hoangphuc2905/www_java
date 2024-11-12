package iuh.week02_lab_huynhhoangphuc_21036541.backend.services;

import iuh.week02_lab_huynhhoangphuc_21036541.backend.enums.ProductStatus;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Product;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Productimage;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Productprice;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.repositories.ProductRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public List<Product> getAll() {
        return productRepository.getAllProduct();
    }

    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }

    public boolean updateProduct(Product product) {
        return productRepository.updateProduct(product);
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }
    public boolean deleteProduct(long id) {
        Optional<Product> op = findById(id);
        if (op.isPresent()) {
            Product product = op.get();
            productRepository.setStatus(product, ProductStatus.TERMINATED);
            return true;
        }
        return false;
    }

    public boolean activeProduct(long id) {
        Optional<Product> op = findById(id);
        if (op.isPresent()) {
            Product product = op.get();
            productRepository.setStatus(product, ProductStatus.ACTIVE);
            return true;
        }
        return false;
    }

    public boolean restProduct(long id) {
        Optional<Product> op = findById(id);
        if (op.isPresent()) {
            Product product = op.get();
            productRepository.setStatus(product, ProductStatus.IN_ACTIVE);
            return true;
        }
        return false;
    }
    public void updateStatus(Long id, ProductStatus status) {
        productRepository.updateStatus(id, status);
    }
    public List<Productimage> getProductImages(long productId) {
        return productRepository.getProductImages(productId);
    }
    public List<Productprice> getProductByPrice(long productId) {
        return productRepository.getProductByPrice(productId);
    }
    public Productimage getRandomProductImage(long productId) {
        List<Productimage> images = getProductImages(productId);
        if (images.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return images.get(random.nextInt(images.size()));
    }

    public List<Productprice> getAllPrice() {
        return productRepository.getAllPrice();
    }

    public void insertProductPrice(Productprice productprice) {
        productRepository.insertProductPrice(productprice);
    }

    public void insertImage(Productimage productimage) {
        productRepository.insertImage(productimage);
    }
    public Productprice getPriceByDate(Date date, double price) {
        return productRepository.getPriceByDate(date, price);
    }
    public String findPriceNote(Long id, Date date, Double price) {
        return productRepository.findPriceNote(id, date, price);
    }

    public Productprice findPrice(Long id, Double price) {
        return productRepository.findPrice(id, price);
    }
}
