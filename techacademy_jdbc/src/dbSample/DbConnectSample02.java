package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.ClassNotFoundException;

public class DbConnectSample02 {

    public static void main(String[] args) {
        //データベース接続と結果習得のための変数。
        Connection con=null;
        Statement stmt=null;
        ResultSet rs=null;
        
        
        try {
            //１．ドライバーのクラスをJava上で読み込む
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        //2.DBと接続する。
        con = DriverManager.getConnection(
         "jdbc:mysql://localhost/world?useSSL=false&allowPublickeyRetrival=ture",
         "root",
         "!49499841a"
                );
        
        //3.DBとやりとりする窓口（statementオブジェクト）の作成
        stmt=con.createStatement();
                
        //4,5 select文の実行と結果を格納/代入
        String sql="select * from country LIMIT 50";
        rs=stmt.executeQuery(sql);
        
         //6.結果を表示する。
        while(rs.next()) {
            String name=rs.getString("name");
            int population=rs.getInt("population");
            System.out.println(name);
            System.out.println(population);
        }
        
        sql="upadate country set Population=105000 where Code='ABW'";
        int count =stmt.executeUpdate(sql);
        System.out.println(count);
                
            
        }catch (ClassNotFoundException e) {
        System.err.println("JDBCドライバーのロードに失敗しました");
        e.printStackTrace();
        
        }catch (SQLException e) {
            System.err.println("JDBCドライバーのロードに失敗しました。");
            e.printStackTrace();
        
        }finally {
            //７．接続を閉じる
            if (rs!=null) {
                try {
                    rs.close();
                }catch (SQLException e) {
                    System.err.println("ResulSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if (stmt!=null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    System.err.println("Strementを閉じるときにエラーが発生しました");
                    e.printStackTrace();
                }
            }
                        
            if (con !=null) {
                try {
                    con.close(); 
                }catch (SQLException e) {
                    System.err.println("データベースの切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }
            
    }
            
}