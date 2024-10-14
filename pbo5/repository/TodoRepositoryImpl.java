package repository;

import entity.Todo;

public class TodoRepositoryImpl implements TodoRepository {
    public Todo[] data = new Todo[10];

    @Override
    public Todo[] repoGetAllTodo() {
        return data;
    }

    @Override
    public void repoAddTodo(Todo newTodo) {
        resizeIfFull();
        // tambahkan ke posisi yang data array-nya NULL
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                data[i] = newTodo;
                break;
            }
        }
    }

    // @Override
    // public boolean repoRemoveTodo(Integer idTodo) {
    //     int position = 0;
    //     for (Todo todo : data) {
    //         if (todo == null)
    //             break;
    //         position++;
    //         if (todo.getId() == idTodo)
    //             break;
    //     }
    //     if (position <= 0)
    //         return false;
    //     if ((position - 1) >= data.length)
    //         return false;
    //     if (data[position - 1] == null)
    //         return false;
    //     for (int i = (idTodo - 1); i < data.length; i++) {
    //         if (i == (data.length - 1)) {
    //             data[i] = null;
    //         } else {
    //             data[i] = data[i + 1];
    //         }
    //     }
    //     return true;
    // }

    @Override
public boolean repoRemoveTodo(Integer idTodo) {
    int position = -1;  // Menyimpan posisi todo yang ingin dihapus

    // Cari posisi berdasarkan ID Todo
    for (int i = 0; i < data.length; i++) {
        if (data[i] == null) {
            break;  // Jika mencapai posisi null, berhenti mencari
        }
        if (data[i].getId().equals(idTodo)) {  // Cek apakah ID Todo cocok
            position = i;  // Simpan posisi elemen yang ditemukan
            break;
        }
    }

    // Jika tidak ditemukan, kembalikan false
    if (position == -1) {
        return false;  // Todo dengan ID tersebut tidak ditemukan
    }

    // Geser elemen setelah todo yang dihapus ke depan
    for (int i = position; i < data.length - 1; i++) {
        data[i] = data[i + 1];  // Geser elemen
    }

    // Kosongkan elemen terakhir karena sudah digeser
    data[data.length - 1] = null;

    return true;  // Hapus berhasil
}


    @Override
    public boolean repoUpdateTodo(Integer nomor, Todo baru) {
        if (nomor <= 0 || nomor > data.length || data[nomor - 1] == null) {
            System.out.println("[!] Tidak ada To-Do pada posisi tersebut.");
            return false;
        }

        for (Todo item : data) {
            if (item != null && baru.getTitle().equalsIgnoreCase(item.getTitle()) && item != data[nomor - 1]) {
                System.out.println("[!] To-Do '" + baru.getTitle() + "' telah tersedia.");
                return false;
            }
        }

        Todo todoLama = data[nomor - 1];
        baru.setId(todoLama.getId());  

        data[nomor - 1] = baru;

        return true;
    }




    private boolean isFull() {
        var isFull = true;
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                // model masih ada yang kosong
                isFull = false;
                break;
            }
        }
        return isFull;
    }

    private void resizeIfFull() {
        // Jika penuh, resize ukuran array 2x lipat
        if (isFull()) {
            var temp = data;
            data = new Todo[data.length * 2];
            for (int i = 0; i < temp.length; i++) {
                data[i] = temp[i];
            }
        }
    }


}