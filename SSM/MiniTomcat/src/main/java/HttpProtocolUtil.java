public class HttpProtocolUtil {
    /**
     * 200 状态码，头信息
     *
     * @param contentLength 响应信息长度
     * @return 200 header info
     */
    public static String getHttpHeader200(long contentLength) {
        return "HTTP/1.1 200 OK \n" + "Content-Type: text/html \n"
                + "Content-length: " + contentLength + " \n" + "\r\n";
    }

    /**
     * 为响应码 404 提供请求头信息(此处也包含了数据内容)
     *
     * @return 404 header info
     */
    public static String getHttpHeader404() {
        String str404 = "<h1>404 not found</h1>";
        return "HTTP/1.1 200 OK \n" + "Content-Type: text/html \n"
                + "Content-length: " + str404.getBytes().length + " \n" + "\r\n" + str404;

    }
}
