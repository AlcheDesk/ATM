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
public class SystemRequirementTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long systemRequirementTypeId = RuntimeVariables.getSystemRequirementTypeToIdMap().get(parameter);
        if (systemRequirementTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, systemRequirementTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long systemRequirementTypeId = rs.getLong(columnName);
        if (systemRequirementTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToSystemRequirementTypeMap().get(systemRequirementTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long systemRequirementTypeId = rs.getLong(columnIndex);
        if (systemRequirementTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToSystemRequirementTypeMap().get(systemRequirementTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long systemRequirementTypeId = cs.getLong(columnIndex);
        if (systemRequirementTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToSystemRequirementTypeMap().get(systemRequirementTypeId);
        }
    }

}
