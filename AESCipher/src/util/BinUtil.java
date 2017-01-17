package util;

import java.nio.ByteBuffer;

public class BinUtil {
	
	public static byte[] integerToByteArray(int i){
		return ByteBuffer.allocate(4).putInt(i).array();
	}
	
	public static int byteArrayToInteger(byte[] bytes){
		return ByteBuffer.wrap(bytes).getInt();
	}
	
	public static int integerValue(byte b){
		return byteArrayToInteger(new byte[]{0x00, 0x00, 0x00, b});
	}

}
