package com.service;

import com.model.*;
import com.repository.DriverRepository;
import com.repository.PlanetRepository;
import com.repository.ShipRepository;
import com.utils.Messages;
import com.utils.TimeComparator;

import java.util.*;

public class MainService {
    private DriverRepository driverRepository;
    private ShipRepository shipRepository;
    private PlanetRepository planetRepository;

    public MainService(DriverRepository driverRepository, ShipRepository shipRepository, PlanetRepository planetRepository){
        this.driverRepository = driverRepository;
        this.shipRepository = shipRepository;
        this.planetRepository = planetRepository;
    }

    public void start(){
        printDescription();
    }

    private Driver readDriverData(Scanner scan) {
        Driver driver = null;
        System.out.println("\n");
        printDrivers();
        System.out.println("\n");

        System.out.println(Messages.ChooseDriverMessage);

        do {
            String driverName = scan.nextLine();
            driver = getTheDriver(driverName);

            if(driver == null) {
                System.out.println(Messages.NoDriverFoundMessage);
            }
        } while(driver == null);

        return driver;
    }

    private int readCargoData(Scanner scan) {
        int cargo = -1;
        System.out.println(Messages.ChooseCargoMessage);
        do {
            try {
                cargo = scan.nextInt();
                if(cargo < 0 ) {
                    System.out.println(Messages.InputNotPositiveInteger);
                    System.out.println("\n");
                }
            } catch (InputMismatchException exception) {
                System.out.println(Messages.InvalidInputForInteger);
                System.out.println("\n");
                scan.nextLine();
            }
        } while (cargo < 0);

        return cargo;
    }

    private Planet readPlanetData(Scanner scan) {
        Planet planet = null;

        System.out.println("\n");
        printPlanets();
        System.out.println("\n");
        System.out.println(Messages.ChoosePlanetMessage);

        do {
            String planetText = scan.next();
            planet = findTheDesignatedPlante(planetText);

            if(planet == null) {
                System.out.println(Messages.PlanetNotFoundMessage);
            }
        } while(planet == null);

        return planet;
    }


    private void choosingOptions(Scanner scan) {
        while(true) {
            Driver driver = readDriverData(scan);
            int cargo = readCargoData(scan);
            Planet planet = readPlanetData(scan);

            System.out.println("\n");
            PriorityQueue<TransportationInfo> transportationInfos = getTransportationInfo(driver, cargo, planet);
            printAllTransportationInfo(transportationInfos);
            System.out.println("\n");

            scan.nextLine();
            System.out.println(Messages.DoYouWishToContinueMessage);
            String option = scan.nextLine();

            if(option.equals("n")) {
                System.out.println(Messages.GoodbyeMessage);
                break;
            }
        }
    }

    private void printDescription() {
        Scanner scan = new Scanner(System.in);
        System.out.println(Messages.ApplicationDescriptionMessage);

        System.out.println("\n");
        System.out.println(Messages.GetStartedMessage);
        String option = scan.nextLine();

        if(option.equals("y")) {
            choosingOptions(scan);
        } else if(option.equals("n")) {
            System.out.println(Messages.GoodbyeMessage);
        }
    }


    private void printDrivers() {
        Map<String,Driver> drivers = driverRepository.getAll();

        for(String key : drivers.keySet()) {
            System.out.println(drivers.get(key));
        }
    }

    private void printPlanets() {
        Map<String,Planet> planets = planetRepository.getAll();

        for(String key: planets.keySet()) {
            System.out.println(planets.get(key));
        }
    }


    private PriorityQueue<TransportationInfo> getTransportationInfo(Driver driver, int cargo, Planet planet) {
        List<Ship> shipList = findShipsForTheDriver(driver.getTypeList());

        if(shipList.isEmpty()) {
            System.out.println(Messages.NoShipsFoundMessage);
            return null;
        }

        Comparator<TransportationInfo> comparator = new TimeComparator();
        PriorityQueue<TransportationInfo> queue = new PriorityQueue<TransportationInfo>(11, comparator);

        for(Ship ship: shipList) {
            queue.add( calculateTimeAndTrips(driver.getName(), cargo, planet, ship) );
        }

        return queue;
    }


    private Driver getTheDriver(String name) {
        return driverRepository.findDriver(name);
    }

    private List<Ship> findShipsForTheDriver(List<String> shipTypes) {
        return shipRepository.findShips(shipTypes);
    }

    private Planet findTheDesignatedPlante(String planet) {
        return planetRepository.findPlanet(planet);
    }

    private TransportationInfo calculateTimeAndTrips(String driver, int cargo, Planet planet , Ship ship) {
        double timeRequiredToReachThePlanet;

        if(cargo > 0) {
            timeRequiredToReachThePlanet = planet.getDistance() / ship.getSpeed();
        } else {
            timeRequiredToReachThePlanet = 0;
        }

        double carg = cargo;
        int numberOfTrips =(int)  Math.ceil(carg / ship.getMaxCargoWeight());   // without returning after delivering all the cargo
        int numberOfJourneys = numberOfTrips + (numberOfTrips-1);   //go and returning , the last part is -1 becouse we don't count the last return
        double numberOfHoursNeededToDeliverTheCargo = numberOfJourneys * timeRequiredToReachThePlanet;

        TransportationInfo transportationInfo = new TransportationInfo(driver , carg , planet.getName(), numberOfHoursNeededToDeliverTheCargo, ship.getName(), numberOfTrips );

        return transportationInfo;
    }


    private  void printAllTransportationInfo( PriorityQueue<TransportationInfo> transportationInfos) {
        for(TransportationInfo trans: transportationInfos) {
            System.out.println(trans);
        }
    }
}