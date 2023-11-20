package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
@Override
@SuppressWarnings("uncheked")
   public User getUserWithCarByModelAndSeries (String model, int series) {
      TypedQuery<User> userQuery= sessionFactory.getCurrentSession().
              createQuery("from User where car.model = :model and car.series = :series")
      .setParameter("model", model).setParameter("series",series);
      return userQuery.getResultList().size() > 0 ? userQuery.getResultList().get(0) : null;
   }
}
