package pet.photography.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.photography.entity.Tag;

import java.util.List;

/**
 * Created by user chenzuoli on 2021/5/22 20:19
 * description:
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    @Query(value = "select * from tags", nativeQuery = true)
    List<Tag> getTags();
}
