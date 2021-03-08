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
public class RunTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long runTypeId = RuntimeVariables.getRunTypeToIdMap().get(parameter);
        if (runTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, runTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long runTypeId = rs.getLong(columnName);
        if (runTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToRunTypeMap().get(runTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long runTypeId = rs.getLong(columnIndex);
        if (runTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToRunTypeMap().get(runTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long runTypeId = cs.getLong(columnIndex);
        if (runTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToRunTypeMap().get(runTypeId);
        }
    }

}
