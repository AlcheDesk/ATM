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
public class DriverTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long driverTypeId = RuntimeVariables.getDriverTypeToIdMap().get(parameter);
        if (driverTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, driverTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long driverTypeId = rs.getLong(columnName);
        if (driverTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToDriverTypeMap().get(driverTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long driverTypeId = rs.getLong(columnIndex);
        if (driverTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToDriverTypeMap().get(driverTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long driverTypeId = cs.getLong(columnIndex);
        if (driverTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToDriverTypeMap().get(driverTypeId);
        }
    }

}
