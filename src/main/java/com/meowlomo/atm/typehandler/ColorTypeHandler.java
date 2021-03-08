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
public class ColorTypeHandler extends BaseTypeHandler<String> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {
        Long colorId = RuntimeVariables.getColorToIdMap().get(parameter);
        if (colorId == null) {
            ps.setLong(i, 0);
        }
        else {
            ps.setLong(i, colorId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        long colorId = rs.getLong(columnName);
        if (colorId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToColorMap().get(colorId);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        long colorId = rs.getLong(columnIndex);
        if (colorId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToColorMap().get(colorId);
        }
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        long colorId = cs.getLong(columnIndex);
        if (colorId == 0) {
            return null;
        }
        else {
            return RuntimeVariables.getIdToColorMap().get(colorId);
        }
    }

}
