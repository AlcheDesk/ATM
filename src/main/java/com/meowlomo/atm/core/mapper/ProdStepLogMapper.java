package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.StepLog;
import com.meowlomo.atm.core.model.StepLogExample;

public interface ProdStepLogMapper {
    long countByExample(StepLogExample example);

    int deleteByExample(StepLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(StepLog record);

    int insertSelective(@Param("record") StepLog record, @Param("selective") StepLog.Column... selective);

    List<StepLog> selectByExample(StepLogExample example);

    List<StepLog> selectByExampleSelective(@Param("example") StepLogExample example, @Param("selective") StepLog.Column... selective);

    List<StepLog> selectByExampleWithRowbounds(StepLogExample example, RowBounds rowBounds);

    StepLog selectByPrimaryKey(Long id);

    StepLog selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") StepLog.Column... selective);

    StepLog selectOneByExample(StepLogExample example);

    StepLog selectOneByExampleSelective(@Param("example") StepLogExample example, @Param("selective") StepLog.Column... selective);

    int updateByExample(@Param("record") StepLog record, @Param("example") StepLogExample example);

    int updateByExampleSelective(@Param("record") StepLog record, @Param("example") StepLogExample example, @Param("selective") StepLog.Column... selective);

    int updateByPrimaryKey(StepLog record);

    int updateByPrimaryKeySelective(@Param("record") StepLog record, @Param("selective") StepLog.Column... selective);
}