package uz.pdp.magazine.mvc.admin.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.magazine.entity.attachment.AttachmentDatabase;
import uz.pdp.magazine.entity.category.CategoryDatabase;
import uz.pdp.magazine.entity.product.ProductDatabase;
import uz.pdp.magazine.model.receive.ProductEdit;
import uz.pdp.magazine.model.receive.ProductReceiveModel;
import uz.pdp.magazine.model.response.base.BaseAdminResponse;
import uz.pdp.magazine.model.response.base.ResponseAdminStatus;
import uz.pdp.magazine.model.response.product.ProductResponseModel;
import uz.pdp.magazine.repository.ProductRepository;
import uz.pdp.magazine.service.attachment.AttachmentService;
import uz.pdp.magazine.service.category.CategoryService;
import uz.pdp.magazine.service.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/api/admin/product")
public class ProductAdminController {

    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final AttachmentService attachmentService;

    @Autowired
    public ProductAdminController(ProductService productService, ProductRepository productRepository, CategoryService categoryService, AttachmentService attachmentService) {
        this.productService = productService;
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.attachmentService = attachmentService;
    }

    @ResponseBody
    @PostMapping(value = "/add", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public BaseAdminResponse addProduct(
            @RequestBody ProductReceiveModel productReceiveModel
    ) {
        BaseAdminResponse baseAdminResponse = null;
        try {
            baseAdminResponse
                    = productService.addProduct(productReceiveModel);
        }catch (Exception e){
            baseAdminResponse
                    = ResponseAdminStatus.UNKNOWN_ERROR;
        }

        return baseAdminResponse;
    }

    @GetMapping("/list")
    public String getProduct(
            Model model
    ) {
        List<CategoryDatabase> categoryDatabaseList = categoryService.getSubCategories(null);
        List<ProductResponseModel> productList = productService.getProductList();
        model.addAttribute("productList", productList);
        model.addAttribute("product", new ProductReceiveModel());
        model.addAttribute("categoryList", categoryDatabaseList);
        return "admin/product/list";
    }

    @PostMapping("/attachment/add/{id}")
    public String addAttachment(
            @RequestParam("file") MultipartFile multipartFile,
            @PathVariable("id") Integer productId,
            Model model
    ) {
        AttachmentDatabase attachmentDatabase = attachmentService.saveFile(multipartFile);
        BaseAdminResponse adminResponse = productService.addAttachment(attachmentDatabase, productId);

        model.addAttribute("response", adminResponse);
        model.addAttribute("productList", productRepository.findAll());
        return "admin/product/list";
    }

    @GetMapping("/attachment/{id}")
    public String getId(
            @PathVariable("id") int productId,
            Model model
    ){
        ProductDatabase product=productRepository.getById(productId);
        ProductEdit productEdit=new ProductEdit();
        productEdit.setId(product.getId());
        productEdit.setName(product.getName());
        productEdit.setPrice(product.getPrice());
        productEdit.setParams(product.getParams());
        model.addAttribute("file",productEdit);
        return "admin/product/crudProduct";
    }


    @PostMapping("/edit")
    public String fake(
            @ModelAttribute("file")ProductEdit productEdit,
            Model model
    ){
        ProductDatabase product=productRepository.getById(productEdit.getId());
        product.setId(productEdit.getId());
        product.setName(productEdit.getName());
        product.setPrice(productEdit.getPrice());
        product.setParams(productEdit.getParams());
        productRepository.save(product);

        AttachmentDatabase attachmentDatabase = attachmentService.saveFile(productEdit.getMultipartFile());
        BaseAdminResponse adminResponse = productService.addAttachment(attachmentDatabase, productEdit.getId());

        model.addAttribute("response", adminResponse);
        List<ProductResponseModel> productList = productService.getProductList();
        model.addAttribute("productList", productList);
        return "admin/product/list";
    }


}
