package pet.petcage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pet.petcage.entity.PetcageBook;

import javax.transaction.Transactional;

/**
 * Created by user chenzuoli on 2020/4/11 16:16
 * description: 宠笼预定工厂
 */
@Repository
public interface PetcageBookRepository extends JpaRepository<PetcageBook, String> {
    @Transactional
    @Modifying
    @Query(value = "insert into petcage_book(open_id, device_id, book_start_time, book_end_time, line_index) values(?1,?2,?3,?4,?5)", nativeQuery = true)
    int addBook(String open_id, String device_id, String book_start_time, String book_end_time, String line_index);

    @Query(value = "select * from petcage_book where device_id = ?1 order by create_time desc limit 1", nativeQuery = true)
    PetcageBook getLineIndex(String device_id);
}
