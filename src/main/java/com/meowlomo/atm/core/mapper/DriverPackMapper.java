package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackExample;

public interface DriverPackMapper {
    long countByExample(DriverPackExample example);

    int deleteByExample(DriverPackExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPack record);

    int insertSelective(@Param("record") DriverPack record, @Param("selective") DriverPack.Column... selective);

    int logicalDeleteByExample(@Param("example") DriverPackExample example);

    int logicalDeleteByPrimaryKey(Long id);

    List<DriverPack> selectByExample(DriverPackExample example);

    List<DriverPack> selectByExampleSelective(@Param("example") DriverPackExample example, @Param("selective") DriverPack.Column... selective);

    List<DriverPack> selectByExampleWithRowbounds(DriverPackExample example, RowBounds rowBounds);

    DriverPack selectByPrimaryKey(Long id);

    DriverPack selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverPack.Column... selective);

    DriverPack selectByPrimaryKeyWithLogicalDelete(@Param("id") Long id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    DriverPack selectOneByExample(DriverPackExample example);

    DriverPack selectOneByExampleSelective(@Param("example") DriverPackExample example, @Param("selective") DriverPack.Column... selective);

    int updateByExample(@Param("record") DriverPack record, @Param("example") DriverPackExample example);

    int updateByExampleSelective(@Param("record") DriverPack record, @Param("example") DriverPackExample example, @Param("selective") DriverPack.Column... selective);

    int updateByPrimaryKey(DriverPack record);

    int updateByPrimaryKeySelective(@Param("record") DriverPack record, @Param("selective") DriverPack.Column... selective);
}