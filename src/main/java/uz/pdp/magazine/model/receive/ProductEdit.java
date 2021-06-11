package uz.pdp.magazine.model.receive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEdit {
    private Integer id;
    private String name;
    private int quantity;
    private BigDecimal price;
    private String params;
    private MultipartFile multipartFile;
}
