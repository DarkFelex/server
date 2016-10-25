package dbService.dao;

import dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by nmikutskiy on 25.10.16.
 */
public class MapDAO {
    private Executor executor;

    public MapDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists map (id bigint auto_increment, region_id int, x int, y int, is_place_for_village boolean, village_id int, primary key (id))");
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table map");
    }
}
