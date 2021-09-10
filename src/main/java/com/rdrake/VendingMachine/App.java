package com.rdrake.VendingMachine;

import com.rdrake.VendingMachine.controller.VendingController;
import com.rdrake.VendingMachine.dao.*;
import com.rdrake.VendingMachine.service.*;
import com.rdrake.VendingMachine.service.VendingServiceLayerImpl;
import com.rdrake.VendingMachine.ui.*;

public class App {
    public static void main(String[] args) {
        UserIO myIo = new UserIOConsoleImpl();
        VendingView myView = new VendingView(myIo);
        VendingDao myDao = new VendingDaoFileImpl();
        VendingAuditDao myAuditDao = new VendingAuditDaoFileImpl();
        VendingServiceLayer myService = new VendingServiceLayerImpl(myDao, myAuditDao);
        VendingController controller = new VendingController(myService, myView);
        controller.run();
    }
}
