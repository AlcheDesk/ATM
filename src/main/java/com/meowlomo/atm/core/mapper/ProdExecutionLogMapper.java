package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.ExecutionLog;
import com.meowlomo.atm.core.model.ExecutionLogExample;

public interface ProdExecutionLogMapper {
    long countByExample(ExecutionLogExample example);

    int deleteByExample(ExecutionLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExecutionLog record);

    int insertSelective(@Param("record") ExecutionLog record, @Param("selective") ExecutionLog.Column... selective);

    List<ExecutionLog> selectByExample(ExecutionLogExample example);

    List<ExecutionLog> selectByExampleSelective(@Param("example") ExecutionLogExample example, @Param("selective") ExecutionLog.Column... selective);

    List<ExecutionLog> selectByExampleWithRowbounds(ExecutionLogExample example, RowBounds rowBounds);

    ExecutionLog selectByPrimaryKey(Long id);

    ExecutionLog selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ExecutionLog.Column... selective);

    ExecutionLog selectOneByExample(ExecutionLogExample example);

    ExecutionLog selectOneByExampleSelective(@Param("example") ExecutionLogExample example, @Param("selective") ExecutionLog.Column... selective);

    int updateByExample(@Param("record") ExecutionLog record, @Param("example") ExecutionLogExample example);

    int updateByExampleSelective(@Param("record") ExecutionLog record, @Param("example") ExecutionLogExample example, @Param("selective") ExecutionLog.Column... selective);

    int updateByPrimaryKey(ExecutionLog record);

    int updateByPrimaryKeySelective(@Param("record") ExecutionLog record, @Param("selective") ExecutionLog.Column... selective);
}