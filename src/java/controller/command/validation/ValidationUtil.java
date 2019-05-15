package controller.command.validation;


import model.exception.WrongDataException;
import model.service.UserService;
import model.service.implement.UserServiceImpl;


public class ValidationUtil {

    public boolean verificate(String name, String email) throws WrongDataException {
        return InputValid.isEnglishValid(name) && InputValid.isEmailValid(email);


    }

    public boolean userExist(String email) {
        UserService userService = new UserServiceImpl();
        return userService.findByEmail(email) == null;
    }

}
