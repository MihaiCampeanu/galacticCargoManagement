
import com.model.Planet;
import com.repository.DriverRepository;
import com.repository.PlanetRepository;
import com.repository.ShipRepository;
import com.service.MainService;
import com.utils.Logo;

public class MainCargoManagement {

    public static void main(String[] args){
        Logo.printLogo();
        ShipRepository shipRepository = new ShipRepository("src/ships.json");
        DriverRepository driverRepository = new DriverRepository("src/characters.json");
        PlanetRepository planetRepository = new PlanetRepository("src/planets.json");
        System.out.println("\n");
        MainService service = new MainService(driverRepository, shipRepository, planetRepository);
        service.start();

    }
}
