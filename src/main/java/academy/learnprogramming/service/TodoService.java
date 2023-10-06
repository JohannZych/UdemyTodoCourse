package academy.learnprogramming.service;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TodoService {

    @Inject
    private EntityManager entityManager;

    @Inject
    private QueryService queryService;

    private String email;

    @PostConstruct
    private void init(){
        //TODO
        email = "";
    }

    public User saveUser(User user){
        entityManager.persist(user);

        return user;
    }


    public Todo createTodo(Todo todo) {
        //Persist into db
        User userByEmail =  queryService.findUserByEmail(email);

        if (userByEmail != null){
            todo.setTodoOwner(userByEmail);
            entityManager.persist(todo);
        }
        return todo;
    }


    public Todo updateTodo(Todo todo) {
        entityManager.merge(todo);
        return todo;
    }


    public Todo findToDoById(Long id) {
        return entityManager.find(Todo.class, id);
    }


    public List<Todo> getTodos() {
        return entityManager.createQuery("SELECT t from Todo t", Todo.class).getResultList();
    }
}
