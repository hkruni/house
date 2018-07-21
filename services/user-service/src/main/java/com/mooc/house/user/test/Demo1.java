package com.mooc.house.user.test;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class Demo1 {

	public static void main(String[] args) {
		
		
		ImmutablePair<String, String> pair = ImmutablePair.of("aaa", "bbb");
		System.out.println(pair.getKey() + ":" + pair.getValue());
		
		// TODO Auto-generated method stub

	}

}
