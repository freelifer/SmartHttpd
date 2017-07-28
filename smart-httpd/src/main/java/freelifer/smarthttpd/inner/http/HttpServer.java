package freelifer.smarthttpd.inner.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import freelifer.smarthttpd.inner.Application;
import freelifer.smarthttpd.inner.log.Logger;
import freelifer.smarthttpd.inner.model.Server;

/**
 * @author kzhu on 2017/7/26.
 */
public class HttpServer implements Runnable {
    private boolean interrupted = false;

    private Logger logger = Logger.getLogger(true, HttpServer.class);

    private final Server server;
    private final Application application;
    public HttpServer(boolean interrupted, Server server) {
        this.interrupted = interrupted;
        this.server = server;
        this.application = new Application();
    }

    @Override
    public void run() {
        doApplicationCreate();
        try {
            // 加油
            Selector selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = serverSocketChannel.socket();
            String portStr = server.getPort();
            serverSocket.setReuseAddress(true);
            try {
                serverSocket.bind(new InetSocketAddress(Integer.parseInt(portStr)));
            } catch (Exception e) {
                logger.e("ServerSocket bind error!");
                return;
            }
            logger.i("ServerSocket bind port:%s success.", portStr);

            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            logger.i("HttpServer start success.");
            while (!interrupted) {
                int readyChannels = selector.select();
                if (readyChannels == 0)
                    continue;
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = server.accept();
                        if (socketChannel != null) {
                            logger.i("receive by ip:" + ((InetSocketAddress) socketChannel.getRemoteAddress()).getHostString());
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (key.isReadable()) {
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        String requestHeader = "";
                        try {
                            requestHeader = receive(socketChannel);
                        } catch (Exception e) {
                            logger.e("socketChannel read error!");
                            return;
                        }
                        if (requestHeader.length() > 0) {
                            logger.i("RequestHeader\r\n%s", requestHeader);
                            logger.i("start child Thread...");
                            new Thread(new HttpHandler(requestHeader, key)).start();
                        }
                    } else if (key.isWritable()) {
                        logger.i("SelectionKey isWritable");
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        socketChannel.shutdownInput();
                        socketChannel.close();
                    }
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doApplicationCreate() {
        if (application == null) {
            throw new NullPointerException("HttpServer Application is null");
        }
        application.onCreate();
    }

    private String receive(SocketChannel socketChannel) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = null;
        int size = 0;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((size = socketChannel.read(buffer)) > 0) {
            buffer.flip();
            bytes = new byte[size];
            buffer.get(bytes);
            baos.write(bytes);
            buffer.clear();
        }
        bytes = baos.toByteArray();
        return new String(bytes);
    }
}
