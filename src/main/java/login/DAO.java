package login;

import java.util.Map;
import spark.Spark.*;


/**
 * Created by Aqib on 12/8/2016.
 */

public interface DAO {

    public boolean addPerson(Map<String, Object> data);

}