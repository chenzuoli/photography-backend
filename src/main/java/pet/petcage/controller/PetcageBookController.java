package pet.petcage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pet.petcage.common.Constant;
import pet.petcage.dto.ResultDTO;
import pet.petcage.entity.PetcageBook;
import pet.petcage.service.PetcageBookService;

/**
 * Created by user chenzuoli on 2020/4/11 16:23
 * description: 宠笼预定控制器
 */
@RestController
public class PetcageBookController {
    @Autowired
    PetcageBookService petcageBookService;
    @Autowired
    Constant constant;

    /**
     * 添加宠笼预定
     *
     * @param open_id         用户open_id
     * @param device_id       设备id
     * @param book_start_time 预定开始时间
     * @param book_end_time   预定结束时间
     * @return ResultDTO
     */
    @RequestMapping(value = "/add_book", method = RequestMethod.POST)
    public ResultDTO addBook(@RequestParam("open_id") String open_id,
                             @RequestParam("device_id") String device_id,
                             @RequestParam("book_start_time") String book_start_time,
                             @RequestParam("book_end_time") String book_end_time) {
        ResultDTO lineIndex = getLineIndex(device_id);
        int result = petcageBookService.addBook(
                open_id,
                device_id,
                book_start_time,
                book_end_time,
                String.valueOf(Integer.parseInt(lineIndex.getData().toString()) + 1));
        if (result > 0) {
            return ResultDTO.ok(result);
        } else {
            return ResultDTO.fail("插入队列失败");
        }
    }

    /**
     * 获取改宠笼排队人数
     *
     * @param device_id 宠笼id
     * @return 宠笼排名名次ResultDTO
     */
    private ResultDTO getLineIndex(@RequestParam("device_id") String device_id) {
        PetcageBook petcageBook = petcageBookService.getLineIndex(device_id);
        if (petcageBook == null) {
            return ResultDTO.ok(0);
        } else {
            return ResultDTO.ok(petcageBook.getLine_index());
        }
    }

}
