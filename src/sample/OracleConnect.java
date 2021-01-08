package sample;
import java.sql.*;
import java.util.Map;
import java.util.Vector;

public class OracleConnect {
    private Connection conn=null;

    public static Connection dbConnect(){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","java","java");
            return conn;
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    public static Boolean dbCheckLog(String user,String pass) throws SQLException {
        try {
            Connection conn = dbConnect();
            PreparedStatement stmt = conn.prepareStatement("select count(*) as total from login where username=? and password=? ");
            stmt.setString(1,user);
            stmt.setString(2,pass);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
                if (rs.getInt("total")==1)
                    return true;

            return false;
        }catch(Exception e){
            return false;
        }
    }

    public static Vector<Saving> dbGetSavings (String val) throws SQLException {
        Vector<Saving> list = new Vector<>();
        String txt = "where ROWNUM <="+val;
        if(val.isEmpty())
                txt="";
        Connection conn = dbConnect();
        try{
            Statement stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery("select * from (select * from Savings order by pos desc) "+ txt);
            while(rs.next())
                list.add(new Saving(rs.getInt("pos"),rs.getDate("date_j"),rs.getInt("dejeuner"),rs.getInt("midi"),rs.getInt("diner"),rs.getInt("courses"),rs.getInt("taxi"),rs.getInt("factures"),rs.getInt("other"),rs.getInt("total")));
            return list;
        }catch (Exception e){
            return list;
        }
    }

    /**
     * this method InsertSaving will add prices to Saving's table
     * we used here prepared statements
     */
    public static void InsertSaving(Date d,int dj,int m,int di,int c,int t ,int f,int o) throws SQLException {
        int sum=dj+m+di+c+t+f+o;
        Connection conn = dbConnect();
        // on constate que la table savings possede 9 valeurs mais je n'ai instancié que 8 c'est parceque le trigger s'en charge de la premiere valeur
        PreparedStatement stmt = conn.prepareStatement("insert into JAVA.Savings (DATE_J, DEJEUNER, MIDI, DINER, COURSES, TAXI, FACTURES, OTHER,TOTAL) values (?,?,?,?,?,?,?,?,?)");
        stmt.setDate(1,d);//set date
        stmt.setInt(2,dj);//set dej
        stmt.setInt(3,m);//set midi
        stmt.setInt(4,di);//set diner
        stmt.setInt(5,c);//set courses
        stmt.setInt(6,t);//set taxi
        stmt.setInt(7,f);//set factures
        stmt.setInt(8,o);//set other
        stmt.setInt(9,sum);
        try{
            stmt.executeUpdate();
        }catch (Exception e){
//            JOptionPane.showMessageDialog(null,e);
        }
    }

    public static void InsertOther(Date d,String titre,int prix) throws SQLException {

        Connection conn = dbConnect();
        // on constate que la table savings possede 9 valeurs mais je n'ai instancié que 8 c'est parceque le trigger s'en charge de la premiere valeur
        PreparedStatement stmt = conn.prepareStatement("insert into OTHCHAMP(DATE_J,DESCRIPTION,PRIX) values (?,?,?)");
        stmt.setDate(1,d);//set date
        stmt.setString(2,titre);//set title of product
        stmt.setInt(3,prix);
        try{
            stmt.executeUpdate();
        }catch (Exception e){
//            JOptionPane.showMessageDialog(null,e);
        }
    }
}
