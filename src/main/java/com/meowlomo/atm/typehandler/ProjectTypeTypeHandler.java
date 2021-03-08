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
public class ProjectTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long typeId = RuntimeVariables.getProjectTypeToIdMap().get(parameter);
        if (typeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, typeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long typeId = rs.getLong(columnName);
        if (typeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToProjectTypeMap().get(typeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long typeId = rs.getLong(columnIndex);
        if (typeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToProjectTypeMap().get(typeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long typeId = cs.getLong(columnIndex);
        if (typeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToProjectTypeMap().get(typeId);
        }
    }

}
