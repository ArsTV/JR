package t3608MVC;


import t3608MVC.controller.Controller;
import t3608MVC.model.MainModel;
import t3608MVC.model.Model;
import t3608MVC.view.EditUserView;
import t3608MVC.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();
        UsersView usersView = new UsersView();
        EditUserView editUserView = new EditUserView();

        Controller controller = new Controller();
        usersView.setController(controller);
        editUserView.setController(controller);

        controller.setModel(model);
        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);

        usersView.fireEventShowAllUsers();
        usersView.fireEventOpenUserEditForm(126);

        editUserView.fireEventUserDeleted(124L);
        editUserView.fireEventUserChanged( "Ars", 126, 9);

        usersView.fireEventShowDeletedUsers();
    }
}