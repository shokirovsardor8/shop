package uz.pdp.magazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.magazine.entity.category.CategoryDatabase;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryDatabase,Integer> {
    List<CategoryDatabase> findAllByParentId(Integer parentId);
}
