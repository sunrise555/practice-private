package concurrent_art.dbConnection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * 模拟数据库驱动
 * 由于Connection是一个接口，如果相对接口内的方法进行操作，需要使用反射来动态代理
 * 因此采用动态代理构造一个connection，这个connection的代理实现在commit()方法中休眠100ms
 *
 * Created by Sunny on 2017/8/19.
 */
public class ConnectionDriver {
    public final static Connection creatConnection() {
        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[] {Connection.class}, new ConnectionHandler());
    }

    private static class ConnectionHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit"))
                TimeUnit.MILLISECONDS.sleep(100);
            return null;
        }
    }
}
