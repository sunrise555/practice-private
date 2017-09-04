package concurrent_art.dbConnection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.concurrent.TimeUnit;

/**
 * ģ�����ݿ�����
 * ����Connection��һ���ӿڣ������Խӿ��ڵķ������в�������Ҫʹ�÷�������̬����
 * ��˲��ö�̬������һ��connection�����connection�Ĵ���ʵ����commit()����������100ms
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
