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
public class ElementTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long elementTypeId = RuntimeVariables.getElementTypeToIdMap().get(parameter);
        if (elementTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, elementTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long elementTypeId = rs.getLong(columnName);
        if (elementTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToElementTypeMap().get(elementTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long elementTypeId = rs.getLong(columnIndex);
        if (elementTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToElementTypeMap().get(elementTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long elementTypeId = cs.getLong(columnIndex);
        if (elementTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToElementTypeMap().get(elementTypeId);
        }
    }

}
