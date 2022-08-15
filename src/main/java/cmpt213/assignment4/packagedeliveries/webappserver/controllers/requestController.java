package cmpt213.assignment4.packagedeliveries.webappserver.controllers;


import cmpt213.assignment4.packagedeliveries.webappserver.control.PackageManagement;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Book;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Electronic;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Package;
import cmpt213.assignment4.packagedeliveries.webappserver.model.Perishable;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

import static cmpt213.assignment4.packagedeliveries.webappserver.control.PackageManagement.*;

@RestController
public class requestController {

    @GetMapping("/ping")
    public String getDefaultMessage() {
        return "System is up!";
    }


    @GetMapping("/listAll")
    public List<Package> getPackageList() {
        return packageList;
    }

    //private AtomicLong nextId = new AtomicLong();
    @PostMapping("/addBook")
    public Book createNewPackage(@RequestBody Book newBook) {
        packageList.add(newBook);
        Collections.sort(packageList);
        return newBook;
    }

    @PostMapping("/addPerishable")
    public Perishable createNewPackage(@RequestBody Perishable newPerishable) {
        packageList.add(newPerishable);
        Collections.sort(packageList);
        return newPerishable;
    }

    @PostMapping("/addElectronic")
    public Electronic createNewPackage(@RequestBody Electronic newElectronic) {
        packageList.add(newElectronic);
        Collections.sort(packageList);
        return newElectronic;
    }

    @PostMapping("/removePackage/{id}")
    public List<Package> removeAPackage(@PathVariable("id") int packageNumber) {
        if (packageNumber > packageList.size())
        {
            System.out.println("The number you have inserted is larger than packagelist's size");
        }
        else
        {
            packageList.remove(packageNumber);
        }
        return packageList;
    }


    @GetMapping("/listOverduePackage")
    public List<Package> listOverDuePackages() {
        return ListOverduePackages(packageList);
    }


    @GetMapping("/listUpcomingPackage")
    public List<Package> listUpcomingPackages() {
        return ListUpcomingPackages(packageList);
    }

    @PostMapping("/markPackageAsDelivered/{id}")
    public List<Package> changeTheStateOfIsDeliveredForAPackage(@PathVariable("id") int packageNumber){
        if (packageNumber > packageList.size())
        {
            System.out.println("The number you have inserted is larger than packagelist's size");
        }
        else
        {
            if(packageList.get(packageNumber).getIsDelivered() == true)
            {
                packageList.get(packageNumber).setDelivered(false);
            }
            else if(packageList.get(packageNumber).getIsDelivered() == false)
            {
                packageList.get(packageNumber).setDelivered(true);
            }
        }
        return packageList;
    }

    @GetMapping("/exit")
    public void exit() {
        PackageManagement.WriteToJSON();
    }


    public requestController() {
        PackageManagement.ReadFromJSONorCreatePackageList();
    }
}
