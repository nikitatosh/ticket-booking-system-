package ticket.booking.service;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.entities.User;
import ticket.booking.utils.UserServiceUtil;

public class UserService {
    private List<User> userList;
    private User currentUser;

    private final ObjectMapper objectMapper=new ObjectMapper();

    private static final String USER_PATH="app/src/main/java/ticket/booking/localDb/users.json";

    //constructor loads users from localdb
    public UserService() throws IOException{
        loadUser();
    }
    //read from localDb
    public void loadUser() throws IOException{
        //userList=objectMapper.readValue(new File(USER_PATH),new TypeReference<List<User>>(){});
        File file = new File(USER_PATH);
        //for debug (file path issue)
       // System.out.println("Path: " + file.getAbsolutePath());
       // System.out.println("Exists: " + file.exists());
      //  System.out.println("Length: " + file.length());

        userList = objectMapper.readValue(
                file,
                new TypeReference<List<User>>() {}
        );

    }
    //write in localDb
    public void saveUsers() throws IOException{
        objectMapper.writeValue(new File(USER_PATH),userList);
    }
    //sign up
    public boolean register(User user){
        //Check if the username already exists
        boolean exit=userList.stream().anyMatch(u-> u.getName().equalsIgnoreCase(user.getName()));

        if(exit)
            return false;
        userList.add(user);
        try{
            saveUsers();
            return true;
        }catch (IOException e){
            return  false;
        }
    }

    public User login(String userName,String password){
        Optional<User> foundUser=userList.stream().filter(u-> u.getName().equalsIgnoreCase(userName) && UserServiceUtil.checkPassword(password, u.getHashedPassword())).findFirst();

        if(foundUser.isPresent()){
            currentUser=foundUser.get();
            return currentUser;
        }

        return null;
    }

    public void logout(){
        currentUser=null;

    }


    public User getCurrentUser(){
        return currentUser;
    }

}

