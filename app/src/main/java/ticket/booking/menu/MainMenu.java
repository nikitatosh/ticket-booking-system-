package ticket.booking.menu;
import ticket.booking.service.UserService;

import java.io.IOException;

import ticket.booking.entities.User;
import ticket.booking.service.UserService;
import ticket.booking.utils.UserServiceUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class MainMenu {
    private final Scanner sc=new Scanner(System.in);

        private final UserService userService;

        public MainMenu() throws IOException {
            this.userService = new UserService();
        }
        public void start(){
            while(true){
                System.out.println("\n  Railway Booking ");
                System.out.println("1. Register");

                System.out.println("2. Login");

                System.out.println("3. Exit");

                System.out.print("Choice : ");
                int choice=sc.nextInt();
                sc.nextLine();
                switch (choice){
                    case 1 -> register();
                    case 2 -> login();
                    case 3 -> {
                        System.out.println("Thank You!");
                        return;
                    }
                    default -> System.out.println("Invalid Choice");
                }
            }
        }
         private void register(){

            System.out.print("Username : ");

            String username = sc.nextLine();

            System.out.print("Password : ");

            String password = sc.nextLine();

            User user = new User(
                    username,
                    UserServiceUtil.hashPassword(password),
                    new ArrayList<>(),
                    UUID.randomUUID().toString() //generate unique id
            );

            if(userService.register(user))

                System.out.println("Registration Successful");

            else

                System.out.println("Username Already Exists");

    }


    private void login(){

        System.out.print("Username : ");

        String username = sc.nextLine();

        System.out.print("Password : ");

        String password = sc.nextLine();

        User user = userService.login(username,password);

        if(user==null){

            System.out.println("Invalid Credentials");

            return;

        }

        System.out.println("\nWelcome " + user.getName());

        userDashboard();

    }

    private void userDashboard(){

        while(true){

            System.out.println();

            System.out.println("===== User Dashboard =====");

            System.out.println("1. Search Train");

            System.out.println("2. My Bookings");

            System.out.println("3. Book Ticket");

            System.out.println("4. Cancel Ticket");

            System.out.println("5. Logout");

            System.out.print("Choice : ");

            int choice = sc.nextInt();

            sc.nextLine();

            switch(choice){

                case 1 ->
                        System.out.println("Search Train");

                case 2 ->
                        System.out.println("My Bookings");

                case 3 ->
                        System.out.println("Book Ticket");

                case 4 ->
                        System.out.println("Cancel Ticket");

                case 5 ->{

                    userService.logout();

                    System.out.println("Logged Out Successfully");

                    return;
                }

                default ->
                        System.out.println("Invalid Choice");

            }

        }

    }

}