package uz.pdp.magazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.magazine.entity.attachment_content.AttachmentContentDatabase;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContentDatabase,Integer> {
}
