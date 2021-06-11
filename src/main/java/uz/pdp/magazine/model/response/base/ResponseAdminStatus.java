package uz.pdp.magazine.model.response.base;

public interface ResponseAdminStatus {

    BaseAdminResponse SUCCESS
            = new BaseAdminResponse("jarayon muvaffaqiyatli bajarildi", 0, true);

    BaseAdminResponse UNKNOWN_ERROR
            = new BaseAdminResponse("nomalum xatolik yuz berdi", 111, true);

    BaseAdminResponse CATEGORY_NOT_FOUND
            = new BaseAdminResponse("kategoriya topilmadi", 100, false);

    BaseAdminResponse PRODUCT_NOT_FOUND
            = new BaseAdminResponse("mahsulot topilmadi", 101, false);
}
