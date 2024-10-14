package Service;

import Entity.MyBook;
import Repository.MyBookRepository;
import Util.InputUtil;

import java.util.ArrayList;

public class MyBookServiceImpl implements MyBookService {

    private MyBookRepository myBookRepository;
    public MyBookServiceImpl (MyBookRepository myBookRepository){
        this.myBookRepository = myBookRepository;
    }
    @Override
    public void showMyBook() {
        ArrayList<MyBook> model = myBookRepository.getAll();
        
        System.out.println("My Book (Semua) :");
        if (model.size() > 0) {
            for (int i = 0; i < model.size() ; i++) {
                var mybook = model.get(i);
                if (mybook != null && mybook.isArchived()) {
                    System.out.println(mybook.getIsbn() + ". " + mybook.getTitle() + " [" + mybook.getCurrentPages() + "/" + mybook.getTotalPages() + "]" + " (Diarsipkan)");
                } else if(mybook != null && !mybook.isArchived()) {
                    System.out.println(mybook.getIsbn() + ". " + mybook.getTitle() + " [" + mybook.getCurrentPages() + "/" + mybook.getTotalPages() + "]");
                }
            }
        } else {
            System.out.println("Belum ada daftar buku yang tersedia");
        }
        System.out.println();
    }

    @Override
    public boolean readingMyBook(int isbn) {
        MyBook membaca = null;
        boolean succes = false;

        for (MyBook myBook : myBookRepository.getAll()) {
            if(myBook.getIsbn() == isbn) {
                membaca = myBook;
                System.out.println("Informasi Buku :");
                System.out.println("ISBN : " + membaca.getIsbn());
                System.out.println("Judul : " + membaca.getTitle());
                System.out.println("Penulis : " + membaca.getAuthor());
                System.out.println("Penerbit : " + membaca.getPublisher());
                System.out.println("Tahun Terbit : " + membaca.getYear());
                System.out.println("Jumlah Halaman : " + membaca.getTotalPages());
                System.out.println("Halaman Terakhir Dibaca : " + membaca.getCurrentPages());
                System.out.println("Status Arsip : Tidak Diarsipkan");
                System.out.println();

                var inginMembaca = InputUtil.input("Halaman saat ini");
                System.out.println();

                int numMembaca = Integer.parseInt(inginMembaca);
                if(numMembaca > membaca.getTotalPages() || numMembaca < 0) {
                    System.out.println("GAGAL MEMBACA MY BOOK : " + membaca.getIsbn() + " sampai halaman " + numMembaca);
                } else {
                    membaca.setCurrentPages(numMembaca);
                    System.out.println("SUKSES MEMBACA MY BOOK : " + membaca.getIsbn() + " sampai halaman " + membaca.getCurrentPages());
                    succes = myBookRepository.updateReading(isbn, numMembaca);;
                }
                break;
            } else {
                System.out.println("Buku dengan ISBN " + isbn + " belum tersedia");
                System.out.println();
            }
        }
        return succes;
    }

    @Override
    public void addMyBook(int isbn, String title, String author, String publisher, int year, int totalPages) {
        MyBook isexixt = null;
        for (MyBook myBook : myBookRepository.getAll()) {
            if (myBook.getIsbn() == isbn) {
                isexixt = myBook;
                break;
            }
        }

        MyBook myBook = new MyBook(isbn,title,author,publisher,year,totalPages);
        if (isexixt != null) {
            System.out.println("GAGAL MENAMBAH MY BOOK : isbn " + myBook.getIsbn() + " telah tersedia");
        } else {
            myBookRepository.add(myBook);
            System.out.println("SUKSES MENAMBAH MY BOOK : " + myBook.getTitle() + " dengan isbn " + myBook.getIsbn());
        }
    }



    @Override
    public void removeMyBook(int isbn) {
        boolean succes = myBookRepository.remove(isbn);
        System.out.println();
        if (succes) {
            System.out.println("SUKSES MENGHAPUS MY BOOK : " + isbn);
        } else {
            System.out.println("GAGAL MENGHAPUS MY BOOK : " + isbn);
        }
    }

    @Override
    public void updateReadingMyBook(int isbn, int currentPages) {

    }

    @Override
    public void showMyBookWithoutArchived() {
       ArrayList<MyBook> tanpaArchive = myBookRepository.getWithoutArchived();
       System.out.println("My Book :");
        if (tanpaArchive.size() > 0 ) {
            for (int i = 0; i < tanpaArchive.size() ; i++) {
                var mybook = tanpaArchive.get(i);
                System.out.println(mybook.getIsbn() + ". " + mybook.getTitle() + " [" + mybook.getCurrentPages() + "/" + mybook.getTotalPages() + "]");
            }
        } else {
            System.out.println("Belum ada daftar buku yang tersedia");
        }
        System.out.println();
       
    }

    @Override
    public void showMyBookOnlyArchived() {
       ArrayList<MyBook> denganArchive = myBookRepository.getOnlyArchived();
       System.out.println("My Book (Diarsipkan) :");
        if (denganArchive.size() > 0) {
            for (int i = 0; i < denganArchive.size() ; i++) {
                var mybook = denganArchive.get(i);
                System.out.println(mybook.getIsbn() + ". " + mybook.getTitle() + " [" + mybook.getCurrentPages() + "/" + mybook.getTotalPages() + "]");
            }
        } else {
            System.out.println("Belum ada daftar buku yang tersedia");
        }
        System.out.println();
    }

    @Override
    public void updateArchivedMyBook(int isbn, boolean isArchived) {
        MyBook isexist = null;

        for (MyBook myBook : myBookRepository.getAll()) {
            if(myBook.getIsbn() == isbn) {
                isexist = myBook;
                break;
            } 

        }
        if(isArchived && isexist != null) {
            System.out.println("SUKSES MENGARSIPKAN MY BOOK : " + isbn + " menjadi diarsipkan");
            myBookRepository.updateArchived(isbn, isArchived);
        } else if(!isArchived && isexist != null) {
            System.out.println("SUKSES MENGARSIPKAN MY BOOK : " + isbn + " menjadi batal diarsipkan");
            myBookRepository.updateArchived(isbn, isArchived);
        } else {
            System.out.println("GAGAL MENGARSIPKAN MY BOOK : " + isbn + " menjadi diarsipkan");
        }
    }

}
