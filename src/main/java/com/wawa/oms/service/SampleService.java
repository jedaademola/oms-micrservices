package com.wawa.oms.service;

import com.wawa.oms.dao.SampleDao;
import com.wawa.oms.exception.MicroServiceException;
import com.wawa.oms.model.document.Address;
import com.wawa.oms.model.document.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleService {
    private static final Logger LOG = LoggerFactory.getLogger(SampleService.class);

    private final SampleDao sampleDao;

    public SampleService(SampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    public List<Sample> getAllUsers() {
        try {
            return sampleDao.getAllUsers();
        } catch(MicroServiceException ex) {
            LOG.error("Failed to fetch {}", "User List", ex);
        }
        return new ArrayList<>();
    }

    public List<Sample> getAllUsersPaged(int pageNumber, int pageSize) {
        try {
            return sampleDao.getAllUsersPaged(pageNumber, pageSize);
        } catch(MicroServiceException ex) {
            LOG.error("Failed to fetch {}", "Paged User List", ex);
        }
        return new ArrayList<>();
    }

    public Sample getUserById(String userId) {
        try {
            return sampleDao.getUserById(userId);
        } catch (MicroServiceException ex) {
            LOG.error("Failed to fetch {}", "User:" + userId, ex);
        }
        return null;
    }

    public void deleteUser(Sample user) {
        try {
            sampleDao.deleteUser(user);
        } catch (MicroServiceException ex) {
            LOG.error("Failed to delete {}", "User", ex);
        }
    }

    public Sample addNewUser(Sample user) {
        try {
            return sampleDao.addNewUser(user);
        } catch(MicroServiceException ex) {
            LOG.error("Failed to save new {}", "User", ex);
        }
        return null;
    }

    public Address addUserAddress(Address address) {
        try {
            return sampleDao.addUserAddress(address);
        } catch(MicroServiceException ex) {
            LOG.error("Failed to save new {}", "Address", ex);
        }
        return null;
    }

    public Object getAllUserSettings(String userId) {
        return sampleDao.getAllUserSettings(userId);
    }
    public String getUserSetting(String userId, String key) {
        try {
            return sampleDao.getUserSetting(userId, key);
        } catch(MicroServiceException ex) {
            LOG.error("Failed to fetch {}", "User Setting", ex);
        }
        return null;
    }

    public String addUserSetting(String userId, String key, String value) {
        try {
            return sampleDao.addUserSetting(userId, key, value);
        } catch(MicroServiceException ex) {
            LOG.error("Failed to Save new {}", "User Setting", ex);
        }
        return null;
    }
}
