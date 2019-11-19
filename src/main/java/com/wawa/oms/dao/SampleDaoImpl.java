package com.wawa.oms.dao;


import com.wawa.oms.model.document.Address;
import com.wawa.oms.model.document.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SampleDaoImpl implements SampleDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Sample> getAllUsers() {
        return mongoTemplate.findAll(Sample.class);
    }

    @Override
    public List<Sample> getAllUsersPaged(int pageNumber, int pageSize) {
        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize);
        Query query = new Query();
        query.with(pageableRequest);
        List<Sample> users = mongoTemplate.find(query, Sample.class);
        return users;
       /* BETWEEN
       Query query = new Query();
        query.addCriteria(Criteria.where("age").lt(50).gt(20));
        List<User> users = mongoTemplate.find(query,User.class);*/

       /* Sorting
       Query query = new Query();
        query.with(new Sort(Sort.Direction.ASC, "age"));
        List<User> users = mongoTemplate.find(query,User.class);*/
    }

    @Override
    public Sample getUserById(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Sample.class);

    }

    @Override
    public void deleteUser(Sample user) {
        mongoTemplate.remove(user);
    }

    @Override
    public Sample addNewUser(Sample user) {
        mongoTemplate.save(user);
        // Now, user object will contain the ID as well
        return user;
    }


    @Override
    public Address addUserAddress(Address address) {
        mongoTemplate.save(address);
        // Now, user object will contain the ID as well
        return address;
    }

    @Override
    public Object getAllUserSettings(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Sample user = mongoTemplate.findOne(query, Sample.class);
        return user != null ? user.getUserSettings() : "User not found.";
    }

    @Override
    public String getUserSetting(String userId, String key) {
        Query query = new Query();
        query.fields().include("userSettings");
        query.addCriteria(Criteria.where("userId").is(userId).andOperator(Criteria.where("userSettings." + key).exists(true)));
        Sample user = mongoTemplate.findOne(query, Sample.class);
        return user != null ? user.getUserSettings().get(key) : "Not found.";
    }

    @Override
    public String addUserSetting(String userId, String key, String value) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        Sample user = mongoTemplate.findOne(query, Sample.class);
        if (user != null) {
            user.getUserSettings().put(key, value);
            mongoTemplate.save(user);
            return "Key added.";
        } else {
            return "User not found.";
        }
    }
}
