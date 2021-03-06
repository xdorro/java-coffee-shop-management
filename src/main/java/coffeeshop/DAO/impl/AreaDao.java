package coffeeshop.DAO.impl;

import coffeeshop.DAO.IAreaDao;
import coffeeshop.DTO.Area;
import coffeeshop.Util.*;
import lombok.extern.log4j.Log4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
public class AreaDao implements IAreaDao {

    Connection conn = null;
    CallableStatement cs = null;
    ResultSet rs = null;
    private BaseMessage response;

    public AreaDao(DbUtil dbUtil) {
        conn = dbUtil.getInstance().getConnection();
    }

    @Override
    public int count() {
        int count = 0;
        String sql = "{CALL sp_countAreas}";

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
    public List<Area> getAll() {
        List<Area> list = new ArrayList<>();
        String sql = "{CALL sp_getAllArea}";

        try {
            cs = conn.prepareCall(sql);
            rs = cs.executeQuery();

            while (rs.next()) {
                Area obj = new Area(
                        rs.getInt("id"),
                        rs.getNString("name"),
                        rs.getBoolean("status")
                );
                list.add(obj);
            }

            response = new MessageResponse<>(Constant.SUCCESS_RESPONSE, "Thành công", list);
            log.info(Common.createMessageLog(null, response, "getAll"));
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(null, response, "getAll"));
        } finally {
            rs = null;
            cs = null;
        }

        return list;
    }

    @Override
    public Map<String, Object> create(Area area) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_insertArea(?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setNString(1, area.getName());
            cs.setBoolean(2, area.getStatus());
            cs.registerOutParameter(3, Types.BIT);
            cs.registerOutParameter(4, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(3));
            output.put("message", cs.getNString(4));

            response = new MessageResponse<>(cs.getBoolean(3), cs.getNString(4), output);
            if (cs.getBoolean(3)) {
                log.info(Common.createMessageLog(area, response, "create"));
            } else {
                log.error(Common.createMessageLog(area, response, "create"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(area, response, "create"));
        } finally {
            cs = null;
        }

        return output;
    }

    /**
     *
     * @param name
     * @return
     */
    @Override
    public Area findByName(String name) {
        Area obj = null;
        String sql = "{CALL sp_findAreaByName(?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setNString(1, name);
            rs = cs.executeQuery();

            while (rs.next()) {
                obj = new Area(
                        rs.getInt("id"),
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

    @Override
    public Map<String, Object> update(Area area) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_updateArea(?, ?, ?, ?, ?)}";

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, area.getId());
            cs.setNString(2, area.getName());
            cs.setBoolean(3, area.getStatus());
            cs.registerOutParameter(4, Types.BIT);
            cs.registerOutParameter(5, Types.NVARCHAR);
            cs.execute();

            output.put("status", cs.getBoolean(4));
            output.put("message", cs.getNString(5));

            response = new MessageResponse<>(cs.getBoolean(4), cs.getNString(5), output);
            if (cs.getBoolean(4)) {
                log.info(Common.createMessageLog(area, response, "update"));
            } else {
                log.error(Common.createMessageLog(area, response, "update"));
            }
        } catch (SQLException e) {
            response = new BaseMessage(Constant.ERROR_RESPONSE, e.getMessage());
            log.error(Common.createMessageLog(area, response, "update"));
        } finally {
            cs = null;
        }

        return output;
    }

    @Override
    public Map<String, Object> delete(int id) {
        Map<String, Object> output = new HashMap<>();
        String sql = "{CALL sp_deleteArea(?, ?, ?)}";

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
    public Area read(int id) {
        return null;
    }
}
