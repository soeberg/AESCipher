import java.util.BitSet;

public class Word {
	
	private int size;
	private BitSet bits;
	
	
	public Word(byte[] bytes){
		this.bits = BitSet.valueOf(bytes);
	}
	
	public int Size(){
		return size;
	}
	
	public BitSet xor(BitSet b){
		BitSet temp = (BitSet) bits.clone();
		temp.xor(b);
		return temp;
	}
}

