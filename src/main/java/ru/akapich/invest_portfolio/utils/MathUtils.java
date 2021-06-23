package ru.akapich.invest_portfolio.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * External mathematics methods
 *
 * @author Aleksandr Marakulin
 **/

@Component
public class MathUtils {

	public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

	public static BigDecimal getPercent(BigDecimal total, BigDecimal value){
		if (total.compareTo(BigDecimal.ZERO) != 0) {
			return divideBigDecimalWithTwoPrecision(value, total).multiply(ONE_HUNDRED);
		}
		return BigDecimal.ZERO;
	}

	public static BigDecimal divideBigDecimalWithTwoPrecision(BigDecimal numerator, BigDecimal denominator){
		return numerator.divide(denominator, 2, RoundingMode.CEILING);
	}
}
