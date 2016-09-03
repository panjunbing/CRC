package crc;


public class CRC {

	private String str;
	private long data[];
	private final long Polynomial;             						  //CCITT标准形式多项式转化成对应的二进制
	private final int R;                                        	  //多项式的最高次幂
	
	public CRC(String str) {
		Polynomial = 69665L;                                          //二进制为10001000000100001
		R = 16;
		setStr(str);
		char tempChar[] = str.toCharArray();
		data = new long[100];
		for (int i = 0; i < tempChar.length; i++) {
			data[i] = tempChar[i];
		}
		System.out.println("原始编码为：");
		printCode();
		encode(data);
		System.out.println("CRC编码为：");
		printCode();
	}
	
	public String getStr() {
		return str;
	}
	
	public void setStr(String str) {
		this.str = str;
	}

	public long[] getData() {
		return data;
	}

	public int getDataNum() {
		int num = 0;
		for (;data[num] != 0; num++);
		return num;
	}

	public void setData(long data[]) {
		this.data = data;
	}
	
	private void encode(long data[]) {
		for (int i = 0; i <= getDataNum(); i++) {          		        
			data[i] = data[i]<<R;									//向左移16位；
			long tempData = data[i];				
			while(data[i] > 65535L ){                               //模2除(高位对齐)，直到data[i]小于R(16)位65535为1111111111111111
				long temp1 = data[i];                               
				int temp2 =  0;                                     //temp2为最高位1的位数
				while (temp1 != 0) {
					temp1 = temp1>>1;
					temp2++;
				}
				data[i] = data[i] ^ (Polynomial<<(temp2-R-1));
			}
			data[i] = tempData | data[i];                           //把余数补在后面
		}
	}
	
	public void incode(long data[]) {
		
	}
	
	private void printCode() {                                    //输出bit位的二进制数
		for (int i = 0; i < getDataNum(); i++) {
			String tempStr = Long.toBinaryString(data[i]);               //把data[i]转化为对应的二进制数
			System.out.println(tempStr);
		}
	}
}





