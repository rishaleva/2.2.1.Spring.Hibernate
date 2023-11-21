package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Peugeot", 308);
      Car car2 = new Car("Audi", 3);
      Car car3 = new Car("Mazda", 6);
      Car car4 = new Car("BMW", 7);

      User user1 = new User("Irina", "Inozemtseva", "irinainoz@gmail.ru");
      User user2 = new User("Paul", "Landers", "paullanders@gmail.ru");
      User user3 = new User("Corey", "Taylor", "cortay@gmail.ru");
      User user4 = new User("Pepe", "Pepovich", "pepe@gmail.ru");

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      System.out.println(userService.getUserWithCarByModelAndSeries("Peugeot", 308));
      userService.getUserWithCarByModelAndSeries("Audi", 3);
      userService.getUserWithCarByModelAndSeries("Mazda", 6);
      userService.getUserWithCarByModelAndSeries("BMW", 7);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println(user + " has a " + user.getCar());
      }

      context.close();
   }
}
