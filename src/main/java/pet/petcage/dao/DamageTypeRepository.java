package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.DamageType;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/3/25 11:33
 * description:
 */
@Repository
public interface DamageTypeRepository extends JpaRepository<DamageType, String> {

    @Query(value = "select * from damage_type", nativeQuery = true)
    List<DamageType> getDamageType();

}
