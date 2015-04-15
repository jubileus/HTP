package com.bjtu.util.common;

import java.math.BigDecimal;
/**
 * @author 刘庶
 * 编写日期：2015-4-12
 * 功能：数据精度转换工具类
 */
public class DataFormatUtil {
	
	/**
	 * @author 刘庶
	 * 编写日期：2015-4-12
	 * 功能：double类型数据精度转换
	 * @param value：要转换的数值
	 * @param scale：要保留的精度
	 * @param roundingMode：精度取值方式
	 * 精度取值模式如下：
	 * 1.ROUND_UP：只要第scale位后面存在大于0的小数，则第scale位就+1
	 * 2.ROUND_DOWN：直接舍弃第scale位后面的所有小数
	 * 3.ROUND_CEILING：如果数字>0 则和ROUND_UP作用一样，如果数字>0 则和ROUND_UP作用一样
	 * 4.ROUND_FLOOR：如果数字>0 则和ROUND_DOWN作用一样，如果数字<0 则和ROUND_UP作用一样
	 * 5.ROUND_HALF_UP [这种方法最常用]：如果第scale+1位数字>=5，则第scale位数字+1
	 * 6.ROUND_HALF_DOWN：如果第3位数字>=5，则做ROUND_UP，如果第3位数字<5,则做ROUND_DOWN
	 * 7.ROUND_HALF_EVEN:如果第3位是偶数，则做ROUND_HALF_DOWN，如果第3位是奇数,则做ROUND_HALF_UP
	 */
	public static double doubleRound(double value, int scale, int roundingMode) {
		BigDecimal bd=new BigDecimal(value);
		bd=bd.setScale(scale,roundingMode);
		double rs=bd.doubleValue();
		return rs;
    }  
	
	
	
}
