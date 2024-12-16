package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   @Autowired
   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession()
              .createQuery("from User", User.class)
              .getResultList();
   }

   @Override
   public User getUserByCarModelAndSeries(String model, int series) {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
              .createQuery("SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      try {
         return query.getSingleResult();
      } catch (javax.persistence.NoResultException e) {
         return null;
      }
   }
}
