package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Template;
import com.meowlomo.atm.core.model.TemplateExample;

public interface TemplateService {
    long countByExample(TemplateExample example);

    int deleteByExample(TemplateExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Template record);

    List<Long> insertRecords(List<Template> records);

    List<Long> insertRecordsSelective(List<Template> records);

    int insertSelective(Template record);

    int logicalDeleteByExample(TemplateExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<Template> selectByExample(TemplateExample example);

    List<Template> selectByExampleWithRowbounds(TemplateExample example, RowBounds rowBounds);

    Template selectByPrimaryKey(Long id);

    Template selectOneByExample(TemplateExample example);

    int updateByExample(Template record, TemplateExample example);

    int updateByExampleSelective(Template record, TemplateExample example);

    int updateByPrimaryKey(Template record);

    int updateByPrimaryKeySelective(Template record);
}