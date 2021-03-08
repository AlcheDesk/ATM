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
public class InstructionActionTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long jobTypeId = RuntimeVariables.getInstructionActionToIdMap().get(parameter);
        if (jobTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, jobTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long instructionActionId = rs.getLong(columnName);
        if (instructionActionId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long instructionActionId = rs.getLong(columnIndex);
        if (instructionActionId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long instructionActionId = cs.getLong(columnIndex);
        if (instructionActionId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToInstructionActionMap().get(instructionActionId);
        }
    }
}
