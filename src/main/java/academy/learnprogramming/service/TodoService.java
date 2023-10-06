package academy.learnprogramming.service;

import academy.learnprogramming.entity.Todo;
import academy.learnprogramming.entity.User;

import java.util.List;
import java.util.Map;
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

    @Inject
    private SecurityUtil securityUtil;

    private String email;

    @PostConstruct
    private void init() {
        //TODO
        email = "";
    }

    public User saveUser(User user) {

        Integer count = (Integer) queryService.countUserByEmail(user.getEmail()).get(0);

        if (user.getId() == null && count == 0) {
            Map<String, String> credMap = securityUtil.hashPassword(user.getPassword());

            user.setPassword(credMap.get(SecurityUtil.HASHED_PASSWORD_KEY));
            user.setSalt(credMap.get(SecurityUtil.SALT_KEY));

            entityManager.persist(user);
            credMap.clear();
        }
        return user;
    }


    public Todo createTodo(Todo todo) {
        //Persist into db
        User userByEmail = queryService.findUserByEmail(email);

        if (userByEmail != null) {
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
