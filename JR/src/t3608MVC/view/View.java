package t3608MVC.view;


import t3608MVC.controller.Controller;
import t3608MVC.model.ModelData;

public interface View {
    void refresh(ModelData modelData);
    void setController(Controller controller);
}
