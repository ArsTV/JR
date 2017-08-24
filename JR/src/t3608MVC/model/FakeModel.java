package t3608MVC.model;

import t3608MVC.bean.User;

import java.util.Arrays;

public class FakeModel implements Model {
    private ModelData modelData = new ModelData();

    @Override
    public ModelData getModelData() {
        return this.modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setUsers(Arrays.asList(new User("A", 1, 1),
                new User("B", 2, 2)));

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
    public void deleteUserById(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changeUserData(String name, long id, int level) {
        throw new UnsupportedOperationException();
    }
}

