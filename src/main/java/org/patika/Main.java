package org.patika;

import org.patika.application.OrderApplication;
import org.patika.utils.DebugUtils;
import org.patika.application.RequiredObjects;


public class Main {

    public static void main(String[] args) {
        //CREATE A SINGLETON OBJECT TO STORE SERVICES, REPOSITORIES, APPLICATION OBJECT, EXAMPLE DATAS OBJECT
        RequiredObjects requiredObjects = RequiredObjects.getInstance();
        //ADD EXAMPLE DATA TO THE DATABASE
        DebugUtils.addExampleData(
                requiredObjects.getClientService(),
                requiredObjects.getItemService(),
                requiredObjects.getInvoiceService(),
                requiredObjects.getCompanyService(),
                requiredObjects.getExampleDatas()
        );
        //GET APPLICATION OBJECT
        OrderApplication orderApplication = requiredObjects.getOrderApplication();
        //OPEN MAIN MENU
        orderApplication.MainMenu();
    }

}

