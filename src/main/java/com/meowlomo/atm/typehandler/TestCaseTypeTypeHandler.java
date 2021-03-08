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
public class TestCaseTypeTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long testCaseTypeId = RuntimeVariables.getTestCaseTypeToIdMap().get(parameter);
        if (testCaseTypeId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, testCaseTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long testCaseTypeId = rs.getLong(columnName);
        if (testCaseTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToTestCaseTypeMap().get(testCaseTypeId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long testCaseTypeId = rs.getLong(columnIndex);
        if (testCaseTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToTestCaseTypeMap().get(testCaseTypeId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long testCaseTypeId = cs.getLong(columnIndex);
        if (testCaseTypeId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToTestCaseTypeMap().get(testCaseTypeId);
        }
    }

}
