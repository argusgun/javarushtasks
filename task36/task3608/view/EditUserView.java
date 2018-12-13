package com.javarush.task.task36.task3608.view;

import com.javarush.task.task36.task3608.bean.User;
import com.javarush.task.task36.task3608.controller.Controller;
import com.javarush.task.task36.task3608.model.ModelData;

public class EditUserView implements View {
    private Controller controller=new Controller();

    @Override
    public void refresh(ModelData modelData) {
        //FakeModel model1 = new FakeModel();
        //controller.setModel(model1);
        // controller.onShowAllUsers();
        System.out.println("User to be edited:");
            System.out.println("\t"+modelData.getActiveUser().toString());
        System.out.println("===================================================");
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
