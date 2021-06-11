package uz.pdp.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.shop.entity.product.ProductDatabase;

public interface ProductRepository extends JpaRepository<ProductDatabase,Integer> {

}
