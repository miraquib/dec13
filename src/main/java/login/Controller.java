package login; /**
 * Created by Aqib on 12/8/2016.
 */

import java.util.Map;

import org.boon.json.JsonFactory;

import org.boon.json.ObjectMapper;


public class Controller {

    private DAO dao;

    ObjectMapper mapper =  JsonFactory.create(); // 1

    public Controller(DAO dao) {

        super();

        this.dao = dao;

    }

    public String addPerson(String json){

        Map<String,Object> data =  mapper.readValue(json, Map.class); // 2

        if (dao.addPerson(data)){ // 3

            return "{\"message\":\"Added a person!\"}";

        } else {

            return "{\"message\":\"Failed to add a person\"}";

        }

    }

}