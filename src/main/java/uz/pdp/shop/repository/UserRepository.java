package uz.pdp.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.shop.entity.user.UserDatabase;

public interface UserRepository extends JpaRepository<UserDatabase,Integer> {

}
