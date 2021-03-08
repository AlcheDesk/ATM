package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Tag;
import com.meowlomo.atm.core.model.TagExample;

public interface TagReferenceService {

    long countByExample(TagExample example);

    List<Tag> selectByExample(TagExample example);

    List<Tag> selectByExampleWithRowbounds(TagExample example, RowBounds rowBounds);

    Tag selectByPrimaryKey(Long id);
}
