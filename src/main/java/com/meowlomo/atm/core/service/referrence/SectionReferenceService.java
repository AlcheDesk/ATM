package com.meowlomo.atm.core.service.referrence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.model.SectionExample;

public interface SectionReferenceService {

    long countByExample(SectionExample example);

    List<Section> selectByExample(SectionExample example);

    List<Section> selectByExampleWithRowbounds(SectionExample example, RowBounds rowBounds);

    Section selectByPrimaryKey(Long id);
}
