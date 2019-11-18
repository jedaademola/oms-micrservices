package com.wawa.oms.service;

import com.wawa.oms.dao.UserDao;
import com.wawa.oms.exception.MicroServiceException;
import com.wawa.oms.model.document.Address;
import com.wawa.oms.model.document.User;
import com.wawa.oms.util.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserDao userDao;

    public UserService( UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers() {
        try {
          return userDao.getAllUsers();
        } catch(MicroServiceException ex) {
            LOGGER.error(ex.getMessage());
            LoggerUtil.logError(LOGGER, ex);

        }
        return new ArrayList<>();
    }


    public User getUserById(String userId) {
        try {
          return userDao.getUserById(userId);
        } catch(MicroServiceException ex) {
            LOGGER.error(ex.getMessage());
            LoggerUtil.logError(LOGGER, ex);
        }
        return null;
    }

    public void deleteUser(User user) {
        try {
            userDao.deleteUser(user);
        } catch (MicroServiceException ex) {
            LOGGER.error(ex.getMessage());
            LoggerUtil.logError(LOGGER, ex);
        }
    }

    public User addNewUser(User user) {
        try {
           return userDao.addNewUser(user);
        } catch(MicroServiceException ex) {
            LOGGER.error(ex.getMessage());
            LoggerUtil.logError(LOGGER, ex);
        }
        return null;
    }

    public Address addUserAddress(Address address) {
        try {
           return userDao.addUserAddress(address);
        } catch(MicroServiceException ex) {
            LOGGER.error(ex.getMessage());
            LoggerUtil.logError(LOGGER, ex);
        }
        return null;
    }

    public Object getAllUserSettings(String userId) {
        return userDao.getAllUserSettings(userId);
    }
    public String getUserSetting(String userId, String key) {
        try {
             return userDao.getUserSetting( userId,  key);
        } catch(MicroServiceException ex) {
            LOGGER.error(ex.getMessage());
            LoggerUtil.logError(LOGGER, ex);
        }
        return null;
    }

    public String addUserSetting(String userId, String key, String value) {
        try {
            return userDao.addUserSetting( userId,  key,  value);
        } catch(MicroServiceException ex) {
            LOGGER.error(ex.getMessage());
            LoggerUtil.logError(LOGGER, ex);
        }
        return null;
    }
}
