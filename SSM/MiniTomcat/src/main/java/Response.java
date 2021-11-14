import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Response {
    private OutputStream outputStream;



    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;

    }
    public Response() {

    }

    public void output(String content) throws IOException {
        outputStream.write(content.getBytes());
    }

    /*依据url 拼接绝对路径再依据绝对路径读取文件路径响应资源 */

    public void outputHtml(String url) throws IOException{
        String absoluteResourcePath= StaticResourceUtil.getAbsolutePath(url);

        final File file = new File(absoluteResourcePath);
        if (file.exists()&&file.isFile()) {
            // output
            StaticResourceUtil.outputStaticResource(new FileInputStream(file),outputStream);
        }else {
            output(HttpProtocolUtil.getHttpHeader404());
        }
    }
}
