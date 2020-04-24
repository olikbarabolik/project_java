package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectorTest {

    @Test
    public void testDbConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/addressbook?user=root&password=");
            Statement st  = conn.createStatement();
            ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()) {
                /*groups.add(new GroupData().withId(rs.getInt("group_id").withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer"))));*/
                groups.add(new GroupData().withName(rs.getString("group_name"))
                        .withHeader(rs.getString("group_header")).withFooter(rs.getString("group_footer")));
            }
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);
        } catch (SQLException ex){
            System.out.println("SQL Exception" + ex.getMessage());
            System.out.println("SQL State" + ex.getSQLState());
            System.out.println("Vendor Error" + ex.getErrorCode());
        }

    }
}
