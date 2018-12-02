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


