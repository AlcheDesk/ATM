package com.meowlomo.atm.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import com.meowlomo.atm.config.RuntimeVariables;

@MappedJdbcTypes(JdbcType.OTHER)
public class LevelTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long groupId = RuntimeVariables.getGroupToIdMap().get(parameter);
        if (groupId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, groupId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long statusId = rs.getLong(columnName);
        if (statusId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToGroupMap().get(statusId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long statusId = rs.getLong(columnIndex);
        if (statusId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToGroupMap().get(statusId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long statusId = cs.getLong(columnIndex);
        if (statusId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToGroupMap().get(statusId);
        }
    }

}
