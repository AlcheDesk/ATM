package com.meowlomo.atm.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

@MappedTypes({ UUID.class })
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType)
            throws SQLException {
        if(parameter == null) {
            ps.setObject(i, null); 
        }
        else {
            String json = parameter.toString();
            PGobject jsonObject = new PGobject();
            jsonObject.setType("uuid");
            jsonObject.setValue(json);
            ps.setObject(i, jsonObject); 
        }
    }

    @Override
    public UUID getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String uuidString = rs.getString(columnName);
        if (uuidString == null) {
            return null;
        }
        else {
            return UUID.fromString(uuidString);
        }
    }

    @Override
    public UUID getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String uuidString = rs.getString(columnIndex);
        if (uuidString == null) {
            return null;
        }
        else {
            return UUID.fromString(uuidString);
        }
    }

    @Override
    public UUID getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String uuidString = cs.getString(columnIndex);
        if (uuidString == null) {
            return null;
        }
        else {
            return UUID.fromString(uuidString);
        }
    }

}
