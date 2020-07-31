package com.stu.groupchat.niodemo;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @ProjectName: stu-parent
 * @Package: com.stu.groupchat.niodemo
 * @ClassName: NioGroupChatClient
 * @Author: ZhangSheng
 * @Description: 基于nio实现群聊系统的client端
 * @Date: 2019/12/8 15:43
 * @Version: 1.0
 */
@Slf4j
public class NioGroupChatClient {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 7000;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    /**
     * @Author ZhangSheng
     * @Description 无参构造器
     */
    public NioGroupChatClient() throws IOException {
        this.selector = Selector.open();
        this.socketChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));
        //设置非阻塞
        this.socketChannel.configureBlocking(false);
        // 将socketChannel注册到selector
        this.socketChannel.register(selector, SelectionKey.OP_READ);
        this.username = socketChannel.getRemoteAddress().toString().substring(1);
    }
    /**
     * @Author ZhangSheng
     * @param msg 消息
     * @Description 向服务器发送消息
     */
    public void sendInfoToServer(String msg){
        msg = this.username +"说:" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            log.error("java.io.IOException",e.getMessage());
        }
    }
    /**
     * @Author ZhangSheng
     * @param
     * @Description 监听读取服务器发送过来的消息
     */
    public void readInfo() {
        int select =0;
        try {
             select = selector.select();
        } catch (IOException e) {
            log.error("java.io.IOException",e.getMessage());
        }
        //如果有消息
        if (select>0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while(iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isReadable()){
                    SocketChannel channel = (SocketChannel)selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    try {
                        channel.read(byteBuffer);
                        //把读到的缓冲区的数据转成字符串
                        String msg = new String(byteBuffer.array());
                        System.out.println(msg.trim());
                    } catch (IOException e) {
                        log.error("java.io.IOException",e.getMessage());
                    }
                }
            }
            iterator.remove();
        }
    }


    public static void main(String[] args) throws IOException {
        NioGroupChatClient chatClient = new NioGroupChatClient();
        new Thread(()->{
            while (true){
                chatClient.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    log.error("java.lang.InterruptedException",e.getMessage());
                }
            }
        }).start();
        //发送数据给服务器端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String info = scanner.nextLine();
            chatClient.sendInfoToServer(info);
        }
    }
}
