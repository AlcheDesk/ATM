package com.meowlomo.atm.core.service.base;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.Color;
import com.meowlomo.atm.core.model.ColorExample;

public interface ColorService {
    long countByExample(ColorExample example);

    int deleteByExample(ColorExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Color record);

    List<Long> insertRecords(List<Color> records);

    List<Long> insertRecordsSelective(List<Color> records);

    int insertSelective(Color record);

    List<Color> selectByExample(ColorExample example);

    List<Color> selectByExampleWithRowbounds(ColorExample example, RowBounds rowBounds);

    Color selectByPrimaryKey(Long id);

    Color selectOneByExample(ColorExample example);

    int updateByExample(Color record, ColorExample example);

    int updateByExampleSelective(Color record, ColorExample example);

    int updateByPrimaryKey(Color record);

    int updateByPrimaryKeySelective(Color record);
}