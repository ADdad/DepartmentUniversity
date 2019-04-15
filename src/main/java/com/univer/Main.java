package com.univer;

import com.univer.presenter.chuhalova.ScienceTopic_Form;
import com.univer.presenter.starostiuk.MainForm;
import com.univer.util.ServiceFactory;

public class Main {

    public static void main(String[] args) {

       // new MainForm(ServiceFactory.getMasterService());
        new ScienceTopic_Form(ServiceFactory.getMasterService(), ServiceFactory.getChuhalovaService()).setVisible(true);
    }

}
