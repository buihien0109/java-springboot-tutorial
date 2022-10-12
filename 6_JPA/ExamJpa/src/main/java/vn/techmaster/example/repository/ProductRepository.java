package vn.techmaster.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.techmaster.example.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getById(Long id);
}