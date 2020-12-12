package pet.petcage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pet.petcage.entity.PetcageOrder;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/4/4 09:43
 * description: 订单DTO传输对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {
    private List<PetcageOrder> petcageorder;
}
