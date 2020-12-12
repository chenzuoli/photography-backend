package pet.petcage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pet.petcage.dao.DamageTypeRepository;
import pet.petcage.entity.DamageType;

import java.util.List;

/**
 * Created by user chenzuoli on 2020/3/25 11:32
 * description: 损坏类型服务
 */
@Service
public class DamageTypeService extends BaseService<DamageType> {
    @Autowired
    DamageTypeRepository damageTypeRepository;

    @Override
    public DamageType getById(String id) {
        return null;
    }

    public List<DamageType> getDamageType() {
        return damageTypeRepository.getDamageType();
    }

}
