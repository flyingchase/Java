import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StaticResourceUtil {
    // 获取完整路径

    public static String getAbsolutePath(String url) {
        String path = StaticResourceUtil.class.getResource("/").getPath();
        return path.replaceAll("\\\\", "/") + url;
    }

    // output static resource

    public static void outputStaticResource(InputStream inputStream, OutputStream outputStream) throws IOException {
        int count = 0;
        while (count == 0) {
            count = inputStream.available();
        }

        // 输出 http 请求头
        int resourceSize = count;
        outputStream.write(HttpProtocolUtil.getHttpHeader200(resourceSize).getBytes());

        int written = 0;
        // 缓冲长度
        int byteSize = 1024;
        byte[] bytes = new byte[byteSize];

        while (written < resourceSize) {
            // 剩余大小不足 byteSize 1024则设为真实长度
            if (written + byteSize > resourceSize) {
                byteSize = resourceSize - written;
                bytes = new byte[byteSize];
            }

            inputStream.read(bytes);
            outputStream.write(bytes);
            outputStream.flush();

            written+=byteSize;
        }


    }


}
