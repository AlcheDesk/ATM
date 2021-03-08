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
public class FileTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long groupId = RuntimeVariables.getFileTypeToIdMap().get(parameter);
        if (groupId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, groupId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long fileTypeId = rs.getLong(columnName);
        if (fileTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToFileTypeMap().get(fileTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long fileTypeId = rs.getLong(columnIndex);
        if (fileTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToFileTypeMap().get(fileTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long fileTypeId = cs.getLong(columnIndex);
        if (fileTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToFileTypeMap().get(fileTypeId);
        }
    }

}
