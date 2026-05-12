package com.piggy.coffee.tmp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TmpTest {
	@Test
	public void testHouse() {
		int years = 50;
		double perYear = 80 * Math.pow(1.05, years) / years;
		double perMonth = perYear / 12;
		System.out.println("每年花费：" + perYear);
		System.out.println("每月花费：" + perMonth);
	}

	@Test
	public void testLambda() {
		List<Integer> list = Arrays.asList(1, 2, 3);
		Integer val = list.stream()
				.map(i -> i * 2)
				.map(i -> i.toString())
				.map(i -> Integer.valueOf(i) * 2).findFirst()
				.get();
		assertEquals(4, val);
							
	}

}
