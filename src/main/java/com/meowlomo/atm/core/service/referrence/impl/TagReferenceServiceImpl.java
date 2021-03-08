package com.meowlomo.atm.core.service.referrence.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.meowlomo.atm.cache.CacheKeyGenerator;
import com.meowlomo.atm.cache.RedisService;
import com.meowlomo.atm.core.mapper.TagMapper;
import com.meowlomo.atm.core.mapper.TagReferenceMapper;
import com.meowlomo.atm.core.model.Tag;
import com.meowlomo.atm.core.model.TagExample;
import com.meowlomo.atm.core.service.referrence.TagReferenceService;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TagReferenceServiceImpl implements TagReferenceService {

    @Autowired
    private CacheKeyGenerator cacheKeyGenerator;
    @Autowired
    private RedisService<Tag> redisService;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TagReferenceMapper tagReferenceMapper;

    @Override
    public long countByExample(TagExample example) {
        return tagMapper.countByExample(example);
    }

    @Override
    public List<Tag> selectByExample(TagExample example) {
        List<Tag> selectResult = tagReferenceMapper.selectByExample(example);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Tag.class, example, "reference"), selectResult);
        }
        return selectResult;
    }

    @Override
    public List<Tag> selectByExampleWithRowbounds(TagExample example, RowBounds rowBounds) {
        List<Tag> selectResult = tagReferenceMapper.selectByExampleWithRowbounds(example, rowBounds);
        if (!selectResult.isEmpty()) {
            redisService.putList(cacheKeyGenerator.generateKey(Tag.class, example, rowBounds, "reference"),
                    selectResult);
        }
        return selectResult;
    }

    @Override
    public Tag selectByPrimaryKey(Long id) {
        Tag selectResult = tagReferenceMapper.selectByPrimaryKey(id);
        if (selectResult != null) {
            redisService.putValue(cacheKeyGenerator.generateKey(Tag.class, id, "reference"), selectResult);
        }
        return selectResult;
    }

}
