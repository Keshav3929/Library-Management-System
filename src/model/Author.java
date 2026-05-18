package src.model;

public class Author {
    private String name;
    private int id;
    private int bookPublished;

    public Author(String name, int bookPublished){
        this.bookPublished=bookPublished;
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getBookPublished(){
        return bookPublished;
    }
    public void setBookPublished(int bookPublished){
        this.bookPublished=bookPublished;
    }
}