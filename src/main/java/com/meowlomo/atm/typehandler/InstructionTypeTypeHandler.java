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
public class InstructionTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long id = RuntimeVariables.getInstructionTypeToIdMap().get(parameter);
        if (id == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, id);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long id = rs.getLong(columnName);
        if (id == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToInstructionTypeMap().get(id);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long id = rs.getLong(columnIndex);
        if (id == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToInstructionTypeMap().get(id);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long id = cs.getLong(columnIndex);
        if (id == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToInstructionTypeMap().get(id);
        }
    }

}
