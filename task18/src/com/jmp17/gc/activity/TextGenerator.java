package com.jmp17.gc.activity;

import java.util.concurrent.ThreadLocalRandom;

public class TextGenerator {
	
	private final boolean useNewStrings;
	
	private final String[] samples = {
			"random text",
			"some text",
			"some other text",
			"aaabbbcccddd",
			"12345 12345 ",
			"qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq",
			"qwertyuiop[] qwertyuiop[] qwertyuiop[] qwertyuiop[] qwertyuiop[]qwertyuiop[]qwertyuiop[]qwertyuiop[]",
			"zxcvfewrfdsgdfsgfdhbs dsfgerstgfd ersgdsfg",
			"6541456498456647448674864654846584864564864865486454867486548484864512574851894513584646878754",
			"00000000000000000000000000000000",
			"ghjkjlk",
			"asdf",
			"et5rh65hth65",
			"!@#$%^&"
	};
	
	public TextGenerator() {
		this(false);
	}
	public TextGenerator(boolean useNewStrings) {
		this.useNewStrings = useNewStrings;
	}

	public String getText() {
		String text = samples[getRandom().nextInt(samples.length)];
		return useNewStrings ? new String(text) : text;
	}
	
	private ThreadLocalRandom getRandom() {
		return ThreadLocalRandom.current();
	}
}
