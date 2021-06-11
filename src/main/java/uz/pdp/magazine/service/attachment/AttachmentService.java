package uz.pdp.magazine.service.attachment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.magazine.entity.attachment.AttachmentDatabase;
import uz.pdp.magazine.entity.attachment_content.AttachmentContentDatabase;
import uz.pdp.magazine.repository.AttachmentContentRepository;
import uz.pdp.magazine.repository.AttachmentRepository;

@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @Autowired
    public AttachmentService(AttachmentRepository attachmentRepository, AttachmentContentRepository attachmentContentRepository) {
        this.attachmentRepository = attachmentRepository;
        this.attachmentContentRepository = attachmentContentRepository;
    }


    public AttachmentDatabase saveFile(
            MultipartFile multipartFile
    ){
        try {

            /*attachment contentni saqlash boshlandi*/

            byte[] bytes = multipartFile.getBytes();
            AttachmentContentDatabase attachmentContentDatabase
                    = new AttachmentContentDatabase();
            attachmentContentDatabase.setBytes(bytes);

            AttachmentContentDatabase savedAttachmentContentDatabase
                    = attachmentContentRepository.save(attachmentContentDatabase);

            /*attachment contentni saqlash tugadi*/

            /*attachment  saqlash boshlandi*/
            AttachmentDatabase attachmentDatabase = new AttachmentDatabase();
            attachmentDatabase.setAttachmentContentDatabase(savedAttachmentContentDatabase);
            attachmentDatabase.setSize(multipartFile.getSize());
            attachmentDatabase.setName(multipartFile.getOriginalFilename());
            attachmentDatabase.setFileContentType(multipartFile.getContentType());
            /*attachment saqlash tugadi*/
            return attachmentRepository.save(attachmentDatabase);

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
