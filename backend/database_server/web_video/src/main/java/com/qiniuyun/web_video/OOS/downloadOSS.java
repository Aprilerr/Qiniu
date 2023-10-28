//package com.qiniuyun.web_video.OOS;
//
//import com.google.gson.Gson;
//import com.qiniu.cdn.CdnManager;
//import com.qiniu.cdn.CdnResult;
//import com.qiniu.common.QiniuException;
//import com.qiniu.http.Response;
//import com.qiniu.storage.Configuration;
//import com.qiniu.storage.Region;
//import com.qiniu.storage.UploadManager;
//import com.qiniu.storage.model.DefaultPutRet;
//import com.qiniu.util.Auth;
//import com.qiniuyun.web_video.entity.OOS;
//import com.qiniuyun.web_video.entity.OOS;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.stereotype.Component;
//
//import java.net.URLEncoder;
//
//@Component          // 注解将该标记为Spring的组件
////测试类所在包名 test 和 启动类所在包名java 一致(都在同一包src下)，不需要加配置。如果不一致要加配置（加属性）
////@SpringBootTest(classes = SanGengBlogApplication.class)//指定启动类的字节码
//@SpringBootTest
//public class downloadOSS {
//    @Autowired
//    OOS oos;
//
//    //构造私有空间的需要生成的下载的链接；
//    //格式： http://私有空间绑定的域名/空间下的文件名.文件后缀
//
//
//    @Test
//    public void downloadOos(){
//
//
//
//        String accessKey = "2ixxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";          //AccessKey值
//        String secretKey = "wHxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";          //SecretKey值
//        //密钥配置
//        Auth auth = Auth.create(accessKey,secretKey);
//
//
//        /**
//         * 获取下载文件路径，即：donwloadUrl
//         */
//        public String getDownloadUrl(String targetUrl) {
//            String downloadUrl = auth.privateDownloadUrl(targetUrl);
//            return downloadUrl;
//        }
//        /**
//         * 下载
//         */
//        public void download(String targetUrl) {
//            //获取downloadUrl
//            String downloadUrl = getDownloadUrl(targetUrl);
//            //本地保存路径
//            String filePath = "D:/atemp/";
//            download(downloadUrl, filePath);
//        }
//
//        /**
//         * 通过发送http get 请求获取文件资源
//         */
//        private static void download(String url, String filepath) {
//            OkHttpClient client = new OkHttpClient();
//            System.out.println(url);
//            Request req = new Request.Builder().url(url).build();
//            Response resp = null;
//            try {
//                resp = client.newCall(req).execute();
//                System.out.println(resp.isSuccessful());
//                if(resp.isSuccessful()) {
//                    ResponseBody body = resp.body();
//                    InputStream is = body.byteStream();
//                    byte[] data = readInputStream(is);
//                    File imgFile = new File(filepath + "download123.jpg");   //下载到本地的图片命名
//                    FileOutputStream fops = new FileOutputStream(imgFile);
//                    fops.write(data);
//                    fops.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                System.out.println("Unexpected code " + resp);
//            }
//        }
//        /**
//         * 读取字节输入流内容
//         */
//        private static byte[] readInputStream(InputStream is) {
//            ByteArrayOutputStream writer = new ByteArrayOutputStream();
//            byte[] buff = new byte[1024 * 2];
//            int len = 0;
//            try {
//                while((len = is.read(buff)) != -1) {
//                    writer.write(buff, 0, len);
//                }
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return writer.toByteArray();
//        }
//
//    }
//}
