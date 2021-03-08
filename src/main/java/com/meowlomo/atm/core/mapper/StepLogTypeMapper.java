package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.StepLogType;
import com.meowlomo.atm.core.model.StepLogTypeExample;

public interface StepLogTypeMapper {
    long countByExample(StepLogTypeExample example);

    int deleteByExample(StepLogTypeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StepLogType record);

    int insertSelective(@Param("record") StepLogType record, @Param("selective") StepLogType.Column... selective);

    List<StepLogType> selectByExample(StepLogTypeExample example);

    List<StepLogType> selectByExampleSelective(@Param("example") StepLogTypeExample example, @Param("selective") StepLogType.Column... selective);

    List<StepLogType> selectByExampleWithRowbounds(StepLogTypeExample example, RowBounds rowBounds);

    StepLogType selectByPrimaryKey(Long id);

    StepLogType selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") StepLogType.Column... selective);

    StepLogType selectOneByExample(StepLogTypeExample example);

    StepLogType selectOneByExampleSelective(@Param("example") StepLogTypeExample example, @Param("selective") StepLogType.Column... selective);

    int updateByExample(@Param("record") StepLogType record, @Param("example") StepLogTypeExample example);

    int updateByExampleSelective(@Param("record") StepLogType record, @Param("example") StepLogTypeExample example, @Param("selective") StepLogType.Column... selective);

    int updateByPrimaryKey(StepLogType record);

    int updateByPrimaryKeySelective(@Param("record") StepLogType record, @Param("selective") StepLogType.Column... selective);
}