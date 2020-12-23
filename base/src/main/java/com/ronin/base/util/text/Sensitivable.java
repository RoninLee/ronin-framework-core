package com.ronin.base.util.text;

import org.apache.commons.lang3.StringUtils;

/**
 * 提供转换方法
 *
 */
public interface Sensitivable {

	default String apply(String text, int start, int end, char replacement) {
		if (StringUtils.isBlank(text)) {
			return StringUtils.EMPTY;
		}
		char[] chars = text.toCharArray();
		for (int i = start; i < end;) {
			chars[i++] = replacement;
		}
		return new String(chars);
	}
}
