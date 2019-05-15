package model.service;

import model.entity.User;

public interface UserService extends GenenralService<User> {
    User loginUser(String login, String password) ;
    User findByEmail(String email);
}
