import Repository.MyBookRepository;
import Repository.MyBookRepositoryImpl;
import Service.MyBookService;
import Service.MyBookServiceImpl;
import View.MyBookView;

public class App_11S22036 {
    public static void main(String[] args) {
        MyBookRepository myBookRepository = new MyBookRepositoryImpl();
        MyBookService myBookService = new MyBookServiceImpl(myBookRepository);
        MyBookView myBookView = new MyBookView(myBookService);

        myBookView.showMybook();
    }
}
