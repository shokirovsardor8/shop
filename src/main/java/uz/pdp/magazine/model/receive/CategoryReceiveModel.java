package uz.pdp.magazine.model.receive;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryReceiveModel {

    private String name;

    @JsonProperty("parent_id")
    private int parentId;
}
