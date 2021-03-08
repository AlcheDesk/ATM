package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Color;
import com.meowlomo.atm.core.model.ColorExample;

public interface ColorMapper {
    long countByExample(ColorExample example);

    int deleteByExample(ColorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Color record);

    int insertSelective(@Param("record") Color record, @Param("selective") Color.Column... selective);

    List<Color> selectByExample(ColorExample example);

    List<Color> selectByExampleSelective(@Param("example") ColorExample example, @Param("selective") Color.Column... selective);

    List<Color> selectByExampleWithRowbounds(ColorExample example, RowBounds rowBounds);

    Color selectByPrimaryKey(Long id);

    Color selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") Color.Column... selective);

    Color selectOneByExample(ColorExample example);

    Color selectOneByExampleSelective(@Param("example") ColorExample example, @Param("selective") Color.Column... selective);

    int updateByExample(@Param("record") Color record, @Param("example") ColorExample example);

    int updateByExampleSelective(@Param("record") Color record, @Param("example") ColorExample example, @Param("selective") Color.Column... selective);

    int updateByPrimaryKey(Color record);

    int updateByPrimaryKeySelective(@Param("record") Color record, @Param("selective") Color.Column... selective);
}