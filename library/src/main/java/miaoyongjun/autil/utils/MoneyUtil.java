
package miaoyongjun.autil.utils;

import java.text.DecimalFormat;

public class MoneyUtil {

	public static class Money {
		public boolean isChange;
		public String money;
	}

	/**
	 * 拿到钱字符串
	 * @param moneyString 输入的字符
	 * @param intLength int的长度
	 * @param floatLength 小叔的长度
	 */
	public static Money getMoney(CharSequence moneyString, int intLength, int floatLength) {
		Money money = new Money();
		String source = moneyString.toString();
		if (source.contains(" ")) {
			source = source.replace(" ", "");
			money.isChange = true;
		}

		// 当第一位是0，其它非0
		if (source.startsWith("0") && source.indexOf(".") == -1 && source.length() > 2) {
			source = source.substring(1, source.length());
			money.isChange = true;
		}

		// 当第一位是0，第二位不是.时，直接显示0
		if (source.startsWith("0") && source.indexOf(".") != 1 && source.length() > 1) {
			source = "0";
			money.isChange = true;
		}
		// 当仅仅输入.时
		if (source.startsWith(".") && source.length() == 1) {
			source = "0.";
			money.isChange = true;
		}
		if (source.startsWith(".") && source.length() > 2) {
			source = source.substring(1, source.length()) + ".";
			money.isChange = true;
		}
		if (".0".equals(source)) {
			source = "0.";
			money.isChange = true;
		}
		// 当内容中有.时
		if (source.contains(".")) {
			// 当.后的字符长度大于floatLength时
			int point = source.indexOf(".");
			String floatCon = source.substring(point + 1);
			String newFloat = floatCon;
			if (floatCon.contains(".")) {
				newFloat = floatCon.replace(".", "");
				source = source.replace(floatCon, newFloat);
				money.isChange = true;
			}
			if (newFloat.length() > floatLength) {
				source = source.substring(0, source.indexOf(".") + floatLength + 1);
				money.isChange = true;
			}
		}

		money.money = source;

		// 判断.之前的
		if (intLength > 0) {
			int point = source.indexOf(".");
			if (point == -1) {// 没有.时
				getIntContent(money, source, intLength);
			} else {
				String intContent = source.substring(0, point);
				String floatContent = source.substring(point);
				getIntContent(money, intContent, intLength);
				money.money += floatContent;
			}
		}
		return money;
	}

	private static void getIntContent(Money money, String content, int intLength) {
		money.money = content;
		if (content.length() > intLength) {
			money.isChange = true;
			money.money = content.substring(0, intLength);
		}
	}

	/**
	 * 格式化成金额
	 */
	public static String formatMoney(double nowSize) {
		DecimalFormat df = new DecimalFormat();
		df.applyPattern("###,###.00");
		String result = df.format(nowSize);
		if (result.startsWith("."))
			result = "0" + result;
		return result;
	}

}
