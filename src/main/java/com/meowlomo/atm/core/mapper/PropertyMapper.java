package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Property;
import com.meowlomo.atm.core.model.PropertyExample;

public interface PropertyMapper {
    long countByExample(PropertyExample example);

    int deleteByExample(PropertyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Property record);

    int insertSelective(@Param("record") Property record, @Param("selective") Property.Column... selective);

    List<Property> selectByExample(PropertyExample example);

    List<Property> selectByExampleSelective(@Param("example") PropertyExample example, @Param("selective") Property.Column... selective);

    List<Property> selectByExampleWithRowbounds(PropertyExample example, RowBounds rowBounds);

    Property selectByPrimaryKey(Long id);

    Property selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Property.Column... selective);

    Property selectOneByExample(PropertyExample example);

    Property selectOneByExampleSelective(@Param("example") PropertyExample example, @Param("selective") Property.Column... selective);

    int updateByExample(@Param("record") Property record, @Param("example") PropertyExample example);

    int updateByExampleSelective(@Param("record") Property record, @Param("example") PropertyExample example, @Param("selective") Property.Column... selective);

    int updateByPrimaryKey(Property record);

    int updateByPrimaryKeySelective(@Param("record") Property record, @Param("selective") Property.Column... selective);
}