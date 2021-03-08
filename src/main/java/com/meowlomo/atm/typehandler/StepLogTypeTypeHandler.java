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
public class StepLogTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long groupId = RuntimeVariables.getStepLogTypeToIdMap().get(parameter);
        if (groupId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, groupId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long stepLogTypeId = rs.getLong(columnName);
        if (stepLogTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToStepLogTypeMap().get(stepLogTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long stepLogTypeId = rs.getLong(columnIndex);
        if (stepLogTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToStepLogTypeMap().get(stepLogTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long stepLogTypeId = cs.getLong(columnIndex);
        if (stepLogTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToStepLogTypeMap().get(stepLogTypeId);
        }
    }

}
