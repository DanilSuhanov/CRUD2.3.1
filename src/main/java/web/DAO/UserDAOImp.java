package web.DAO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserDAOImp implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("from " + User.class.getSimpleName(), User.class).getResultList();
    }

    @Override
    public User getUser(long id) {
        User e = entityManager.find(User.class, id);
        return e;
    }

    @Override
    public void update(long id, User user) {
        User oldUser = entityManager.find(User.class, id);
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setAge(user.getAge());
    }

    @Override
    public void delete(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
