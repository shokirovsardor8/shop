package uz.pdp.magazine.entity.attachment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.magazine.entity.attachment_content.AttachmentContentDatabase;
import uz.pdp.magazine.entity.base.BaseDatabase;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AttachmentDatabase extends BaseDatabase {

    @Column(name = "content_type")
    private String fileContentType;
    private Long size;

    @OneToOne
    @JoinColumn(name = "attachment_content_id")
    private AttachmentContentDatabase attachmentContentDatabase;
    // bu asosiy file ogriligini saqlab turadi

}
