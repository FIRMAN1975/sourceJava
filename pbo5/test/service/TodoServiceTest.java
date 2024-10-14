package test.service;

import entity.Todo;
import repository.TodoRepository;
import repository.TodoRepositoryImpl;
import service.TodoService;
import service.TodoServiceImpl;

public class TodoServiceTest {
    public static void main(String[] args) {
        testShowTodo();
        testAddTodo();
        testRemoveTodo();
    }

    public static void testShowTodo() {
        System.out.println("Test Show Todo:");
        TodoRepositoryImpl todoRepositoryImpl = new TodoRepositoryImpl();
        todoRepositoryImpl.data[0] = new Todo("Belajar PBO");
        todoRepositoryImpl.data[1] = new Todo("Belajar BASDAT");
        todoRepositoryImpl.data[2] = new Todo("Belajar AOK");
        todoRepositoryImpl.data[3] = new Todo("Belajar SISDIG");
        TodoService todoService = new TodoServiceImpl(todoRepositoryImpl);
        todoService.serviceShowTodo();
        System.out.println();
    }

    public static void testAddTodo() {
        System.out.println("Test Add Todo:");
        TodoRepository todoRepository = new TodoRepositoryImpl();
        TodoService todoService = new TodoServiceImpl(todoRepository);
        todoService.serviceAddTodo("Belajar PBO");
        todoService.serviceAddTodo("Belajar BASDAT");
        todoService.serviceAddTodo("Belajar AOK");
        todoService.serviceAddTodo("Belajar SISDIG");
        todoService.serviceShowTodo();
        System.out.println();
    }

    public static void testRemoveTodo() {
        System.out.println("Test Remove Todo:");
        TodoRepository todoRepository = new TodoRepositoryImpl();
        TodoService todoService = new TodoServiceImpl(todoRepository);
        todoService.serviceAddTodo("Belajar PBO");
        todoService.serviceAddTodo("Belajar BASDAT");
        todoService.serviceAddTodo("Belajar AOK");
        todoService.serviceAddTodo("Belajar SISDIG");
        todoService.serviceShowTodo();
        // Menghapus data yang tidak tersedia
        todoService.serviceRemoveTodo(5);
        // Menghapus data yang tersedia
        todoService.serviceRemoveTodo(3);
        todoService.serviceShowTodo();
        todoService.serviceRemoveTodo(1);
        todoService.serviceRemoveTodo(2);
        todoService.serviceShowTodo();
        todoService.serviceRemoveTodo(4);
        todoService.serviceShowTodo();
        System.out.println();
    }
}
