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
public class RunSetTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long runSetTypeId = RuntimeVariables.getRunSetTypeToIdMap().get(parameter);
        if (runSetTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, runSetTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long runSetTypeId = rs.getLong(columnName);
        if (runSetTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToRunSetTypeMap().get(runSetTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long runSetTypeId = rs.getLong(columnIndex);
        if (runSetTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToRunSetTypeMap().get(runSetTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long runSetTypeId = cs.getLong(columnIndex);
        if (runSetTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToRunSetTypeMap().get(runSetTypeId);
        }
    }

}
