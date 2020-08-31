 package com.stu.wapper;

 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;

 import javax.servlet.*;
 import javax.servlet.annotation.WebFilter;
 import javax.servlet.http.HttpServletResponse;
 import java.io.IOException;
 import java.net.URLEncoder;

 /**
 * @ProjectName: stu-parent 
 * @Package: com.stu.wapper
 * @ClassName: TestFilter
 * @Author: ZhangSheng
 * @Description: ${description}  
 * @Date: 2019/12/20 16:54
 * @Version: 1.0
 */
@WebFilter("/test/*")
public class TestFilter implements Filter{

    private Logger logger = LoggerFactory.getLogger(TestFilter.class);

     @Override
     public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
         String contentType = httpResponse.getContentType();
         logger.info("contentType:"+contentType);
         ResponseWapper responseWapper = new ResponseWapper(httpResponse);
         chain.doFilter(request, responseWapper);
         // 获取缓存的响应数据
         byte[] bytes = responseWapper.getBytes();
         String backMsg = new String(bytes, "UTF-8");
         String encode = URLEncoder.encode(backMsg, "UTF-8");
         byte[] bts = encode.getBytes();
         // 将编码数据响应给客户端
         response.getOutputStream().write(bts);
     }

 }
