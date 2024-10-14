package repository;

import entity.Todo;

public interface TodoRepository {
    Todo[] repoGetAllTodo();

    void repoAddTodo(Todo newTodo);

    boolean repoRemoveTodo(Integer idTodo);

    boolean repoUpdateTodo(Integer nomor , Todo baru );
}