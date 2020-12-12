package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.UserPet;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/8 15:55
 * description: 用户宠物表工厂
 */
@Repository
public interface UserPetRepository extends JpaRepository<UserPet, String> {

    @Transactional
    @Modifying
    @Query(value = "update user_pet set contact = ?1, pet_type = ?2, variety = ?3, nick_name = ?4, gender = ?5, birthday = ?6, avatar_url = ?7, description = ?8, update_time = ?9 where id = ?10", nativeQuery = true)
    int updateUserPet(String contact, String pet_type, String variety, String nick_name, String gender, String birthday, String avatar_url, String description, String update_time, String id);

    @Query(value = "select * from user_pet where open_id = ?1", nativeQuery = true)
    List<UserPet> getUserPets(String open_id);

    @Query(value = "select * from user_pet where id = ?1", nativeQuery = true)
    UserPet getPetInfo(String id);

    @Transactional
    @Modifying
    @Query(value = "insert into user_pet(open_id, contact, pet_type, variety, nick_name, gender, birthday, avatar_url, description) values(?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
    int addUserPet(String open_id, String contact, String pet_type, String variety, String nick_name, String gender, String birthday, String avatar_url, String description);

    @Transactional
    @Modifying
    @Query(value = "delete from user_pet where id = ?1", nativeQuery = true)
    int deleteUserPet(String id);
}
