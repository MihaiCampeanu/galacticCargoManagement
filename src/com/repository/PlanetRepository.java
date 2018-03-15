package com.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Planet;
import com.utils.Messages;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlanetRepository implements JSONReader {
    private Map<String,Planet> planetrepo;

    public PlanetRepository(String fileName){
        this.planetrepo = new HashMap<>();
        loadDataFromJSONFile(fileName);
    }

    @Override
    public void loadDataFromJSONFile(String fileName) {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;
            ObjectMapper mapper = new ObjectMapper();

            for(Object object: jsonArray) {

                String str = object.toString();
                planetrepo.put(mapper.readValue(str, Planet.class).getName(), mapper.readValue(str, Planet.class));

            }
        }  catch ( IOException e) {
            System.out.println(Messages.FileErrorMessage);
        } catch ( ParseException e) {
            System.out.println(Messages.ParsingErrorMessage);
        }
    }

    public Map<String, Planet> getAll() {
        return this.planetrepo;
    }

    public Planet findPlanet(String planet) {
        return planetrepo.get(planet);
    }
}
