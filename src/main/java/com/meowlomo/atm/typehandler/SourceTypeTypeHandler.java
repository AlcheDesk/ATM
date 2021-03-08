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
public class SourceTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long sourceTypeId = RuntimeVariables.getSourceTypeToIdMap().get(parameter);
        if (sourceTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, sourceTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long sourceTypeId = rs.getLong(columnName);
        if (sourceTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToSourceTypeMap().get(sourceTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long sourceTypeId = rs.getLong(columnIndex);
        if (sourceTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToSourceTypeMap().get(sourceTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long sourceTypeId = cs.getLong(columnIndex);
        if (sourceTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToSourceTypeMap().get(sourceTypeId);
        }
    }

}
