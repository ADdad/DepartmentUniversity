package com.univer;

import com.univer.presenter.MainForm;
import com.univer.util.ServiceFactory;

public class Main {

    public static void main(String[] args) {

        new MainForm(ServiceFactory.getMasterService());

    }

}
