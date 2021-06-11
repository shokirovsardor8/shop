package uz.pdp.magazine.service.product;

import org.springframework.stereotype.Service;
import uz.pdp.magazine.entity.attachment.AttachmentDatabase;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.entity.product.ProductDatabase;
import uz.pdp.magazine.model.receive.ProductReceiveModel;
import uz.pdp.magazine.model.response.base.BaseAdminResponse;
import uz.pdp.magazine.model.response.base.ResponseAdminStatus;
import uz.pdp.magazine.model.response.product.ProductResponseModel;
import uz.pdp.magazine.repository.CategoryRepository;
import uz.pdp.magazine.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public BaseAdminResponse addProduct(
            ProductReceiveModel productReceiveModel
    ) {
        try {
            Optional<CategoryDatabase> optionalCategoryDatabase
                    = categoryRepository.findById(productReceiveModel.getCategoryId());
            if (optionalCategoryDatabase.isEmpty())
                return ResponseAdminStatus.CATEGORY_NOT_FOUND;

            ProductDatabase productDatabase = new ProductDatabase();
            productDatabase.setName(productReceiveModel.getName());
            productDatabase.setQuantity(productReceiveModel.getQuantity());
            productDatabase.setPrice(productReceiveModel.getPrice());
            productDatabase.setCategoryDatabase(optionalCategoryDatabase.get());
            productDatabase.setParams(productReceiveModel.getParams());

            productRepository.save(productDatabase);
            return ResponseAdminStatus.SUCCESS;
        } catch (Exception e) {
            return ResponseAdminStatus.UNKNOWN_ERROR;
        }
    }

    public List<ProductResponseModel> getProductList() {
        List<ProductResponseModel> list = new ArrayList<>();
        List<ProductDatabase> productDatabaseList = productRepository.findAll();
        productDatabaseList
                .forEach((product -> {
                    ProductResponseModel productResponseModel
                            = new ProductResponseModel(
                            product.getId(),
                            product.getName(),
                            product.getQuantity(),
                            product.getPrice(),
                            product.getCategoryDatabase().getName(),
                            product.getParams()
                    );
                    list.add(productResponseModel);
                }));

        return list;
    }

    public BaseAdminResponse addAttachment(
            AttachmentDatabase attachmentDatabase,
            Integer productId
    ) {

        if (attachmentDatabase == null)
            return ResponseAdminStatus.UNKNOWN_ERROR;

        Optional<ProductDatabase> optionalProductDatabase
                = productRepository.findById(productId);

        if (optionalProductDatabase.isEmpty())
            return ResponseAdminStatus.PRODUCT_NOT_FOUND;

        ProductDatabase productDatabase = optionalProductDatabase.get();

        List<AttachmentDatabase> attachmentDatabases
                = productDatabase.getAttachmentDatabases();

        attachmentDatabases.add(attachmentDatabase);

        productRepository.save(productDatabase);

        return ResponseAdminStatus.SUCCESS;

    }

    public List<ProductDatabase> getProductByCategory(Integer categoryId) {
        List<ProductDatabase> productDatabaseList = productRepository.findAll();
        return productDatabaseList.stream().filter(productDatabase -> productDatabase.getCategoryDatabase().getId() == categoryId).collect(Collectors.toList());
    }
}
