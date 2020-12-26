package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.photography.entity.Photography;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/22 23:00
 * description: 摄影作品工厂类
 */
@Repository
public interface PhotographyRepository extends JpaRepository<Photography, String> {
    @Query(value = "select * from photography", nativeQuery = true)
    List<Photography> get_photogrphies();
}
