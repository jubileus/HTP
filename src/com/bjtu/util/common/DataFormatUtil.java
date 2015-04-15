package com.bjtu.util.common;

import java.math.BigDecimal;
/**
 * @author ����
 * ��д���ڣ�2015-4-12
 * ���ܣ����ݾ���ת��������
 */
public class DataFormatUtil {
	
	/**
	 * @author ����
	 * ��д���ڣ�2015-4-12
	 * ���ܣ�double�������ݾ���ת��
	 * @param value��Ҫת������ֵ
	 * @param scale��Ҫ�����ľ���
	 * @param roundingMode������ȡֵ��ʽ
	 * ����ȡֵģʽ���£�
	 * 1.ROUND_UP��ֻҪ��scaleλ������ڴ���0��С�������scaleλ��+1
	 * 2.ROUND_DOWN��ֱ��������scaleλ���������С��
	 * 3.ROUND_CEILING���������>0 ���ROUND_UP����һ�����������>0 ���ROUND_UP����һ��
	 * 4.ROUND_FLOOR���������>0 ���ROUND_DOWN����һ�����������<0 ���ROUND_UP����һ��
	 * 5.ROUND_HALF_UP [���ַ������]�������scale+1λ����>=5�����scaleλ����+1
	 * 6.ROUND_HALF_DOWN�������3λ����>=5������ROUND_UP�������3λ����<5,����ROUND_DOWN
	 * 7.ROUND_HALF_EVEN:�����3λ��ż��������ROUND_HALF_DOWN�������3λ������,����ROUND_HALF_UP
	 */
	public static double doubleRound(double value, int scale, int roundingMode) {
		BigDecimal bd=new BigDecimal(value);
		bd=bd.setScale(scale,roundingMode);
		double rs=bd.doubleValue();
		return rs;
    }  
	
	
	
}
