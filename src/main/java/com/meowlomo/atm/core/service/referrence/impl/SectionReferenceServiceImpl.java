package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.SectionMapper;
import com.meowlomo.atm.core.mapper.SectionReferenceMapper;
import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.model.SectionExample;
import com.meowlomo.atm.core.service.referrence.SectionReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class SectionReferenceServiceImpl implements SectionReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Section> redisService;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private SectionReferenceMapper sectionReferenceMapper;

    @Override
    public long countByExample(SectionExample example) {
        return sectionMapper.countByExample(example);
    }

    @Override
    public List<Section> selectByExample(SectionExample example) {
        List<Section> selectResult = sectionReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Section.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Section> selectByExampleWithRowbounds(SectionExample example, RowBounds rowBounds) {
        List<Section> selectResult = sectionReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Section.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Section selectByPrimaryKey(Long id) {
        Section selectResult = sectionReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Section.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
