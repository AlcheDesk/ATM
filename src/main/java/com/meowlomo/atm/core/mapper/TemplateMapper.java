package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Template;
import com.meowlomo.atm.core.model.TemplateExample;

public interface TemplateMapper {
    long countByExample(TemplateExample example);

    int deleteByExample(TemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Template record);

    int insertSelective(@Param("record") Template record, @Param("selective") Template.Column... selective);

    int logicalDeleteByExample(@Param("example") TemplateExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Template> selectByExample(TemplateExample example);

    List<Template> selectByExampleSelective(@Param("example") TemplateExample example, @Param("selective") Template.Column... selective);

    List<Template> selectByExampleWithRowbounds(TemplateExample example, RowBounds rowBounds);

    Template selectByPrimaryKey(Long id);

    Template selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Template.Column... selective);

    Template selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    Template selectOneByExample(TemplateExample example);

    Template selectOneByExampleSelective(@Param("example") TemplateExample example, @Param("selective") Template.Column... selective);

    int updateByExample(@Param("record") Template record, @Param("example") TemplateExample example);

    int updateByExampleSelective(@Param("record") Template record, @Param("example") TemplateExample example, @Param("selective") Template.Column... selective);

    int updateByPrimaryKey(Template record);

    int updateByPrimaryKeySelective(@Param("record") Template record, @Param("selective") Template.Column... selective);
}