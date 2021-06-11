package uz.pdp.magazine.entity.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.magazine.entity.attachment.AttachmentDatabase;
import uz.pdp.magazine.entity.base.BaseDatabase;
import uz.pdp.magazine.entity.category.CategoryDatabase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProductDatabase extends BaseDatabase {

    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private BigDecimal price;
    private String params;

    @ManyToOne
    @JoinColumn(
            name = "category_id",
            nullable = false
    )
    private CategoryDatabase categoryDatabase; // category_database_id bb saqlanadi

    @OneToMany()
    private List<AttachmentDatabase> attachmentDatabases;
    // one directional bo'lgani un alohida tablitsa yaratadi


}
