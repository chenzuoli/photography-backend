package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.photography.entity.Photography;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by user chenzuoli on 2020/12/22 23:00
 * description: 摄影作品工厂类
 */
@Repository
public interface PhotographyRepository extends JpaRepository<Photography, String> {
    @Query(value = "select * from photography order by create_time desc", nativeQuery = true)
    List<Photography> getPhotogrphies();

    @Transactional
    @Modifying
    @Query(value = "insert into photography(open_id, url, type, subject, nick_name, photographer, tag_id) values (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    int addPhotography(String open_id, String url, String type, String subject, String nick_name, String photographer, String tag_id);
}
