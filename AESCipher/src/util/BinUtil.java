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
	
	public static byte modShift(byte x, byte y, int mod){
		int base = integerValue(x);
		int s = integerValue(y);
		int p = 7;
		int temp = 0;
		int result = 0;
		for (int i= 1; i <8;i++){
			if((s>>i) % 2 == 1){
				temp = base;
				for (int j = 0; j < i; j++){
					if(temp < Math.pow(2,7)){
						temp = temp<<1;
					} else {
						temp = (int) (((temp<<1)^mod) % Math.pow(2,8));
					}
				}
				result = result^temp;
			}
		}
		if (s % 2 == 1){
			result = result^base;
		}
		
		return integerToByteArray(result)[3];
		
	}

}
