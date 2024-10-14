package test.view;

import entity.Todo;
import repository.TodoRepository;
import repository.TodoRepositoryImpl;
import service.TodoService;
import service.TodoServiceImpl;
import view.TodoView;

public class TodoViewTest {

    public static void main(String[] args) {

        testViewShowTodo();

        testViewAddTodo();

        testViewRemoveTodo();

    }

    public static void testViewShowTodo() {

        System.out.println("Test View Show Todo:");

        Todo.total = 0;

        TodoRepository todoRepository = new TodoRepositoryImpl();

        TodoService todoService = new TodoServiceImpl(todoRepository);

        TodoView todoView = new TodoView(todoService);

        todoService.serviceAddTodo("Belajar PBO");

        todoService.serviceAddTodo("Belajar BASDAT");

        todoService.serviceAddTodo("Belajar AOK");

        todoService.serviceAddTodo("Belajar SISDIG");

        todoView.viewShowTodo();

        System.out.println();

    }

    public static void testViewAddTodo() {

        System.out.println("Test View Add Todo:");

        Todo.total = 0;

        TodoRepository todoRepository = new TodoRepositoryImpl();

        TodoService todoService = new TodoServiceImpl(todoRepository);

        TodoView todoView = new TodoView(todoService);

        todoView.viewAddTodo();

        todoService.serviceShowTodo();

        todoView.viewAddTodo();

        todoService.serviceShowTodo();

        System.out.println();

    }

    public static void testViewRemoveTodo() {

        System.out.println("Test View Remove Todo:");

        Todo.total = 0;

        TodoRepository todoRepository = new TodoRepositoryImpl();

        TodoService todoService = new TodoServiceImpl(todoRepository);

        TodoView todoView = new TodoView(todoService);

        todoService.serviceAddTodo("Belajar PBO");

        todoService.serviceAddTodo("Belajar BASDAT");

        todoService.serviceAddTodo("Belajar AOK");

        todoService.serviceAddTodo("Belajar SISDIG");

        todoService.serviceShowTodo();

        todoView.viewRemoveTodo();

        todoService.serviceShowTodo();

        todoView.viewRemoveTodo();

        todoService.serviceShowTodo();

        System.out.println();

    }
}
