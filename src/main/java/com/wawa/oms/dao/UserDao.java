package com.wawa.oms.dao;


import com.wawa.oms.model.document.Address;
import com.wawa.oms.model.document.User;

import java.util.List;

public interface UserDao {

    List<User> getAllUsers();

    User getUserById(String userId);

    User addNewUser(User user);

    Address addUserAddress(Address address);

    Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);

    void deleteUser(User user);
}
