package sunny.homework.keyWords;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class TransientTest implements Serializable{
	private Date loggingDate = new Date();   
    private String uid;   
    private transient String pwd;   
      
    TransientTest(String user, String password)   
    {   
        uid = user;   
        pwd = password;   
    }   
    public String toString()   
    {   
        String password=null;   
        if(pwd == null)   
        {   
        password = "NOT SET";   
        }   
        else  
        {   
            password = pwd;   
        }   
        return "logon info: \n   " + "user: " + uid +   
            "\n   logging date : " + loggingDate.toString() +   
            "\n   password: " + password;   
    }   
    
    public static void main(String[] args) {
    	TransientTest logInfo = new TransientTest("MIKE", "MECHANICS");   
    	System.out.println("序列化操作前loginInfo="+logInfo.toString());   
    	
    	//对象序列化
    	try  
    	{   
    	   ObjectOutputStream o = new ObjectOutputStream(   
    	                new FileOutputStream("logInfo.out"));   
    	   o.writeObject(logInfo);   
    	   o.close();   
    	}   
    	catch(Exception e) {
    		
    	}//deal with exception}   
    	  
    	//To read the object back, we can write   
    	 //对象反序列化
    	try  
    	{   
    	   ObjectInputStream in =new ObjectInputStream(   
    	                new FileInputStream("logInfo.out"));   
    	   logInfo = (TransientTest) in.readObject();
    	   System.out.println("反序列化操作后loginInfo="+logInfo.toString()); 
    	   in.close();
    	}   
    	catch(Exception e2) {
    		
    	}
    	//输出结果：password未被序列化，所以反序列化时，该数据成员为null
    	/*序列化操作前loginInfo=logon info: 
   			user: MIKE
   			logging date : Fri Mar 17 20:59:01 CST 2017
   			password: MECHANICS
		 反序列化操作后loginInfo=logon info: 
   			user: MIKE
   			logging date : Fri Mar 17 20:59:01 CST 2017
   			password: NOT SET
    	 */
    	
	}
}
