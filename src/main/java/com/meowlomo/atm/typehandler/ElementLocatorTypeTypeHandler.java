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
public class ElementLocatorTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long elementLocatorTypeId = RuntimeVariables.getElementLocatorTypeToIdMap().get(parameter);
        if (elementLocatorTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, elementLocatorTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long elementLocatorTypeId = rs.getLong(columnName);
        if (elementLocatorTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToElementLocatorTypeMap().get(elementLocatorTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long elementLocatorTypeId = rs.getLong(columnIndex);
        if (elementLocatorTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToElementLocatorTypeMap().get(elementLocatorTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long elementLocatorTypeId = cs.getLong(columnIndex);
        if (elementLocatorTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToElementLocatorTypeMap().get(elementLocatorTypeId);
        }
    }
}