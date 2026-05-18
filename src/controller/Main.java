package src.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import src.dao.AuthorDao;  
import src.dao.BookDao;
import src.model.Author;
import src.model.Book;
import src.utils.DBConnection;

public class Main {
    public static void main(String[] args) {

        BookDao dao = new BookDao();
        AuthorDao authorDao = new AuthorDao();  
        Scanner sc = new Scanner(System.in);
        while (true) { 
            System.out.println("Library Management Project ------------");
        System.out.println("1:Author Options\n2:Book Options\n3:Exit Program");
        int choice;
        choice = sc.nextInt();
        sc.nextLine();
        if(choice==3)
            break;

        switch (choice) {
            case 1:
                System.out.println("a--Add Author\nb--Delete Author\nc--Update Author\nd--All Author");
                char options = sc.nextLine().charAt(0);
                authorCrud(options);
                break;
            case 2:
                Book book;
                try {
                    book = bookInput();        
                    System.out.println(book);       
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;                                 

            case 3:                                    
                System.out.println("Exiting program...");
            }     
            if(choice==1){
                break;
            }
        }
        } 

    public static Book bookInput() throws SQLException {

        Connection connection = DBConnection.getConnection();  // FIX 8: DriverManager.getConnection() needs URL/credentials, use DBConnection instead

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book name : ");
        String name = sc.nextLine();

        System.out.println("Enter book edition : ");
        String edition = sc.nextLine();

        System.out.println("Enter book price : ");
        double price = sc.nextDouble();  //  FIX 9: Was sc.nextInt() for a double field
        sc.nextLine();

        System.out.println("Enter book author name : ");
        String authorName = sc.nextLine();
        Author author = AuthorDao.getAuthorByName(authorName, connection);

        Book newBook = new Book(name, edition, price, author);

        return newBook;
    }
    
    public static void authorCrud(char options){
        AuthorDao authorDao = new AuthorDao();
        Scanner sc=new Scanner(System.in);
        switch(options){
            case 'a':
                String input1;
                System.out.println("Enter author name : ");
                input1=sc.nextLine();

                try(Connection con=DBConnection.getConnection();){
                    authorDao.addAuthor(con,input1);
                }catch(Exception e){
                    e.printStackTrace();
                }
                break;
                case 'b':
                    String nameEntered;
                    System.out.println("Enter author name to delete : ");
                    nameEntered=sc.nextLine();
                    authorDao.delAuthor(nameEntered);
                    break;
                case 'c':
                    System.out.println("Enter author Name you want to update : ");
                    String prevName=sc.nextLine();

                    System.out.println("Enter updated Name : ");
                    String updatedName=sc.nextLine();

                    System.out.println("Name Updated Seccessfully !");
                    authorDao.updateAuthor(prevName, updatedName);
                    break;
                case 'd':
                    authorDao.getAllAuthor();
                    break;
                case 'e':
                    System.exit(0);
                default: 
                    //invalid input
                    break;
        }
    }
}