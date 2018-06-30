package com.app;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RecaptchaUtil {

	public String recap() {

		String str = "1234567890";

		StringBuffer sb = new StringBuffer();

		Random random = new Random();

		for (int i = 0; i < 5; i++) {

			sb.append(str.charAt(random.nextInt(9)));
		}

		System.out.println(sb);

		return new String(sb);
	}

}
