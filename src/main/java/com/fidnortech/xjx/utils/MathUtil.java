package com.fidnortech.xjx.utils;
 
import java.math.BigDecimal;
 
/**
 * 数学工具
 *
 */
public class MathUtil {

	/**
	 * Double类型相加
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double doubleAdd(Double a, Double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return b1.add(b2).doubleValue();
	}

	/**
	 * Double类型相减
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double doubleSub(Double a, Double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * Double类型乘法
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double doubleMul(Double a, Double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * Integer类型乘Double
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double doubleMul2(Integer a, Double b) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * Double类型除法
	 *
	 * @param a
	 * @param b
	 * @param scale        几位小数
	 * @param roundingMode 舍入模式 4-四舍五入
	 * @return
	 */
	public static Double doubleDiv(Double a, Double b, Integer scale, Integer roundingMode) {
		BigDecimal b1 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return b1.divide(b2, scale, roundingMode).doubleValue();
	}

	/**
	 * Double类型除法,4位小数,四舍五入模式
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static Double doubleDiv(Double a, Double b) {
		return doubleDiv(a, b, 4, 4);
	}

}