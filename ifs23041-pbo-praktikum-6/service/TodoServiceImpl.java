package service;

import java.util.ArrayList;
import entity.Todo;
import repository.TodoRepository;

public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public void serviceShowTodo() {
        ArrayList<Todo> todos = todoRepository.repoGetAllTodo();
        System.out.println("Daftar Todo:");
        if (todos.size() <= 0) {
            System.out.println("- Data todo belum tersedia!");
            return;
        }
        for (Todo todo : todos) {
            if (todo != null) {
                System.out.println(todo);
            } else {
                break;
            }
        }
    }

    @Override
    public void serviceAddTodo(String todo) {
        Todo newTodo = new Todo(todo);
        todoRepository.repoAddTodo(newTodo);
    }

    @Override
    public void serviceRemoveTodo(Integer id) {
        boolean success = todoRepository.repoRemoveTodo(id);
        if (!success) {
            System.out.printf("[!] Gagal menghapus todo dengan ID: %d.\n", id);
            return;
        }
    }

    @Override
    public Todo serviceGetTodoById(Integer id) {
        ArrayList<Todo> todos = todoRepository.repoGetAllTodo();
        for (Todo todo : todos) {
            if (todo != null && todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    @Override
    public Todo[] serviceGetAllTodos() {
        ArrayList<Todo> todoList = todoRepository.repoGetAllTodo();  // Get all Todos from repository
        return todoList.toArray(new Todo[0]);  // Convert the ArrayList to a Todo array
    }



    public void serviceUpdateTodo(Integer id, String newTitle, boolean newStatus) {
        Todo todoToUpdate = serviceGetTodoById(id);
        if (todoToUpdate == null) {
            System.out.printf("[!] Todo dengan ID %d tidak ditemukan.\n", id);
            return;
        }

        Todo updatedTodo = new Todo();
        updatedTodo.setTitle(newTitle);
        updatedTodo.setFinished(newStatus);

        boolean success = todoRepository.repoUpdateTodo(id, updatedTodo);
//        if (success) {
//            System.out.println("[✔] Todo berhasil diperbarui.");
//        }
    }

    @Override
    public void serviceSortIDTodo(boolean ascending) {
        todoRepository.repoSortIDTodo(todoRepository.repoGetAllTodo(), ascending);

    }

    @Override
    public void serviceSortTitleTodo(boolean ascending) {
        todoRepository.repoSortTitleTodo(todoRepository.repoGetAllTodo(), ascending);
    }
    
    @Override
    public void serviceSearchTodo(String targetString) {
        ArrayList<Todo> todos = todoRepository.repoGetAllTodo();
        System.out.printf("Hasil pencarian: %s\n", targetString);

        ArrayList<Todo> foundTodos = new ArrayList<>();

        int position = 0;
        boolean found = false;
//        System.out.println("Ditemukan " + position +" Todo:");
        for (Todo todo : todos) {
            if (todo.getTitle().toLowerCase().contains(targetString.toLowerCase())) {

                foundTodos.add(todo);
                position++;  // Increment position for each found Todo
                found = true;
            }
        }


        if (foundTodos.isEmpty()) {
            System.out.println("Tidak ada todo yang sesuai dengan pencarian.");
        } else {
            System.out.println("Ditemukan " + foundTodos.size() + " Todo:");
            for (Todo todo : foundTodos) {
                System.out.println(todo);  // Print each found Todo
            }
        }



    }

}
