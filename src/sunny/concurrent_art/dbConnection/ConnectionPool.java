package concurrent_art.dbConnection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 构造连接池
 * 通过一个双向队列维护连接
 * Created by Sunny on 2017/8/19.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSzie) {
        if (initialSzie > 0)
            for (int i = 0;i < initialSzie;i++)
                pool.addLast(ConnectionDriver.creatConnection());
    }

    public void releasseConnection(Connection connection) {
        if (null != connection) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    //超时返回
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills < 0) {
                while (pool.isEmpty())
                    pool.wait();
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(mills);
                    remaining = future - System.currentTimeMillis();
                }
                Connection con = null;
                if (!pool.isEmpty())
                    con = pool.removeFirst();
                return con;

            }
        }
    }
}
