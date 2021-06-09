package testBook;


class Book {
    String bookName;
    String bookAuthor;
    String bookSell;

    public Book(){

    }

    public Book(String bN,String bA,String bS){
        bookName = bN;
        bookAuthor = bA;
        bookSell = bS;
    }

   public void  setBook(String bN,String bA,String bS){
        bookName = bN;
        bookAuthor = bA;
        bookSell = bS;
   }

   public  void printBook(){
        System.out.println("书名 "+bookName);
        System.out.println("作者 "+bookAuthor);
        System.out.println("销量 "+bookSell);
   }

}

public class testBook {
    public static void main(String[] args) {
        Book book = new Book("科技书","ak","100");
        book.printBook();
    }
}
