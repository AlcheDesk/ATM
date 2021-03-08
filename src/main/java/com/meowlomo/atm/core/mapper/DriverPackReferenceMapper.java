package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPack;
import com.meowlomo.atm.core.model.DriverPackExample;

public interface DriverPackReferenceMapper {

    long countByExample(DriverPackExample example);

    List<DriverPack> selectByExample(DriverPackExample example);

    List<DriverPack> selectByExampleSelective(@Param("example") DriverPackExample example, @Param("selective") DriverPack.Column... selective);

    List<DriverPack> selectByExampleWithRowbounds(DriverPackExample example, RowBounds rowBounds);

    DriverPack selectByPrimaryKey(Long id);

    DriverPack selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverPack.Column... selective);

    DriverPack selectOneByExample(DriverPackExample example);

    DriverPack selectOneByExampleSelective(@Param("example") DriverPackExample example, @Param("selective") DriverPack.Column... selective);
}