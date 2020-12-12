package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.PetcageOrder;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/1 17:17
 * description: 订单仓库
 */
@Repository
public interface PetcageOrderRepository extends JpaRepository<PetcageOrder, String> {

    @Query(value = "select * from petcage_order where phone = ?1 and is_done = false", nativeQuery = true)
    List<PetcageOrder> getPetcageOrderByPhone(String phone);

    @Query(value = "select * from petcage_order where open_id = ?1 and is_done = false", nativeQuery = true)
    List<PetcageOrder> getPetcageOrderByOpenId(String open_id);

    @Transactional
    @Modifying
    @Query(value = "insert into petcage_order(order_id, phone, open_id, is_done, device_id, pet_id, start_time) values(?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
    int add_order(String order_id, String phone, String open_id, boolean is_done, String device_id, String pet_id, String start_time);

    @Transactional
    @Modifying
    @Query(value = "update petcage_order set is_done = true, end_time = ?1, amount = ?2, update_time = ?3 where open_id = ?4 and order_id = ?5", nativeQuery = true)
    int close_order(String end_time, String amount, String update_time, String open_id, String order_id);

    @Query(value = "select * from petcage_order where open_id = ?1", nativeQuery = true)
    List<PetcageOrder> getPetcageOrder(String open_id);

    @Query(value = "select * from petcage_order where device_id = ?1 and is_done = false", nativeQuery = true)
    List<PetcageOrder> getPetcageStatus(String device_id);

}
