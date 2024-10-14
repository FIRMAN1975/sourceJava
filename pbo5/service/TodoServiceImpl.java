package service;

import entity.Todo;
import repository.TodoRepository;
import java.util.HashSet;
import java.util.Set;


public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {

        this.todoRepository = todoRepository;

    }

    @Override

    public void serviceShowTodo() {

        Todo[] todos = todoRepository.repoGetAllTodo();

        System.out.println("Daftar Todo:");

        int counter = 0;

        for (Todo todo : todos) {

            if (todo != null) {

                counter++;

                System.out.println(todo);

            } else {

                break;

            }

        }

        if (counter <= 0) {

            System.out.println("- Data todo belum tersedia!");

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
        Todo[] todos = todoRepository.repoGetAllTodo();
        for (Todo todo : todos) {
            if (todo != null && todo.getId() == id) {
                return todo; 
            }
        }
        return null; 
    }

    @Override
    public Todo[] serviceGetAllTodos() {
        return todoRepository.repoGetAllTodo();  // Ambil semua Todo dari repository
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



}