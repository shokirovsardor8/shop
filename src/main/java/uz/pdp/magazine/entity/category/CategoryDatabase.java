package uz.pdp.magazine.entity.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.magazine.entity.base.BaseDatabase;
import uz.pdp.magazine.entity.product.ProductDatabase;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CategoryDatabase extends BaseDatabase {

    @JsonProperty("parent_id") // TODO connect parentId as foreign key #Bobur aka
    private int parentId;

    @OneToMany(mappedBy = "categoryDatabase")
    private List<ProductDatabase> productDatabases;

}
