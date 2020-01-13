 package com.stu.wapper;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.http.HttpServletResponseWrapper;
 import java.io.*;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper
 * @ClassName: ResponseWapper
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/20 16:39
 * @Version: 1.0
 */
public class ResponseWapper extends HttpServletResponseWrapper {

     private static final Logger logger = LoggerFactory.getLogger(ResponseWapper.class);
     private ByteArrayOutputStream bytes = new ByteArrayOutputStream();
     private HttpServletResponse response;
     private PrintWriter pwrite;


     public ResponseWapper(HttpServletResponse response) {
         super(response);
         this.response = response;
     }

     /**
      * 重写父类的 getWriter() 方法，将响应数据缓存在 PrintWriter 中
      */
     @Override
     public PrintWriter getWriter() throws IOException {
         try {
             pwrite = new PrintWriter(new OutputStreamWriter(bytes, "utf-8"));
         } catch (UnsupportedEncodingException e) {
             e.printStackTrace();
         }
         return pwrite;
     }

     /**
      * 获取缓存在 PrintWriter 中的响应数据
      *
      * @return
      */
     public byte[] getBytes() {
         if (null != pwrite) {
             pwrite.close();
             return bytes.toByteArray();
         }

         if (null != bytes) {
             try {
                 bytes.flush();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         return bytes.toByteArray();
     }


 }
