package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.model.SectionExample;

public interface SectionReferenceMapper {

    long countByExample(SectionExample example);

    List<Section> selectByExample(SectionExample example);

    List<Section> selectByExampleSelective(@Param("example") SectionExample example, @Param("selective") Section.Column... selective);

    List<Section> selectByExampleWithRowbounds(SectionExample example, RowBounds rowBounds);

    Section selectByPrimaryKey(Long id);

    Section selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Section.Column... selective);

    Section selectOneByExample(SectionExample example);

    Section selectOneByExampleSelective(@Param("example") SectionExample example, @Param("selective") Section.Column... selective);
}