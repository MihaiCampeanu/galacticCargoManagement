package com.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Ship;
import com.utils.Messages;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShipRepository implements JSONReader {
    private Map<String,Ship> shiprepo;

    public ShipRepository(String fileName){
        this.shiprepo = new HashMap<>();
        loadDataFromJSONFile(fileName);
    }

    @Override
    public void loadDataFromJSONFile(String fileName){

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;
            ObjectMapper mapper = new ObjectMapper();

            for(Object object: jsonArray) {

                String str = object.toString();
                shiprepo.put(mapper.readValue(str, Ship.class).getName(), mapper.readValue(str, Ship.class));

            }

        }  catch ( IOException e) {
            System.out.println(Messages.FileErrorMessage);
        } catch ( ParseException e) {
            System.out.println(Messages.ParsingErrorMessage);
        }
    }

    public Map<String, Ship> getAll() { return this.shiprepo; }

    public List<Ship> findShips(List<String> shipTypes){
        List<Ship> ships = new ArrayList<>();

        for(String key: shiprepo.keySet()) {

            for (String type : shipTypes) {

                if (shiprepo.get(key).getType().equals(type)) {

                    ships.add(shiprepo.get(key));

                    break;
                }
            }
        }

        return ships;
    }
}
