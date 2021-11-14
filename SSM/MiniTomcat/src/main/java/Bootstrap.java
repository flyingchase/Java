import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Bootstrap {
    private int port = 8090;

    /**
     * 启动入口
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.start();
    }

    /**
     * 启动函数
     *
     * @throws IOException
     */
    // version 1.0  将字符串请求到页面
    // public void start() throws IOException {
    //     System.out.println("MiniTomcat starting...");
    //     String responseData = "Hello, this is the test message.";
    //     ServerSocket socket = new ServerSocket(port);
    //
    //     while (true) {
    //         Socket accept = socket.accept();
    //         OutputStream outputStream = accept.getOutputStream();
    //         String responseText = HttpProtocolUtil.getHttpHeader200(responseData.length())+responseData;
    //         outputStream.write(responseText.getBytes());
    //         accept.close();
    //     }
    // }

    // 封装 request 和 response 对象 返回 HTML 静态资源文件
    public void start() throws IOException {

        System.out.println("MiniTomcat starting...");

         ServerSocket socket = new ServerSocket(port);

        while (true) {
            Socket accept = socket.accept();
            OutputStream outputStream = accept.getOutputStream();

            // 封装 request 和 response 对象
             Request request = new Request(accept.getInputStream());
             Response response = new Response(outputStream);

            response.outputHtml(request.geturl());
            accept.close();
        }
    }
}
