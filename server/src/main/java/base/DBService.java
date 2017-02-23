package base;

import dbService.DBException;
import dbService.dataSets.UsersDataSet;

/**
 * Created by nmikutskiy on 01.10.16.
 */
public interface DBService {
    void printConnectInfo();
    long addUser(String name) throws DBException;
    UsersDataSet getUser(long id) throws DBException;
    void cleanUp() throws DBException;
}
