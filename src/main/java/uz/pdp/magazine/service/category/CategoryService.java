package uz.pdp.magazine.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.magazine.entity.base.BaseDatabase;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.model.receive.CategoryReceiveModel;
import uz.pdp.magazine.model.response.base.BaseAdminResponse;
import uz.pdp.magazine.model.response.base.ResponseAdminStatus;
import uz.pdp.magazine.model.response.category.CategoryResponseModel;
import uz.pdp.magazine.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public BaseAdminResponse addCategory(
            CategoryReceiveModel categoryReceiveModel
    ) {
        try {
            CategoryDatabase categoryDatabase = new CategoryDatabase();
            categoryDatabase.setName(categoryReceiveModel.getName());
            categoryDatabase.setParentId(categoryReceiveModel.getParentId());
            categoryRepository.save(categoryDatabase);
            return ResponseAdminStatus.SUCCESS;
        } catch (Exception e) {
            return ResponseAdminStatus.UNKNOWN_ERROR;
        }
    }

    public List<CategoryResponseModel> getCategoryList() {
        List<CategoryResponseModel> list = new ArrayList<>();
        List<CategoryDatabase> categoryDatabaseList = categoryRepository.findAll();

        categoryDatabaseList
                .forEach((category -> {
                    CategoryResponseModel categoryResponseModel
                            = new CategoryResponseModel(
                            category.getId(),
                            category.getName(),
                            this.getCategoryName(categoryDatabaseList, category.getParentId())
                    );
                    list.add(categoryResponseModel);
                }));

        return list;
    }

    private String getCategoryName(
            List<CategoryDatabase> categoryDatabaseList,
            int parentId
    ) {
        Optional<CategoryDatabase> optionalCategoryDatabase = categoryDatabaseList
                .stream()
                .filter((category) -> {
                    return category.getId() == parentId;
                }).findFirst();

        return optionalCategoryDatabase.map(BaseDatabase::getName).orElse(null);
    }

    public List<CategoryDatabase> getSubCategories(
            Integer categoryId
    ) {
        return categoryRepository.findAll();
    }

    public List<CategoryDatabase> getSubCategories1() {
        return categoryRepository.findAllByParentId(0);
    }

    public List<CategoryDatabase> getCategoryByParent(Integer parentId) {
        List<CategoryDatabase> all = categoryRepository.findAll();
        List<CategoryDatabase>        allByParentId = all.stream().filter(categoryDatabase -> categoryDatabase.getParentId() == parentId).collect(Collectors.toList());
        if (allByParentId.size() == 0) {
            CategoryDatabase categoryDatabase1 = all.stream().filter(categoryDatabase -> categoryDatabase.getId() == parentId).findFirst().get();
            allByParentId = all.stream().filter(categoryDatabase -> categoryDatabase.getParentId() == categoryDatabase1.getParentId()).collect(Collectors.toList());
        }
        return allByParentId;
    }


}
