package View;

import Service.MyBookService;
import Util.InputUtil;

public class MyBookView {
    private MyBookService myBookService;
    private int viewMode = 1;
    private int previousViewMode = 1;

    public MyBookView (MyBookService myBookService){
        this.myBookService = myBookService;
    }

    public void showMybook() {
        while (true) {
            if (viewMode == 1) {
                myBookService.showMyBook();
            } else if (viewMode == 2) {
                myBookService.showMyBookWithoutArchived();
            } else if(viewMode == 3) {
                myBookService.showMyBookOnlyArchived();
            }
            System.out.println("Menu :");
            System.out.println("1. Mode Tampilan");
            System.out.println("2. Tambah");
            System.out.println("3. Membaca");
            System.out.println("4. Mengarsipkan");
            System.out.println("5. Hapus");
            System.out.println("x. Keluar");

            var input = InputUtil.input("Pilih");
            System.out.println();
            var stop = false;
            switch (input) {
                case "1" -> viewModeMyBook();
                case "2" -> addMyBook();
                case "3" -> readingMyBook();
                case "4" -> archivedMyBook();
                case "5" -> removeMyBook();
                case"x" -> stop = true;
                default -> System.out.println("Pilihan tidak dimengerti");
            }

            if (stop) {
                break;
            }

            System.out.println();
        }
    }

    public void addMyBook() {
        System.out.println("Menambah My Book");
        var judul = InputUtil.input("Judul (x Jika Batal)");
        if (judul.equals("x")) {
            System.out.println("Tindakan dibatalkan");
            System.out.println();
        } else {
            var isbn = InputUtil.input("ISBN");
            var penulis = InputUtil.input("Penulis");
            var penerbit = InputUtil.input("Penerbit");
            var Tahun = InputUtil.input("Tahun Terbit");
            var halaman = InputUtil.input("Jumlah Halaman");

            int year = Integer.parseInt(Tahun);
            int isbnnumber = Integer.parseInt(isbn);
            int hal = Integer.parseInt(halaman);
            System.out.println();
            myBookService.addMyBook(isbnnumber, judul, penulis, penerbit, year, hal);
        }
    }

    public void readingMyBook() {
        System.out.println("Membaca My Book");
        var isbn = InputUtil.input("ISBN (x Jika Batal)");
        if (isbn.equals("x")) {
            System.out.println("Tindakan dibatalkan");
            System.out.println();
        } else {
            int isbnnumber = Integer.parseInt(isbn);
            myBookService.readingMyBook(isbnnumber);
        }
        
    }

    public void removeMyBook() {
        System.out.println("Menghapus My Book");
        var isbn = InputUtil.input("ISBN (x Jika Batal)");

        if (isbn.equals("x")) {
            System.out.println("Tindakan dibatalkan");
             System.out.println();
        } else {
            int isbnnumber = Integer.parseInt(isbn);
            myBookService.removeMyBook(isbnnumber);
        }
        
    }

    public void archivedMyBook() {
        System.out.println("Mengarsipkan My Book");
        var isbn = InputUtil.input("ISBN (x Jika Batal)");  
        if(isbn.equals("x")) {
            System.out.println("Tindakan dibatalkan");
            System.out.println();
        } else {
            int isbnnumber = Integer.parseInt(isbn);
            System.out.println();

            System.out.println("Konfirmasi Tindakan :");
            System.out.println("1. Mengarsipkan Buku");
            System.out.println("[Lainnya]. Batal Mengarsipkan Buku");
            var respond = InputUtil.input("Pilihan");
            System.out.println();

            if(respond.equals("1")) {
                myBookService.updateArchivedMyBook(isbnnumber, true);
            } else {
                myBookService.updateArchivedMyBook(isbnnumber, false);
            }
        }
        
    }

    public void viewModeMyBook() {
        System.out.println("Memilih Mode Tampilan :");
        System.out.println("1. Semua Daftar Buku");
        System.out.println("2. Daftar Buku Selain yang Diarsipkan");
        System.out.println("3. Daftar Buku yang Diarsipkan");
        System.out.println("x. Batal");

        var view = InputUtil.input("Pilih Mode");
        
        
        if(view.equals("x")) {
            viewMode = previousViewMode;
            System.out.println("Tindakan dibatalkan");
            System.out.println();
        } else {
            viewMode = Integer.parseInt(view);
            if(viewMode == 1) {
            previousViewMode = viewMode;
            System.out.println("Berhasil mengubah mode tampilan menjadi : " + viewMode);
            System.out.println();
            } else if(viewMode == 2) {
                previousViewMode = viewMode;
                System.out.println("Berhasil mengubah mode tampilan menjadi : " + viewMode);
                System.out.println();
            } else if(viewMode == 3) {
                previousViewMode = viewMode;
                System.out.println("Berhasil mengubah mode tampilan menjadi : " + viewMode);
                System.out.println();
            } else {
                viewMode = previousViewMode;
                System.out.println("Pilihan tidak dimengerti");
                System.out.println();
            }
        }
    }
}
