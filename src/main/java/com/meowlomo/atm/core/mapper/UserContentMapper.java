package com.meowlomo.atm.core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.meowlomo.atm.core.model.UserContent;
import com.meowlomo.atm.core.model.UserContentExample;

public interface UserContentMapper {
    long countByExample(UserContentExample example);

    int deleteByExample(UserContentExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserContent record);

    int insertSelective(@Param("record") UserContent record, @Param("selective") UserContent.Column... selective);

    List<UserContent> selectByExample(UserContentExample example);

    List<UserContent> selectByExampleSelective(@Param("example") UserContentExample example, @Param("selective") UserContent.Column... selective);

    List<UserContent> selectByExampleWithRowbounds(UserContentExample example, RowBounds rowBounds);

    UserContent selectByPrimaryKey(Long id);

    UserContent selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UserContent.Column... selective);

    UserContent selectOneByExample(UserContentExample example);

    UserContent selectOneByExampleSelective(@Param("example") UserContentExample example, @Param("selective") UserContent.Column... selective);

    int updateByExample(@Param("record") UserContent record, @Param("example") UserContentExample example);

    int updateByExampleSelective(@Param("record") UserContent record, @Param("example") UserContentExample example, @Param("selective") UserContent.Column... selective);

    int updateByPrimaryKey(UserContent record);

    int updateByPrimaryKeySelective(@Param("record") UserContent record, @Param("selective") UserContent.Column... selective);
}