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
public class LogLevelTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long logLevelId = RuntimeVariables.getLogLevelToIdMap().get(parameter);
        if (logLevelId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, logLevelId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long logLevelId = rs.getLong(columnName);
        if (logLevelId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToLogLevelMap().get(logLevelId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long logLevelId = rs.getLong(columnIndex);
        if (logLevelId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToLogLevelMap().get(logLevelId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long logLevelId = cs.getLong(columnIndex);
        if (logLevelId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToLogLevelMap().get(logLevelId);
        }
    }

}
