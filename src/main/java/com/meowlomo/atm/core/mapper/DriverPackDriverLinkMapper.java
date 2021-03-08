package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.DriverPackDriverLink;
import com.meowlomo.atm.core.model.DriverPackDriverLinkExample;

public interface DriverPackDriverLinkMapper {
    long countByExample(DriverPackDriverLinkExample example);

    int deleteByExample(DriverPackDriverLinkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DriverPackDriverLink record);

    int insertSelective(@Param("record") DriverPackDriverLink record, @Param("selective") DriverPackDriverLink.Column... selective);

    List<DriverPackDriverLink> selectByExample(DriverPackDriverLinkExample example);

    List<DriverPackDriverLink> selectByExampleSelective(@Param("example") DriverPackDriverLinkExample example, @Param("selective") DriverPackDriverLink.Column... selective);

    List<DriverPackDriverLink> selectByExampleWithRowbounds(DriverPackDriverLinkExample example, RowBounds rowBounds);

    DriverPackDriverLink selectByPrimaryKey(Long id);

    DriverPackDriverLink selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") DriverPackDriverLink.Column... selective);

    DriverPackDriverLink selectOneByExample(DriverPackDriverLinkExample example);

    DriverPackDriverLink selectOneByExampleSelective(@Param("example") DriverPackDriverLinkExample example, @Param("selective") DriverPackDriverLink.Column... selective);

    int updateByExample(@Param("record") DriverPackDriverLink record, @Param("example") DriverPackDriverLinkExample example);

    int updateByExampleSelective(@Param("record") DriverPackDriverLink record, @Param("example") DriverPackDriverLinkExample example,
            @Param("selective") DriverPackDriverLink.Column... selective);

    int updateByPrimaryKey(DriverPackDriverLink record);

    int updateByPrimaryKeySelective(@Param("record") DriverPackDriverLink record, @Param("selective") DriverPackDriverLink.Column... selective);
}