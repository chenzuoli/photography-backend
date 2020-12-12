package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.PetcageBookRepository;
import pet.petcage.entity.PetcageBook;

/**
 * Created by user chenzuoli on 2020/4/11 16:20
 * description: 宠笼预定服务表
 */
@Service
public class PetcageBookService extends BaseService<PetcageBook> {
    @Autowired
    PetcageBookRepository petcageBookRepository;

    @Override
    public PetcageBook getById(String id) {
        return null;
    }

    public int addBook(String open_id, String device_id, String book_start_time, String book_end_time, String line_index) {
        System.out.println("add book params: " + open_id + "," + device_id + "," + book_start_time + "," + book_end_time + "," + line_index);
        return petcageBookRepository.addBook(open_id, device_id, book_start_time, book_end_time, line_index);
    }

    public PetcageBook getLineIndex(String device_id) {
        System.out.println("get line index params: " + device_id);
        return petcageBookRepository.getLineIndex(device_id);
    }

}
