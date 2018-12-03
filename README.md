# NIO
java Non_blocking IO的学习  
## ***Knowledge points***  
* ***Channel***  
* ***Buffer***  
* ***Selector***  
### channel和buffer  
从原来的面向数据字节流的模式改为现在面向通道的模式，一个通道可以指向一个输出流或者输出流，
而与程序直接接触的对象是buffer，也就是通道是我们操作的目标，buffer则是我们操作所用到的工具，
在程序中只需要读写buffer，然后将整个buffer送入通道或者从通道中整个取出。
在使用channel.read()或者write()，程序不会阻塞，继续往下执行，但是取出的数据就是真正目前获取的数据，
不能保证数据完全读入或者取出完毕。
### Selector选择器  
channel往selector里面注册事件（连接，读，写等），也就是告诉选择器，我对这些感兴趣;
在死循环里面调用selector.select()，这个是阻塞方法，当有通道可以读或者可以写或者可以连接，
selector就会通知相应的channel可用，然后进行相应的操作。

### Selector使用方式  
* Selector selector = Selector.open();新建一个选择器
* SocketChannel socketChannel = SocketChannel.open();  socketChannel.configureBlocking(false);
打开一个客户端的chanel，并设置为非阻塞
* socketChannel.register(selector, SelectionKey.OP_CONNECT);将这个channel注册到selector中，设置对连接事件感兴趣
* 在一个死循环中调用selector.select();此方法为阻塞方法，也就是说当没有感兴趣的事件停下来等待，不会造成cpu空转
* selector.selectedKeys();遍历selector的所有感兴趣事件
* 如果某个事件是可用的（可读，可写，可连接的），那么client = ((SocketChannel) key.channel());
通过这个SelectionKey来找到对应的通道，进行相应的操作（读、写、连接）
### Selector的注销  
由于Selector的注册是由位标志来记录的，也就是一个数的二进制的表示法，某个位上的值为一表示对某一个事件感兴趣，
那么注销就可以直接设置感兴趣的值为0，也就是所有的事件都不感兴趣，就将这个channel移出去，代表断开连接