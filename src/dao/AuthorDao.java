package src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import src.model.Author;
import src.utils.DBConnection;

public class AuthorDao {

    public void updateAuthor(String prevName, String updatedName){
        String updateQuery = "update author_tb set name = ? where name = ?";        
        try(
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(updateQuery);
        ){
            ps.setString(1, updatedName);
            ps.setString(2, prevName);

            int rowUpdate=ps.executeUpdate();
            System.out.println("Row update "+rowUpdate);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getAllAuthor(){
        try(
            Connection con = DBConnection.getConnection();
            Statement st=con.createStatement();
        ){
            String query="select name from author_tb";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAuthor(Connection connection, String author_name) {
        String sql = "INSERT INTO author_tb(name) VALUES(?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, author_name);
            int row = preparedStatement.executeUpdate();
            System.out.println("Row updated: " + row);
        } catch (Exception e) {
            System.out.println("Exception occurred in addAuthor Block");
            e.printStackTrace();
        }
    }

    public static Author getAuthorByName(String authorName, Connection connection) {
        String sql = "SELECT * FROM author_tb WHERE name = ?";
        Author retrievedAuthor = null;
        String nameRetrieved = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, authorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                nameRetrieved=resultSet.getString("name");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return retrievedAuthor;
    }
    
    public void delAuthor(String authorName){
        String query="delete from author_tb where (name)=(?)";

        try(Connection con =DBConnection.getConnection();
        PreparedStatement preparedStatement=con.prepareStatement(query)){
            preparedStatement.setString(1, authorName);
            int rowUpdate=preparedStatement.executeUpdate();
            System.out.println("row updated "+rowUpdate);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}