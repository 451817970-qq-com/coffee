package com.piggy.coffee.k8s.client.json;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.piggy.coffee.k8s.client.ClientTest;

import io.fabric8.kubernetes.api.model.metrics.v1beta1.NodeMetricsList;

public class MetricsTest extends ClientTest {
	private Logger log = LoggerFactory.getLogger(getClass());

	@Test
	public void test2() {
		NodeMetricsList nmList = client.top().nodes().metrics();
		log.info(nmList.toString());
	}

}
