package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SaveObject {

    public Object javaObject=null;

    public Object getJavaObject() {
        return javaObject;
    }


    public void setJavaObject(Object javaObject) {
        this.javaObject = javaObject;
    }


    public  void saveObject() throws Exception
    {
        try{
        Connection conn=/// get connection string;
        PreparedStatement ps=null;
        String sql=null;

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);

        oos.writeObject(javaObject);
        oos.flush();
        oos.close();
        bos.close();

        byte[] data = bos.toByteArray();


        sql="insert into javaobject (javaObject) values(?)";
        ps=conn.prepareStatement(sql);
        ps.setObject(1, data);
        ps.executeUpdate();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }


    public Object getObject() throws Exception
    {
        Object rmObj=null;
        Connection conn=/// get connection string;
        PreparedStatement ps=null;
        ResultSet rs=null;
        String sql=null;

        sql="select * from javaobject where id=1";

        ps=conn.prepareStatement(sql);

        rs=ps.executeQuery();

        if(rs.next())
        {
            ByteArrayInputStream bais;

            ObjectInputStream ins;

            try {

            bais = new ByteArrayInputStream(rs.getBytes("javaObject"));

            ins = new ObjectInputStream(bais);

            MyClass mc =(MyClass)ins.readObject();

            System.out.println("Object in value ::"+mc.getSName());
            ins.close();

            }
            catch (Exception e) {

            e.printStackTrace();
            }

        }

        return rmObj;
    }
}