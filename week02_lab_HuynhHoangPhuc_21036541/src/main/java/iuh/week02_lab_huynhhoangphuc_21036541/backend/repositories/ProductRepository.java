package iuh.week02_lab_huynhhoangphuc_21036541.backend.repositories;


import iuh.week02_lab_huynhhoangphuc_21036541.backend.enums.ProductStatus;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Product;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Productimage;
import iuh.week02_lab_huynhhoangphuc_21036541.backend.models.Productprice;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    private EntityManager em;
    private EntityTransaction et;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ProductRepository() {
        em = Persistence.createEntityManagerFactory("MariaBD").createEntityManager();
        et = em.getTransaction();
    }

    public void insertProduct(Product product) {
        try {
            et.begin();
            em.persist(product);
            et.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            et.rollback();
        }
    }

    public void setStatus(Product product, ProductStatus status) {
        product.setStatus(status);
    }

    public void updateStatus(Long id, ProductStatus status) {
        TypedQuery<Product> query = em.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id);
        Product product1 = query.getSingleResult();
        product1.setStatus(status);
        try {
            et.begin();
            em.merge(product1);
            et.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            et.rollback();
        }
    }

    public boolean updateProduct(Product product) {
        try {
            et.begin();
            em.merge(product);
            et.commit();
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            et.rollback();
            return false;
        }
    }

    public List<Product> getAllProduct() {
        return em.createNamedQuery("Product.getAllProduct", Product.class)
                .getResultList();
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(em.find(Product.class, id));
    }

    public String findPriceNote(Long id, Date date, Double price) {
        String query = "SELECT p.note FROM Productprice p WHERE p.product.id = :id AND p.id.priceDateTime = :date AND p.price = :price";
        return em.createQuery(query, String.class)
                .setParameter("id", id)
                .setParameter("date", date)
                .getSingleResult();
    }

    public Productprice findPrice(Long id, Double price) {
        String query = "SELECT p FROM Productprice p WHERE p.product.id = :id AND p.price = :price";
        return em.createQuery(query, Productprice.class)
                .setParameter("id", id)
                .setParameter("price", price)
                .getSingleResult();
    }
    public List<Productimage> getProductImages(long productId) {
        return em.createNamedQuery("Productimage.findByProduct_Id", Productimage.class)
                .setParameter("id", productId)
                .getResultList();
    }

    public List<Productprice> getProductByPrice(long productId) {
        String query = "SELECT p FROM Productprice p WHERE p.product.id = :productId";
        return em.createQuery(query, Productprice.class)
                .setParameter("productId", productId)
                .getResultList();
    }

    public List<Productprice> getAllPrice() {
        return em.createQuery("SELECT p FROM Productprice p", Productprice.class)
                .getResultList();
    }

    public void insertProductPrice(Productprice productprice) {
        try {
            et.begin();
            em.persist(productprice);
            et.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            et.rollback();
        }
    }

    public void insertImage(Productimage productimage) {
        try {
            et.begin();
            em.persist(productimage);
            et.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            et.rollback();
        }
    }

    public Productprice getPriceByDate(Date date, double price)
    {
        String query = "SELECT p FROM Productprice p WHERE p.id.priceDateTime = :date AND p.price = :price";
        return em.createQuery(query, Productprice.class)
                .setParameter("date", date)
                .setParameter("price", price)
                .getSingleResult();
    }

    public void close() {
        em.close();
    }
}
