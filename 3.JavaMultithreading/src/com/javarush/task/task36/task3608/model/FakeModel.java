package com.javarush.task.task36.task3608.model;

import com.javarush.task.task36.task3608.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Борозденец on 05.07.2017.
 */
public class FakeModel implements Model {

    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("A", 1, 1));
        users.add(new User("B", 2, 1));
        users.add(new User("C", 3, 2));
        users.add(new User("C", 4, 2));
        users.add(new User("C", 5, 3));
        users.add(new User("C", 6, 4));

        modelData.setUsers(users);
    }

    @Override
    public void loadDeletedUsers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadUserById(long userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteUserById(long userId) {
        throw new UnsupportedOperationException();
    }
}
