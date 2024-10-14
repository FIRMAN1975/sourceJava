package service;


import entity.Todo;

public interface TodoService {
    void serviceShowTodo();

    void serviceAddTodo(String title);

    void serviceRemoveTodo(Integer id);

    void serviceUpdateTodo(Integer idTodo, String newTitle, boolean newStatus);

    Todo serviceGetTodoById(Integer id);

    Todo[] serviceGetAllTodos();

    void serviceSortIDTodo(boolean ascending);

    void serviceSortTitleTodo(boolean ascending);

    void serviceSearchTodo(String targeString);
    
}
