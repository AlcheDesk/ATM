package com.meowlomo.atm.typehandler;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.meowlomo.atm.config.RuntimeVariables;

public class SystemRequirementTypesTypeHandler extends BaseTypeHandler<Set<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<String> parameter, JdbcType jdbcType)
            throws SQLException {
        Array array = ps.getConnection().createArrayOf("bigint",
                this.systemRequirementTypeStringDataToIdArray(parameter));
        try {
            ps.setArray(i, array);
        }
        finally {
            array.free();
        }
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.sqlArrayToStringArray(rs.getArray(columnName));
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.sqlArrayToStringArray(rs.getArray(columnIndex));
    }

    @Override
    public Set<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.sqlArrayToStringArray(cs.getArray(columnIndex));
    }

    private Set<String> sqlArrayToStringArray(Array sqlArray) throws SQLException {
        if (sqlArray == null) {
            return null;
        }
        else {
            try {
                Set<String> retrunList = new HashSet<String>();
                Long[] data = (Long[]) sqlArray.getArray();
                for (Long typeId : data) {
                    retrunList.add(RuntimeVariables.getIdToSystemRequirementTypeMap().get(typeId));
                }
                return retrunList;
            }
            catch (SQLException e) {
                return null;
            }
            finally {
                sqlArray.free();
            }
        }
    }

    private Object[] systemRequirementTypeStringDataToIdArray(Set<String> systemRequirementTypes) {
        Object[] returnValue = {};
        if (systemRequirementTypes == null || systemRequirementTypes.isEmpty()) {
            return returnValue;
        }
        else {
            List<Object> retrunList = new ArrayList<Object>();
            for (String systemRequirementType : systemRequirementTypes) {
                retrunList.add(RuntimeVariables.getSystemRequirementTypeToIdMap().get(systemRequirementType));
            }
            return retrunList.toArray();
        }
    }
}
