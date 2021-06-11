package uz.pdp.magazine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.magazine.entity.attachment.AttachmentDatabase;

public interface AttachmentRepository extends JpaRepository<AttachmentDatabase, Integer> {

}
