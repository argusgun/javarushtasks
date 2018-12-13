package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class UsersView implements View {
    private Controller controller=new Controller();

    @Override
    public void refresh(ModelData modelData) {
        //FakeModel model1 = new FakeModel();
        //controller.setModel(model1);
       // controller.onShowAllUsers();
        if(modelData.isDisplayDeletedUserList()) {
            System.out.println("All deleted users:");
            for (User user : modelData.getUsers()) {
                System.out.println("\t" + user.toString());
            }
            System.out.println("===================================================");
        }
        else
        {
            System.out.println("All users:");
            for (User user : modelData.getUsers()) {
                System.out.println("\t" + user.toString());
            }
            System.out.println("===================================================");
        }
    }

    public void fireEventOpenUserEditForm(long id) {
        controller.onOpenUserEditForm(id);
    }

    public void fireEventShowDeletedUsers() {
        controller.onShowAllDeletedUsers();
    }

    public void fireEventShowAllUsers()
    {
        controller.onShowAllUsers();
    }

    @Override
    public void setController(Controller controller) {
        this.controller=controller;
    }
}
