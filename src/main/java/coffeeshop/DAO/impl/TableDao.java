package coffeeshop.DAO.impl;

import coffeeshop.DAO.ITableDao;
import coffeeshop.DTO.Table;
import coffeeshop.Util.*;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
public class TableDao implements ITableDao {

    Connection conn = null;
    CallableStatement cs = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private BaseMessage response;

    public TableDao(DbUtil dbUtil) {
        conn = dbUtil.getInstance().getConnection();
    }

    @Override
    public int count() {
        int count = 0;
        String sql = "{CALL sp_countTables}";

        try {
            cs = conn.prepareCall(sql);
            rs = cs.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }

            response = new MessageResponse<>(Constant.SUCCESS_RESPONSE, "Thành công", count);
            log.info(Common.createMessageLog(null, response, "count"));
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(null, response, "count"));
        } finally {
            rs = null;
            cs = null;
        }

        return count;
    }

    @Override
    public List<Table> getAll(Table table) {
        List<Table> list = new ArrayList<>();
        String sql = "{CALL sp_getAllTable(?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setNull(1, Types.INTEGER);
            cs.setNull(2, Types.INTEGER);
            cs.setNull(3, Types.NVARCHAR);
            cs.setNull(4, Types.BOOLEAN);

            if (!Common.isNullOrEmpty(table)) {
                if (!Common.isNullOrEmpty(table.getId())) {
                    cs.setInt(1, table.getId());
                }
                if (!Common.isNullOrEmpty(table.getArea_id())) {
                    cs.setInt(2, table.getArea_id());
                }
                if (!Common.isNullOrEmpty(table.getName())) {
                    cs.setNString(3, table.getName());
                }
                if (!Common.isNullOrEmpty(table.getStatus())) {
                    cs.setBoolean(4, table.getStatus());
                }
            }
            rs = cs.executeQuery();

            while (rs.next()) {
                Table obj = new Table(
                        rs.getInt("id"),
                        rs.getInt("area_id"),
                        rs.getNString("name"),
                        rs.getBoolean("status")
                );

                list.add(obj);
            }

            response = new MessageResponse<>(Constant.SUCCESS_RESPONSE, "Thành công", list);
            log.info(Common.createMessageLog(table, response, "getAll"));
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(table, response, "getAll"));
        } finally {
            rs = null;
            cs = null;
        }

        return list;
    }

    @Override
    public Map<String, Object> create(Table table) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_insertTable(?, ?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, table.getArea_id());
            cs.setNString(2, table.getName());
            cs.setBoolean(3, table.getStatus());
            cs.registerOutParameter(4, Types.BIT);
            cs.registerOutParameter(5, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(4));
            output.put("message", cs.getNString(5));

            response = new MessageResponse<>(cs.getBoolean(4), cs.getNString(5), output);
            if (cs.getBoolean(4)) {
                log.info(Common.createMessageLog(table, response, "create"));
            } else {
                log.error(Common.createMessageLog(table, response, "create"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(table, response, "create"));
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public Table read(int id) {
        Table obj = null;
        String sql = "SELECT * FROM Tables WHERE id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            while (rs.next()) {
                obj = new Table(
                        rs.getInt("id"),
                        rs.getInt("area_id"),
                        rs.getNString("name"),
                        rs.getBoolean("status")
                );
            }

            response = new MessageResponse<>(Constant.SUCCESS_RESPONSE, "Thành công", obj);
            log.info(Common.createMessageLog(id, response, "read"));
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(id, response, "read"));
        } finally {
            ps = null;
        }

        return obj;
    }

    @Override
    public Map<String, Object> update(Table table) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_updateTable(?, ?, ?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, table.getId());
            cs.setInt(2, table.getArea_id());
            cs.setNString(3, table.getName());
            cs.setBoolean(4, table.getStatus());
            cs.registerOutParameter(5, Types.BIT);
            cs.registerOutParameter(6, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(5));
            output.put("message", cs.getNString(6));

            response = new MessageResponse<>(cs.getBoolean(5), cs.getNString(6), output);
            if (cs.getBoolean(5)) {
                log.info(Common.createMessageLog(table, response, "update"));
            } else {
                log.error(Common.createMessageLog(table, response, "update"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(table, response, "update"));
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public Map<String, Object> delete(int id) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_deleteTable(?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.BIT);
            cs.registerOutParameter(3, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(2));
            output.put("message", cs.getNString(3));

            response = new MessageResponse<>(cs.getBoolean(2), cs.getNString(3), output);
            if (cs.getBoolean(2)) {
                log.info(Common.createMessageLog(id, response, "delete"));
            } else {
                log.error(Common.createMessageLog(id, response, "delete"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(id, response, "delete"));
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public Table findByName(String name) {
        Table obj = null;
        String sql = "{CALL sp_getAllTable(?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setNull(1, Types.INTEGER);
            cs.setNull(2, Types.INTEGER);
            cs.setNull(3, Types.NVARCHAR);
            cs.setNull(4, Types.BOOLEAN);

            if (!Common.isNullOrEmpty(name)) {
                cs.setNString(3, name);
            }
            rs = cs.executeQuery();

            while (rs.next()) {
                obj = new Table(
                        rs.getInt("id"),
                        rs.getInt("area_id"),
                        rs.getNString("name"),
                        rs.getBoolean("status")
                );
            }

            response = new MessageResponse<>(Constant.SUCCESS_RESPONSE, "Thành công", obj);
            log.info(Common.createMessageLog(name, response, "findByName"));
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(name, response, "findByName"));
        } finally {
            rs = null;
            cs = null;
        }

        return obj;
    }
}
