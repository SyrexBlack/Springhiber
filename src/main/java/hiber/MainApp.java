package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Toyota", 1);
      Car car2 = new Car("BMW", 5);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      user1.setCar(car1);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      user2.setCar(car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         if (user.getCar() != null) {
            System.out.println("Car Model = " + user.getCar().getModel());
            System.out.println("Car Series = " + user.getCar().getSeries());
         }
         System.out.println();
      }

      User foundUser = userService.getUserByCar("BMW", 5);
      if (foundUser != null) {
         System.out.println("Найден пользователь с машиной BMW серии 5:");
         System.out.println("Id = " + foundUser.getId());
         System.out.println("First Name = " + foundUser.getFirstName());
         System.out.println("Last Name = " + foundUser.getLastName());
         System.out.println("Email = " + foundUser.getEmail());
      } else {
         System.out.println("Пользователь с машиной BMW серии 5 не найден.");
      }

      context.close();
   }
}
