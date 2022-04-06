package ru.kata.spring.boot_security.demo.dao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
        return findByEmail(user.getEmail());
    }

    @Override
    public List<User> userList() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);

        return query.getResultList();
    }

    @Override
    public User findById(long id) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.roles where u.id=:id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public User update(long id, User userUpdated) {
        User userForUpdate = findById(id);
        userForUpdate.setEmail(userUpdated.getEmail());
        userForUpdate.setPassword(passwordEncoder.encode(userUpdated.getPassword()));
        userForUpdate.setFirstName(userUpdated.getFirstName());
        userForUpdate.setLastName(userUpdated.getLastName());
        userForUpdate.setAge(userUpdated.getAge());
        userForUpdate.setRoles(userUpdated.getRoles());
        entityManager.merge(userForUpdate);
        return userForUpdate;
    }

    @Override
    public User findByEmail(String email) {
        TypedQuery<User> query = entityManager.createQuery("select u from User u join fetch u.roles where u.email=:email", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

}
