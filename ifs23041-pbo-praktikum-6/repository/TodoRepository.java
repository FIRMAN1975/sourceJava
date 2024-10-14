package repository;

import java.util.ArrayList;
import entity.Todo;

public interface TodoRepository {
    ArrayList<Todo> repoGetAllTodo();

    void repoAddTodo(Todo newTodo);

    boolean repoRemoveTodo(Integer idTodo);

//    boolean repoUpdateTodo(Integer idTodo, String newTitle, Boolean newFinished);

    boolean repoUpdateTodo(Integer nomor, Todo baru);
    void repoSortIDTodo(ArrayList<Todo> data, boolean ascending);

    void repoSortTitleTodo (ArrayList<Todo> data, boolean ascending);

    void repoSearchTodo(ArrayList<Todo> data,String newTodo);
    
}
