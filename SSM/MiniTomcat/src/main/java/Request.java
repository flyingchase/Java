import java.io.IOException;
import java.io.InputStream;

/*对 HTTP/1.1 的 request 解析
 *  GET / HTTP/1.1
 *  Host: www.baidu.com
 *  Connection: keep-alive
 *  Pragma: no-cache
 *  Cache-Control: no-cache
 *  Upgrade-Insecure-Requests: 1
 *  User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.83 Safari/537.36


 * */
public class Request {
    private String method;


    private String url="127.0.0.1";

    private InputStream inputStream;


    public Request() {

    }

    public Request(InputStream inputStream) throws IOException {
        this.inputStream=inputStream;

        int count =0;
        while (count == 0) {
            count=inputStream.available();
        }
        byte[] bytes = new byte[count];

        inputStream.read(bytes);
         String requestSrting = new String(bytes);
         // 按换行符分割
         String[] requestStringArray = requestSrting.split("\\n");

         // read the first line GET / HTTP/1.1
         String firstline = requestStringArray[0];
        // 遍历第一行数据
         String[] firstLineArray = firstline.split(" ");
         this.method = firstLineArray[0];
         this.url= firstLineArray[1];


    }

    public String geturl() {
        return url;
    }
}
