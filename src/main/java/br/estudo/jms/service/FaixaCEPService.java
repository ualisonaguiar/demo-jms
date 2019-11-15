package br.estudo.jms.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaixaCEPService {

	public static Map<String, List<Integer>> getFaixasCEPProcessar() {
		Map<String, List<Integer>> listaCEP = new HashMap<String, List<Integer>>();
		// listaCEP.put("SP", Arrays.asList(1000000, 19999999)); // SP
		// listaCEP.put("DF", Arrays.asList(70000000, 73699999)); // DF
		listaCEP.put("DF", Arrays.asList(73350000, 73350700)); // DF
		return listaCEP;
	}
}
