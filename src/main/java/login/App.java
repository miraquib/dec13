//package login; /**
// * Created by Aqib on 12/8/2016.
// */
//
//
//import spark.Spark;
//
//public class App {
//
//    private final static DAO dao = new JdbcDAO();
//
//    private final static Controller controller = new Controller(dao);
//
//    public static void main(String[] args) {
//
//        //...
//
//        Spark.post("/person", (req, res) -> {
//            return controller.addPerson(req.body());
//        }); // 1
//
//    }
//
//}
