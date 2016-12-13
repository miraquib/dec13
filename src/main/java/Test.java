//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.RequestBody;
//
//import java.io.IOException;
//
//public class Test {
//
//    public static String testFunction() {
//
//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody formBody = new FormBody.Builder()
//                .add("message", "Your message")
//                .build();
//
//        okhttp3.Request request = new okhttp3.Request().Builder
////        okhttp3.Request request = new okhttp3.Request().Builder()
//                .url("http://www.foo.bar/index.php")
//                .post(formBody)
//                .build();
//
//        try {
//            okhttp3.Response response = client.newCall(request).execute();
//
//            // Do something with the response.
//            return "true";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "false";
//    }
//
//
//}
