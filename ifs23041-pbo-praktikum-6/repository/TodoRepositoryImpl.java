package repository;

import java.util.ArrayList;
import java.util.Comparator;
import entity.Todo;

public class TodoRepositoryImpl implements TodoRepository {
    public ArrayList<Todo> data = new ArrayList<Todo>();

    @Override
    public ArrayList<Todo> repoGetAllTodo() {
        return data;
    }

    @Override
    public void repoAddTodo(Todo newTodo) {
        data.add(newTodo);
    }

    @Override
    public boolean repoRemoveTodo(Integer idTodo) {
        Todo targetTodo = data
                .stream()
                .filter(obj -> obj.getId() == idTodo)
                .findFirst()
                .orElse(null);
        if (targetTodo == null) {
            return false;
        }
        data.remove(targetTodo);
        return true;
    }

//    @Override
//    public boolean repoUpdateTodo(Integer idTodo, String newTitle, Boolean newFinished) {
//        // Cari Todo berdasarkan ID
//        Todo targetTodo = data.stream()
//                .filter(todo -> todo.getId() == idTodo)
//                .findFirst()
//                .orElse(null);
//
//        if (targetTodo == null) {
//            // Jika Todo tidak ditemukan, kembalikan false
//            return false;
//        }
//
//        // Update title jika newTitle tidak null
//        if (newTitle != null && !newTitle.isEmpty()) {
//            targetTodo.setTitle(newTitle);
//        }
//
//        // Update finished status jika newFinished tidak null
//        if (newFinished != null) {
//            targetTodo.setFinished(newFinished);
//        }



//        // Jika proses update berhasil, kembalikan true
//        return true;
//    }

    @Override
    public boolean repoUpdateTodo(Integer nomor, Todo baru) {
        if (nomor <= 0 || nomor > data.size() || data.get(nomor - 1) == null) {
            System.out.println("[!] Tidak ada To-Do pada posisi tersebut.");
            return false;
        }

        for (Todo item : data) {
            if (item != null && baru.getTitle().equalsIgnoreCase(item.getTitle()) && item != data.get(nomor - 1)) {
                System.out.println("[!] To-Do '" + baru.getTitle() + "' telah tersedia.");
                return false;
            }
        }

        Todo todoLama = data.get(nomor - 1);
        baru.setId(todoLama.getId());  // Preserve the ID from the old Todo

        data.set(nomor - 1, baru);  // Update the Todo at the given position

        return true;
    }


    @Override
    public void repoSortIDTodo(ArrayList<Todo> data, boolean ascending) {
        if (ascending) {
            data.sort(Comparator.comparing(Todo::getId));
        } else {
            data.sort(Comparator.comparing(Todo::getId).reversed());
        }
    }

    @Override
    public void repoSortTitleTodo(ArrayList<Todo> data, boolean ascending) {
        if (ascending) {
            // Urutkan berdasarkan title secara ascending (A-Z)
            data.sort(Comparator.comparing(Todo::getTitle));
        } else {
            // Urutkan berdasarkan title secara descending (Z-A)
            data.sort(Comparator.comparing(Todo::getTitle).reversed());
        }
    }

//    @Override
//    public void repoSearchTodo(ArrayList<Todo> data, String keywords) {
//        System.out.println("Data Tersedia untuk Hasil Pencarian \"" + keywords + "\" :");
//        int position = 0;
//        for (Todo todo : data) {
//            if (todo.getTitle().contains(keywords)) {
//                position++;
//                System.out.println(position + ". " + todo);
//            }
//        }
//        if (position == 0) {
//            System.out.println("Tidak ada data yang cocok dengan pencarian.");
//        }
//        System.out.println();
//    }

    @Override
    public void repoSearchTodo(ArrayList<Todo> data, String keywords) {
        System.out.println("Data Tersedia untuk Hasil Pencarian \"" + keywords + "\" :");

//        if (position == 0) {
//            System.out.println("Tidak ada data yang cocok dengan pencarian.");
//        }
        System.out.println();
    }

}
