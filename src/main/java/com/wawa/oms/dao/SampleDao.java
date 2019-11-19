package com.wawa.oms.dao;


import com.wawa.oms.model.document.Address;
import com.wawa.oms.model.document.Sample;

import java.util.List;

public interface SampleDao {

    List<Sample> getAllUsers();

    Sample getUserById(String userId);

    Sample addNewUser(Sample user);

    Address addUserAddress(Address address);

    Object getAllUserSettings(String userId);

    String getUserSetting(String userId, String key);

    String addUserSetting(String userId, String key, String value);

    void deleteUser(Sample user);

    List<Sample> getAllUsersPaged(int pageNumber, int pageSize);
}
