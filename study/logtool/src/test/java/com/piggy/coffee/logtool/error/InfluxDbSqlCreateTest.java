package com.piggy.coffee.logtool.error;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

import com.piggy.coffee.common.util.json.JsonUtils;

public class InfluxDbSqlCreateTest {

	@Test
	public void testCreate() throws IOException {

		String str = "{\"tpiID\":\"745792\",\"podType\":0,\"instanceChallenge\":\"7\",\"timeLimit\":15,\"evaluateStartTime\":\"2019-10-31 22:03:01\",\"evaluateEndTime\":\"2019-10-31 22:03:01\"}" ;
		EvaluatingAssayParam param = JsonUtils.toBean(str, EvaluatingAssayParam.class);
		createCpuUsage(param);
		createCpuUsageRate(param);
		createMemoryPageFaultsRate(param);
		createCurl(param);
	}

	private void createCpuUsage(EvaluatingAssayParam assayParam) {
		String cmd = "select time, container_name, nodename, pod_name, type, value from \"cpu/usage\" where time >= '%s' and time <= '%s' and pod_name =~ /.*-%s/;";
		System.out.println("--------cpu/usage--------");

		System.out.println("--1");
		String startTime = cvtTime(assayParam.getEvaluateStartTime().minusHours(8)); // 东8区
		String endTime = cvtTime(assayParam.getEvaluateEndTime().minusHours(8).plusSeconds(10));// 加上收集周期10s
		String podName = assayParam.tpiID;
		cmd = String.format(cmd, startTime, endTime, podName);
		System.out.println(cmd);

		System.out.println("--2");
		startTime = cvtTime(assayParam.getEvaluateStartTime().minusHours(8).minusSeconds(10)); // 东8区
		endTime = cvtTime(assayParam.getEvaluateEndTime().minusHours(8).plusSeconds(30));// 加上收集周期10s
		podName = assayParam.tpiID;
		cmd = String.format(cmd, startTime, endTime, podName);
		System.out.println(cmd);

		System.out.println();
	}

	private void createCpuUsageRate(EvaluatingAssayParam assayParam) {
		// 语句
		String cmd = "select time, container_name, nodename, pod_name, type, value from \"cpu/usage_rate\" where time >= '%s' and time <= '%s' and type = 'node' and nodename = '%s';";
		System.out.println("--------cpu/usage_rate--------");

		System.out.println("--1");
		String startTime = cvtTime(assayParam.getEvaluateStartTime().minusHours(8)); // 东8区
		String endTime = cvtTime(assayParam.getEvaluateEndTime().minusHours(8).plusSeconds(10));// 加上收集周期10s
		cmd = String.format(cmd, startTime, endTime, "");
		System.out.println(cmd);

		System.out.println();

	}

	private void createMemoryPageFaultsRate(EvaluatingAssayParam assayParam) {

		// 语句
		String cmd = "select time, container_name, nodename, pod_name, type, value from \"memory/page_faults_rate\" where time >= '%s' and time <= '%s' and pod_name =~ /.*-%s/;";
		System.out.println("--------cpu/page_faults_rate--------");

		System.out.println("--1");
		String startTime = cvtTime(assayParam.getEvaluateStartTime().minusHours(8)); // 东8区
		String endTime = cvtTime(assayParam.getEvaluateEndTime().minusHours(8).plusSeconds(20));// 加上收集时间
		String podName = assayParam.tpiID;
		cmd = String.format(cmd, startTime, endTime, podName);
		System.out.println(cmd);

		System.out.println("--2");
		startTime = cvtTime(assayParam.getEvaluateStartTime().minusHours(8).minusSeconds(10)); // 东8区
		endTime = cvtTime(assayParam.getEvaluateEndTime().minusHours(8).plusSeconds(20));// 加上收集时间
		podName = assayParam.tpiID;
		cmd = String.format(cmd, startTime, endTime, podName);
		System.out.println(cmd);

	}

	private void createCurl(EvaluatingAssayParam assayParam) {

		// 语句
		String cmd = "curl -X POST -H 'Content-Type: application/json' -i 'http://172.16.94.254:8890/bridge/monitors/evaluatings/assays/1' -d '{\"tpiID\":\"%s\",\"podType\":%d,\"instanceChallenge\":\"%s\",\"timeLimit\":%d,\"evaluateStartTime\":\"%s\",\"evaluateEndTime\":\"%s\"}'";
		cmd = String.format(cmd, assayParam.tpiID, assayParam.podType, assayParam.instanceChallenge,
				assayParam.timeLimit, assayParam.evaluateStartTime, assayParam.evaluateEndTime);

		System.out.println("--------curl--------");
		System.out.println(cmd);

	}

	private String cvtTime(LocalDateTime time) {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'").format(time);
	}

}
