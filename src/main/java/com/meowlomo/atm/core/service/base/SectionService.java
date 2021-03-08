package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.model.SectionExample;

public interface SectionService {
    long countByExample(SectionExample example);

    int deleteByExample(SectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Section record);

    List<Long> insertRecords(List<Section> records);

    List<Long> insertRecordsSelective(List<Section> records);

    int insertSelective(Section record);

    int logicalDeleteByExample(SectionExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Section> selectByExample(SectionExample example);

    List<Section> selectByExampleWithRowbounds(SectionExample example, RowBounds rowBounds);

    Section selectByPrimaryKey(Long id);

    Section selectOneByExample(SectionExample example);

    int updateByExample(Section record, SectionExample example);

    int updateByExampleSelective(Section record, SectionExample example);

    int updateByPrimaryKey(Section record);

    int updateByPrimaryKeySelective(Section record);
}