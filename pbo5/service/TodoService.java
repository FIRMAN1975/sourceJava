package service;

import entity.Todo;

public interface TodoService {

        void serviceShowTodo();

        void serviceAddTodo(String todo);

        void serviceRemoveTodo(Integer id);

        Todo serviceGetTodoById(Integer id);

        Todo[] serviceGetAllTodos();

        void serviceUpdateTodo(Integer id, String newTitle, boolean newStatus);
}