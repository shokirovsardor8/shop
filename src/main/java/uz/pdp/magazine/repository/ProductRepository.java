package uz.pdp.magazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.entity.product.ProductDatabase;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductDatabase,Integer> {
    List<ProductDatabase> findByCategoryDatabase(CategoryDatabase categoryDatabase);
}
