package t3608MVC.view;


import t3608MVC.bean.User;
import t3608MVC.controller.Controller;
import t3608MVC.model.ModelData;


public class UsersView implements View {
    private Controller controller;

    @Override
    public void refresh(ModelData modelData) {
        if (!modelData.isDisplayDeletedUserList()) {
            System.out.println("All users:");
        }
        if (modelData.isDisplayDeletedUserList()) {
            System.out.println("All deleted users:");
        }
        for(User user:modelData.getUsers()){
            String s = String.format("\tUser{name='%s', id=%d, level=%d}", user.getName(), user.getId(), user.getLevel());
            System.out.println(s);
        }
        System.out.println("===================================================");
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void fireEventShowAllUsers(){
        controller.onShowAllUsers();
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }
}

