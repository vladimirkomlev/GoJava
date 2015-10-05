package com.Airbnb.app;
import com.Airbnb.app.common.Observer;
import com.Airbnb.app.common.Subject;
import com.Airbnb.app.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by romanroma on 26.09.15.
 */
public class Airbnb implements Subject {

    public static Map<Integer, Apartment> apartments = new HashMap<Integer, Apartment>();

    public void registerHost (String name, String surname, String email){
        User host = new Host(name, surname, email);
        if (host.validation()) {
            User.hosts.put(host.getId(),host);
        }
        else System.out.println("Please enter valid data");
    }

    public void registerClient (String name, String surname, String email){
        User client = new Client(name, surname, email);
        if (client.validation()) {
            User.clients.put(client.getId(), client);
        }
        else System.out.println("Please enter valid data");
    }

    public void removeClient (int id){
        User.clients.remove(id);
    }

    public void removeHost (int id){
        User.hosts.remove(id);
    }

    public void notifyAll (String message){
        /*for (Observer observer : User.clients){
            observer.update(message);
        }
        for (Observer observer : hostList){
            observer.update(message);
        }*/
    }

    public int createApartment (int hostId, String city, ApartType apartType, boolean reserved){
        if (hostId<1) {
            return -1;
        }

        Host host = (Host) User.hosts.get(hostId);
        if (host == null){
            return -1;
        }

        Apartment apartment = new Apartment(host,city,apartType,reserved);
        if (apartment.validation()){
            apartments.put(apartment.getId(),apartment);
            return apartment.getId();
        }
        return -1;
    }


}
