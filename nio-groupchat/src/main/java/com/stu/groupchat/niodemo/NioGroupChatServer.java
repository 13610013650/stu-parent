package com.stu.groupchat.niodemo;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @ProjectName: stu-parent
 * @Package: com.stu.groupchat.niodemo
 * @ClassName: NioGroupChatServer
 * @Author: ZhangSheng
 * @Description: 基于nio实现群聊系统的server端
 * @Date: 2019/12/8 15:43
 * @Version: 1.0
 */
@Slf4j
public class NioGroupChatServer {

    private static final int PORT = 7000;

    private ServerSocketChannel serverSocketChannel;

    private Selector selector;

    /**
     * @description: 构造方法 初始化参数
     * @author Mr.Zhang
     * @date 2019/12/8 0:59
     **/
    public NioGroupChatServer() throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    /**
     * @Author ZhangSheng
     * @param
     * @Description 监听
     */
    public void linst() throws IOException {
        System.out.println("开始监听...");
        while(true){
            //阻塞住
            int count = selector.select();
            // 有链接
            if (count>0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();
                    //判断 是否是连接事件
                    if (selectionKey.isAcceptable()){
                        SocketChannel accept = serverSocketChannel.accept();
                        //设置非阻塞
                        accept.configureBlocking(false);
                        //将该SocketChannel注册到Selector
                        accept.register(selector,SelectionKey.OP_READ);
                        System.out.println(accept.getRemoteAddress()+",已上线..");
                    }
                    // 处理读事件
                    if (selectionKey.isReadable()) {
                        //开始读数据
                        readData(selectionKey);
                    }
                    // 当前的selectionKey删除
                    iterator.remove();
                }
            } else {
                System.out.println("等待....");
            }
        }
    }

    /**
     * @Author ZhangSheng
     * @param
     * @Description 读取管道里的数据
     */
    private void readData(SelectionKey selectionKey) {
        // 获取管道
        SelectableChannel channel = selectionKey.channel();
        SocketChannel socketChannel = (SocketChannel)channel;
        // 创建一个容量位1024的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int read = socketChannel.read(byteBuffer);
            // 有数据
            if (read > 0){
                // 将缓冲区的数据 转换成字符串
                String msg = new String(byteBuffer.array());
                System.out.println("from 客户端:" + msg.trim());

                //再向其他的客户端发送消息
                sendInfoToOtherClients(msg,socketChannel);
            }
        } catch (IOException e) {
            try {
                log.info(socketChannel.getRemoteAddress()+"离线了...");
                // 取消注册
                selectionKey.channel();
                // 关闭连接
                socketChannel.close();
            } catch (IOException e1) {
                log.error("IOException..",e);
            }
        }

    }

    /**
     * @Author ZhangSheng
     * @param
     * @Description 发送消息给其他的客户端
     */
    public void sendInfoToOtherClients(String msg , SocketChannel myself) throws IOException {
        log.debug("服务器转发消息中...");
        // 遍历 选择器中所有的客户端
        for (SelectionKey selectionKey: selector.keys()){
            // 根据selectKey 拿到对应客户端的 管道
            Channel channel = selectionKey.channel();
            // 排除自己 发送信息
            if (channel instanceof SocketChannel && channel != myself){
                SocketChannel socketChannel = (SocketChannel)channel;
                //将msg 存储到buffer
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(byteBuffer);
            }
            log.debug("服务器转发消息完成...");
        }
    }

    public static void main(String[] args) throws IOException {
        NioGroupChatServer nioGroupChatServer = new NioGroupChatServer();
        nioGroupChatServer.linst();
    }

}
