package com.meowlomo.atm.core.mapper;

import com.meowlomo.atm.core.model.Section;
import com.meowlomo.atm.core.model.SectionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SectionMapper {
    long countByExample(SectionExample example);

    int deleteByExample(SectionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Section record);

    int insertSelective(@Param("record") Section record, @Param("selective") Section.Column... selective);

    int logicalDeleteByExample(@Param("example") SectionExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Section> selectByExample(SectionExample example);

    List<Section> selectByExampleSelective(@Param("example") SectionExample example, @Param("selective") Section.Column... selective);

    List<Section> selectByExampleWithRowbounds(SectionExample example, RowBounds rowBounds);

    Section selectByPrimaryKey(Long id);

    Section selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Section.Column... selective);

    Section selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Section selectOneByExample(SectionExample example);

    Section selectOneByExampleSelective(@Param("example") SectionExample example, @Param("selective") Section.Column... selective);

    int updateByExample(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByExampleSelective(@Param("record") Section record, @Param("example") SectionExample example, @Param("selective") Section.Column... selective);

    int updateByPrimaryKey(Section record);

    int updateByPrimaryKeySelective(@Param("record") Section record, @Param("selective") Section.Column... selective);
}