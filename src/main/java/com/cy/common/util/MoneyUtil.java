package com.cy.common.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 金额工具类
 */
public class MoneyUtil {

	/**
	 * @param osource
	 *            金额
	 * @param scale
	 *            精确位数
	 * @param fmtstr
	 *            格式化字符串
	 * @return
	 */
	public static String formatMoney(Object osource, int scale, String fmtstr) {
		if (osource == null || "".equals(osource))
			return "";

		String source = osource.toString();

		source = new BigDecimal(source).toPlainString();

		source = source.trim();
		if (source.length() == 0) {
			source = "0";
		}
		Double money = new Double(source);
		DecimalFormat fmt = null;
		String pattern = "###,##0";
		if (fmtstr != null && !fmtstr.equals("")) {
			pattern = fmtstr;
		}
		if (scale > 0) {
			pattern = pattern + ".";
			try {
				pattern = rightPad(pattern, pattern.length() + scale, "0");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		fmt = new DecimalFormat(pattern);
		source = fmt.format(money);
		return source;
	}

	/**
	 * 字符右填充
	 * 
	 * @param source
	 *            金额
	 * @param len
	 *            长度
	 * @param padStr
	 *            填充字符
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String rightPad(String source, int len, String padStr) {
		if (padStr == null || padStr.equals("")) {
			padStr = "  ";
		}
		if (source == null)
			source = "";
		StringBuffer ss = new StringBuffer(source);
		int slen = 0;
		try {
			slen = source.getBytes("GBK").length;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int dlen = padStr.length();
		while (true) {
			if (slen >= len)
				break;
			if ((len - slen) > dlen) {
				ss.append(padStr);
				slen += dlen;
			} else {
				ss.append(padStr.substring(0, len - slen));
				break;
			}
		}
		return ss.toString();
	}

	/**
	 * 格式化金额显示
	 * 
	 * @param source
	 *            例:12345.67800
	 * @param scale
	 *            例:2
	 * @return 例:12,345.68
	 */
	public static String formatMoney(Object osource, int scale) {
		return formatMoney(osource, scale, "");
	}

	/**
	 * 将人民币转化为大写 如:1837.33返回：壹仟捌佰叁拾柒圆叁角叁分
	 * 
	 * @param source
	 * @return
	 */
	public static String formatRmb(String source) {
		String CN_ZERO = "零";
		String CN_SYMBOL = "";
		String CN_DOLLAR = "圆";
		String CN_INTEGER = "整";

		String[] digits = new String[] { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String[] radices = new String[] { "", "拾", "佰", "仟" };
		String[] bigRadices = new String[] { "", "万", "亿", "万" };
		String[] decimals = new String[] { "角", "分" };

		String integral = null; // 整数部分
		String decimal = null; // 小数部分
		String outputCharacters = null; // 最终转换输出结果

		String d = null;
		int zeroCount = 0, p = 0, quotient = 0, modulus = 0;

		// 删除数字中的逗号,
		source = source.replace("/,/g", "");
		// 删除数字左边的0
		source = source.replace("/^0+/", "");

		// 拆分数字中的整数与小数部分
		String[] parts = source.split("\\.");
		if (parts.length > 1) {
			integral = parts[0];
			decimal = parts[1];

			// 如果小数部分长度大于2，四舍五入，保留两位小数
			if (decimal.length() > 2) {
				long dd = Math.round(Double.parseDouble("0." + decimal) * 100);
				decimal = Long.toString(dd);
			}

		} else {
			integral = parts[0];
			decimal = "0";
		}

		// Start processing:
		outputCharacters = "";
		// Process integral part if it is larger than 0:
		if (Double.parseDouble(integral) > 0) {

			zeroCount = 0;

			for (int i = 0; i < integral.length(); i++) {

				p = integral.length() - i - 1;
				d = integral.substring(i, i + 1);

				quotient = p / 4;
				modulus = p % 4;
				if (d.equals("0")) {
					zeroCount++;
				} else {
					if (zeroCount > 0) {
						outputCharacters += digits[0];
					}
					zeroCount = 0;
					outputCharacters += digits[Integer.parseInt(d)] + radices[modulus];
				}
				if (modulus == 0 && zeroCount < 4) {
					outputCharacters += bigRadices[quotient];
				}
			}
			outputCharacters += CN_DOLLAR;
		}

		// Process decimal part if it is larger than 0:
		if (Double.parseDouble(decimal) > 0) {
			for (int i = 0; i < decimal.length(); i++) {
				d = decimal.substring(i, i + 1);
				if (!d.equals("0")) {
					outputCharacters += digits[Integer.parseInt(d)] + decimals[i];
				} else {
					if (i == 0) {
						outputCharacters += CN_ZERO;
					}
				}
			}
		}

		// Confirm and return the final output string:
		if (outputCharacters.equals("")) {
			outputCharacters = CN_ZERO + CN_DOLLAR;
		}
		if (decimal == null || decimal.equals("0")) {
			outputCharacters += CN_INTEGER;
		}

		outputCharacters = CN_SYMBOL + outputCharacters;
		return outputCharacters;
	}

	/** 分转元String */
	public static String cent2DollarStr(String cent, int fmtScale) {
		if (fmtScale < 0 || fmtScale > 10)
			fmtScale = 0;
		return formatMoney(cent2DollarStr(cent), fmtScale);
	}

	/** 分转元String */
	public static String cent2DollarStr(String cent) {
		if (cent == null)
			return "0.00";
		double icent = 0;
		try {
			icent = new Double(cent).doubleValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "0.00";
		}
		return "" + cent2Dollar(icent);
	}

	/** 分转元Double */
	public static Double cent2Dollar(double cent) {
		if (cent == 0) {
			return new Double(0D);
		}
		try {
			return doubleDiv(cent, 100D, 2);
		} catch (Exception ex) {
			return new Double(0D);
		}
	}

	/** 元转分String */
	public static String dollar2CentStr(String cent) {
		if (cent == null)
			return "0";
		double icent = 0;
		try {
			icent = new Double(cent).doubleValue();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "0";
		}
		return "" + dollar2Cent(icent);
	}

	/** 元转分Integer */
	public static Integer dollar2Cent(double cent) {
		if (cent == 0)
			return 0;
		return doubleMul(cent, 100D).intValue();
	}

	/**
	 * 精确减法运算
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Double doubleSub(Double x, Double y) {
		x = x == null ? 0D : x;
		y = y == null ? 0D : y;
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 精确加法运算
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Double doubleAdd(Double x, Double y) {
		x = x == null ? 0D : x;
		y = y == null ? 0D : y;
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 精确乘法运算
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Double doubleMul(Double x, Double y) {
		x = x == null ? 0D : x;
		y = y == null ? 0D : y;
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.multiply(b2).doubleValue();
	}

	public static Double doubleDiv2(Double x, Double y) {
		return doubleDiv(x, y, 2);
	}

	public static Double doubleDiv4(Double x, Double y) {
		return doubleDiv(x, y, 4);
	}

	/**
	 * 提供精确的除法运算，结果四舍五入
	 * 
	 * @param v1除数
	 * @param v2被除数
	 * @param scale精确位数
	 * @return
	 */
	public static Double doubleDiv(Double x, Double y, int scale) {
		x = x == null ? 0D : x;
		y = y == null ? 0D : y;
		BigDecimal b1 = new BigDecimal(Double.toString(x));
		BigDecimal b2 = new BigDecimal(Double.toString(y));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Double scale2(Double d) {
		return scale(d, 2);
	}

	/**
	 * 提供精确的四舍五入
	 * 
	 * @param v1需要四舍五入的数字
	 * @param scale小数点后保留几位
	 * @return结果
	 */
	public static Double scale(Double d, int scale) {
		d = d == null ? 0D : d;
		BigDecimal decimal = new BigDecimal(d);
		return decimal.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 获取小于该数的最大整数
	 * 
	 * @param value
	 * @return
	 */
	public static Integer floor(Double value) {
		return (int) Math.floor(value);
	}

	public static void main(String[] args) {
		System.out.println(MoneyUtil.floor(1.6412));
	}

	/**
	 * 乘法--保留两位小数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static BigDecimal mul(BigDecimal x, BigDecimal y) {
		return scale2(x.multiply(y));
	}

	/**
	 * 除法--保留2位小数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static BigDecimal div(BigDecimal x, BigDecimal y) {
		if (y.compareTo(BigDecimal.valueOf(0)) == 0) {
			return BigDecimal.valueOf(0);
		}
		return x.divide(y, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 除法--保留10位小数
	 *
	 * @param x
	 * @param y
	 * @return
	 */
	public static BigDecimal div10(BigDecimal x, BigDecimal y) {
		if (y.compareTo(BigDecimal.valueOf(0)) == 0) {
			return BigDecimal.valueOf(0);
		}
		return x.divide(y, 10, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 保留两位小数
	 * 
	 * @param x
	 * @return
	 */
	public static BigDecimal scale2(BigDecimal x) {
		return scale(x, 2);
	}

	public static BigDecimal scale(BigDecimal x, int scale) {
		return x.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}
}
