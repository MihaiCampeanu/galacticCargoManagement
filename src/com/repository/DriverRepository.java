package com.repository;

import com.model.Driver;
import com.utils.Messages;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverRepository implements JSONReader {
    private Map<String,Driver> driverRepo;

    public DriverRepository(String fileName){
        this.driverRepo = new HashMap<>();
        loadDataFromJSONFile(fileName);
    }

    @Override
    public void loadDataFromJSONFile(String fileName) {


        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader(fileName));
            JSONArray jsonArray = (JSONArray) obj;

            for(Object object: jsonArray) {

                JSONObject jsonObject = (JSONObject)object;
                Long id = (Long) jsonObject.get("id");
                String name = (String)jsonObject.get("name");
                JSONArray shipsType = (JSONArray)jsonObject.get("shipsType");
                driverRepo.put(name, new Driver(id, name, this.setShipsType(shipsType)));

            }
        } catch ( IOException e) {
           System.out.println(Messages.FileErrorMessage);
        } catch ( ParseException e) {
            System.out.println(Messages.ParsingErrorMessage);
        }

    }

    private List<String> setShipsType(JSONArray shipsType) {
        List<String> shit_type = new ArrayList<>();

        for(Object type: shipsType){

            shit_type.add(type.toString());

        }

        return shit_type;
    }

    public Map<String, Driver> getAll() {
        return this.driverRepo;
    }

    public Driver findDriver(String name) {
        return driverRepo.get(name);
    }
}
