package src.dao;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import src.model.Book;

public class BookDao {

    public void addBook(Connection con ){
        try(Statement statement=con.createStatement()){

        }catch(SQLException e){

        }
    }

    public void testMethod(Connection con){
        String query="select *  from book_tb";
        try(Statement st=con.createStatement()) {
            System.out.println("Successfully ");
            ResultSet rs=st.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("name")+" "+rs.getInt("price"));
            }
        } catch (Exception e) {
            System.out.println("failed"); 
            e.printStackTrace();
        }
    }

    public static Book getBookByName(String bookName, Connection connection){
        String sql="select * from book_tb where (name)=(?)";
        Book retriveBook=null;
        String booknameRetrived=null;
        String bookEditionRetrived=null;
        double bookPriceRetrived=0.0; 
        src.model.Author authorRetrived=null;
        try(PreparedStatement preparedStatement=connection.prepareStatement(sql)) {
            preparedStatement.setString(1, bookName);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                booknameRetrived=resultSet.getString("name");
                bookEditionRetrived =resultSet.getString("edition");
                bookPriceRetrived=resultSet.getDouble("price");
                authorRetrived=(src.model.Author)resultSet.getObject("author");
                   }
                retriveBook=new Book(booknameRetrived, bookEditionRetrived, bookPriceRetrived, authorRetrived);
        } catch (Exception e) { 
            e.printStackTrace();
        }
        return retriveBook;
    }
    
}