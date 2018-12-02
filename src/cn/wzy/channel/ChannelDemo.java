package cn.wzy.channel;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class ChannelDemo {

	public static void main(String[] args) throws IOException {
		RandomAccessFile raf = new RandomAccessFile("G:\\NIO\\src\\cn\\wzy\\channel\\in.txt", "rw");
		FileChannel fc = raf.getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(50);
		while (fc.read(buffer) != -1) {
			buffer.flip();
			byte[] bytes = Arrays.copyOfRange(buffer.array(),0,buffer.limit());
			System.out.println(buffer);
			System.out.print(new String(bytes,"utf-8"));
			buffer.clear();
		}
	}
}
