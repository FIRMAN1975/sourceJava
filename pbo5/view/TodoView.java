package view;

import entity.Todo;
import service.TodoService;
import util.InputUtil;

public class TodoView {

    private TodoService todoService;

    public TodoView(TodoService todoService) {

        this.todoService = todoService;

    }

    /**
     * 
     * Menampilkan view todo
     * 
     */

    public void viewShowTodo() {
        while (true) {

            todoService.serviceShowTodo();

            System.out.println("Menu:");

            System.out.println("1. Tambah");

            System.out.println("2. Ubah");

            System.out.println("3. Hapus");

            System.out.println("x. Keluar");

            var input = InputUtil.input("Pilih");

            var stop = false;

            switch (input) {

                case "1" -> viewAddTodo();

                case "2" -> viewUpdateTodo();

                case "3" -> viewRemoveTodo();

                case "x" -> stop = true;

                default -> System.out.println("[!] Pilihan tidak dimengerti.");

            }

            if (stop) {

                break;

            }

            System.out.println();

        }

    }

    /**
     * 
     * Menampilkan view add todo
     * 
     */

    public void viewAddTodo() {
    System.out.println("[Menambah Todo]");

    var title = InputUtil.input("Judul (x Jika Batal)");

    if (!title.equals("x")) {
        // Ambil semua Todo untuk memeriksa duplikasi
        Todo[] todos = todoService.serviceGetAllTodos(); 

        // Cek apakah judul yang baru sudah ada
        for (Todo todo : todos) {
            if (todo != null && todo.getTitle().equalsIgnoreCase(title)) {
                System.out.printf("[!] Todo %s telah tersedia\n", title);
                return; // Kembali tanpa menambahkan jika ada duplikasi
            }
        }

        // Jika tidak ada duplikasi, tambahkan Todo baru
        todoService.serviceAddTodo(title);
       
    }
}


    /**
     * 
     * Menampilkan view remove todo
     */

     public void viewRemoveTodo() {
        System.out.println("[Menghapus Todo]");
    
        var strIdTodo = InputUtil.input("[ID Todo] yang dihapus (x Jika Batal)");
    
        if (!strIdTodo.equals("x")) {
            int idTodo = Integer.valueOf(strIdTodo); // Mendapatkan ID Todo yang ingin dihapus
    
            // Mendapatkan Todo berdasarkan ID dari service
            var currentTodo = todoService.serviceGetTodoById(idTodo);
    
            if (currentTodo == null) {
                System.out.println("[!] Tidak ditemukan todo dengan ID: " + idTodo + ".");
                return;
            }
    
            // Menampilkan pesan sebelum menghapus dengan judul dari currentTodo
            System.out.println("Dihapus: " + idTodo);
    
            // Panggil service untuk menghapus Todo
            todoService.serviceRemoveTodo(idTodo);
    
            // Memberikan umpan balik kepada pengguna
            
        }
    }
    
    

    /**
     * 
     * Menampilkan view update todo
     */


public void viewUpdateTodo() {
    System.out.println("[Mengubah Todo]");

    // Meminta input ID Todo yang akan diubah
    var strIdTodo = InputUtil.input("[ID Todo] yang diubah (x Jika Batal)");

    if (!strIdTodo.equals("x")) {
        int idTodo = Integer.valueOf(strIdTodo);

        // Mendapatkan Todo berdasarkan ID dari service
        var currentTodo = todoService.serviceGetTodoById(idTodo);

        if (currentTodo == null) {
            System.out.println("[!] Tidak ditemukan todo dengan ID: " + idTodo + ".");
            return;
        }

        // Menampilkan judul lama dan meminta input untuk judul baru
        System.out.println("[!] Tekan enter untuk menggunakan judul lama.");
        var newTitle = InputUtil.input("Judul Baru [Judul Lama: " + currentTodo.getTitle() + "]");
        if (newTitle.isEmpty()) {
            newTitle = currentTodo.getTitle(); // Jika kosong, gunakan judul lama
        }

        // Memeriksa apakah judul baru sudah ada di todo lain
        Todo[] todos = todoService.serviceGetAllTodos();  // Ambil semua Todo
        for (Todo todo : todos) {
            // Cek apakah judul sudah ada di todo lain dengan ID berbeda
            if (todo != null && !todo.getId().equals(idTodo) && todo.getTitle().equalsIgnoreCase(newTitle)) {
                System.out.printf("[!] Todo %s telah tersedia\n", newTitle);
                return;  // Kembali tanpa memperbarui jika ada duplikasi
            }
        }

        // Meminta input status baru (0 untuk belum selesai, 1 untuk selesai)
        var strStatus = InputUtil.input("Status Todo [0: Belum Selesai, 1: Selesai]");
        boolean newStatus;
        if (strStatus.equals("1")) {
            newStatus = true;  // Selesai
        } else if (strStatus.equals("0")) {
            newStatus = false; // Belum selesai
        } else {
            System.out.println("[!] Status tidak valid, menggunakan status lama.");
            newStatus = currentTodo.isFinished(); // Gunakan status lama jika input tidak valid
        }

        // Panggil service untuk memperbarui Todo jika tidak ada duplikasi
        todoService.serviceUpdateTodo(idTodo, newTitle, newStatus);
        
    }
}


    
 
    
    






}