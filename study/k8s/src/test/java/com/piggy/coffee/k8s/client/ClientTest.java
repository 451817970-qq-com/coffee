package com.piggy.coffee.k8s.client;

import org.junit.After;
import org.junit.Before;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class ClientTest {
	KubernetesClient client = null;

	@Before
	public void setUp() throws Exception {
		//withCertFile();
		withCertData();
	}

	void withCertFile() {
		String certDir = "D:\\mumu\\note\\cloud\\k8s\\k8s_auth\\";
		Config config = new ConfigBuilder().withMasterUrl("https://192.168.56.101:6443").withNamespace("default")
				.withTrustCerts(true).withCaCertFile(certDir + "k8s-root-ca.pem")
				.withClientCertFile(certDir + "admin.pem").withClientKeyFile(certDir + "admin-key.pem")
				.withOauthToken("aa1d522ad080f2f893ea87252d5d1588").build();
		client = new DefaultKubernetesClient(config);

	}

	void withCertData() {
		Config config = new ConfigBuilder().withMasterUrl("https://192.168.56.102:6443").withNamespace("default")
				.withTrustCerts(true).withCaCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRJeE1ETXpNREE1TXpnd05sb1hEVE14TURNeU9EQTVNemd3Tmxvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTVZtCkY1dHBOY3I1d3EwOUNGU0RpMURPVjQvNWFicDE5SGxSMGVOTXQ5amFPUVRNSGM3QTM4VjN0SnNMVGlPZ3lubWIKTWxHOEQzZk9ZVWdmOVRPazQzRUJHWFQ3Sm1QUVhiaCtEelRSbXVVN0poTC9qZmFobU5pVDdBR0NMbW9BRGFBVgpXTFcrNHNBQXBqUTlOZmxqVG5xdmlaVm5pcS9EaDJ3TUpudDNUczhHOVNtbFdlYS9HMEtsaVliaDRNcTRpQmVKCjFSdkdsK0hOdW1kT0J2OG8zWHRsRHplYm5RMktDL3d1ditINGpWbnFyVExWOFJKcFVaRHdWblFSa2N6WDZUbjUKRnRCQnhjb1NDelQ4eStXY1JMbng1dlpzU0lqRTJ3TzRKMEo5RWhWN0svd2pnMjF3THJZOFRKaSs2Z21qUDNBVgpWdkJtWElWSlprUGdjNW1YQS84Q0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFHcS8zcCtWMTNQUnlLeWpwaWdiNFB0NitpcXoKTlFuWEtkZzdNTkdGY2tleGNIenVIK1k4SUJRQ3VvNVlBV21JdnJRbElyS0o1ZHZxMThQMXJlOVVCeVFqeHpWcgo3d3IwdGdjdFRuQ0VZaTg1ZlEwNXJEamhoaEVHbE8wYldpMjA4RTYzWi9MT29hZ3BCZjVscGRPZXd3c1JGZGtjClhaNjhOZml6cUF1Tll3OGcvcHRtc2U3cXEzMWNxQ3ZDcmFBaUQzNlJ1Vkc4azdwdHdMU093ZVZ3TXhQOVF3QVcKOVo0MDJPT1lZR1F6N0c0aHdPNmpucTJ0ek5WNjRMVE5MUC9rK0ovelpGSER6VnJVU3F0N21MYVBDaWVSVCtsagp3QzVEYzVhSWhIQjVkUWZTTG14VGN4aXVjR01ZVEcxQ1V0akJzdFJvaSt3Sk5WSGtBZGhjMnF4TnF1az0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo")
				.withClientCertData("LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUM4akNDQWRxZ0F3SUJBZ0lJRXNsTjZOeWhqblV3RFFZSktvWklodmNOQVFFTEJRQXdGVEVUTUJFR0ExVUUKQXhNS2EzVmlaWEp1WlhSbGN6QWVGdzB5TVRBek16QXdPVE00TURaYUZ3MHlNakF6TXpBd09UTTRNRGhhTURReApGekFWQmdOVkJBb1REbk41YzNSbGJUcHRZWE4wWlhKek1Sa3dGd1lEVlFRREV4QnJkV0psY201bGRHVnpMV0ZrCmJXbHVNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQTJnOGFJdHliZThMWkVNcTkKR3pXaVZmQVpZQkw3Uzh6cjJkdVdKeHo0QUNZMW8ydWZwekgrQ01uc3YvamdrRUprZHZ1ZGRkU1lTRWxCT28zVQpQVjU5dlRuazFXMDZPTnlQVHN1aTZMdzF3dWJ5RFlTYlp4Y1ZaaFBrOU4yNkJ6elRKeklGZDhUbGIzbkplTk02Ck1tNFRuR0gzL0tQYkx0S2l5OW80aGhQTlBRanNCMmRxSXQ0RzFKajQ1bTFIN2JwMEdPNXViNUJuSGlubFhPVWIKbHJaa0JVWkRBbXhwK3V1dlNKRXgzV1FBQWRQRDZ5VVB3elRBV0liOGw3MzF1ZHMrc05HakRZV2NxNE4wcEpOaApiaGtxK2M3SlFKdXVReWJrUkxHbCsrYWZPM2hrYVhseXkwV3lLTHNHL0FlNVlyUkFYeWJ0dE83d1FYSmJYZVBHCndhOEFjd0lEQVFBQm95Y3dKVEFPQmdOVkhROEJBZjhFQkFNQ0JhQXdFd1lEVlIwbEJBd3dDZ1lJS3dZQkJRVUgKQXdJd0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFLQWlSSFV6am5qVW1xemxHUVJLc0RUT3BWZzlYbm1iUnlyVgo2a0h4YmJPTjhuS3hBMHBveHBnNk41WEM5Mkw1K2dGZy9zNThFWXN1SmFJK1JTTXFhMTdpOEFTcldWcWhFZnVBCkp1cW8zTjRqYWQvZXZnZThHcC94S1B2THdCQVFvZGVidjF3RmFFZGxEcTVtNnAzRFJrakJ4VENUM0hmdEczRkoKTjlCaC94QVRMZTFML05vWUllM2h2NytBby9pdHA3OFNET3QyTTgwMm13R3FVbjRYaG5UYXVseENuWDk1aFp3bQpjdVRTNXBYMFlhWllMbmVNWTR2TW9zQmZBT01JOW5qcU9FcER4UFdkb0dxdDN4Z05ZWm11NG51Yko4NndqOVJiCnpTa0J5d3U3MG5sS0dtWUNmMGV6TEZQcmRtQXhpb0F6cGU3Y0F5QmVGcUpldm1ISFp0cz0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo")
				.withClientKeyData("LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb3dJQkFBS0NBUUVBMmc4YUl0eWJlOExaRU1xOUd6V2lWZkFaWUJMN1M4enIyZHVXSnh6NEFDWTFvMnVmCnB6SCtDTW5zdi9qZ2tFSmtkdnVkZGRTWVNFbEJPbzNVUFY1OXZUbmsxVzA2T055UFRzdWk2THcxd3VieURZU2IKWnhjVlpoUGs5TjI2Qnp6VEp6SUZkOFRsYjNuSmVOTTZNbTRUbkdIMy9LUGJMdEtpeTlvNGhoUE5QUWpzQjJkcQpJdDRHMUpqNDVtMUg3YnAwR081dWI1Qm5IaW5sWE9VYmxyWmtCVVpEQW14cCt1dXZTSkV4M1dRQUFkUEQ2eVVQCnd6VEFXSWI4bDczMXVkcytzTkdqRFlXY3E0TjBwSk5oYmhrcStjN0pRSnV1UXlia1JMR2wrK2FmTzNoa2FYbHkKeTBXeUtMc0cvQWU1WXJSQVh5YnR0Tzd3UVhKYlhlUEd3YThBY3dJREFRQUJBb0lCQUR0dldGbGVOZ2NDc2ZXUQp2UUNZbXRtL3FycjBFWWkwbnlodllMcmllM3BXNzlIT09PTHlqbUpJMzMzbjBSV2c4dzgzUDlEcEZHVEhpcjd4Ci80S0dxZEVVNFptR3Z4TFdrejBQNWlSM2dGUFNiUEZnVHBkSHhHNWhSOHV5VDgwL04vSjhBTitIVEY0WkhPQVEKK3REYURnQlc5NmZsNVkwYW5vTGZ4UDVPa1dkcjhWVUxlQkZBcVp4NlZ1QllXK3VIQlhvQXpoVTJYSjFjR1JPQQpLdkhuWUZKNmF4NmQvVU5CaDE4Yy9BWUlETjJLSnd0OEpheHhTaW9WVEhTclVvMW43RkxnM0h1NU1uUDR4bmNtCjRyRytrOW04azNtQlBxOURxRTVIQ29YN1dsbXJRVytGNHRaSHVaeS9UeEVuR29SUnk3VEQzazVCc2JTelBrVmsKZU4yR3Bza0NnWUVBN0lYaGI2Zkx5a2hHNjIwcG1kK2pERjYwdUw1Q2ZjZlpjaEJUK3A5QkxKaGlxLzZ6Mm1JUgpEUW44TU5FanBBdmpQdVRiQ0xqc0Zhc2llQVZRMW1BTXNzakNlS0FFS295VjFraUFSbmlBbEFhN2pKZEdERC9VCkZEWk5PZGJzZTIxNFVxTDdNbmJYcnlMMVAvTEJ5YXZic1JFL2ZkRllrblBuQjNiVXhsWVJyZWNDZ1lFQTdBUDcKNkxwdkRFYytqcGJCTzR5eDZTNkZGMVRjY0ZBMmx5bG9vdStvTkR0SEhMbWN1elFtcHY1RnZGZm53QklacTdXKwptSlNVMTl2S2J6Q3M5eGtieUNFalhpQnZ2bjFwUTBWZWF5SktubTBLNzZUV3A2Tk1nMXNsVDJ5WHBvRjl1WXB0CkU4VjdObVM3bm9lRDRZaldoajBvSi95eUlHNDk2UlVRMVJVaHo1VUNnWUJjK0s5SnRYcHN0QVowaWo5ZEJjUlEKbTNXTVhQVkQvZWVpWjNxclBDaGhRREVoREloajRrSnpqcVk4QmlsdFZXTHBHUXBrSGxSbW5CTjdmQk52Z09mYwo2djlPYlEvV0xkYkVHVkdhNGNrZzVwaVlSZW81N0drUDNOeDBUT0ErRUNUSXp0blExMWdtdHFZU2tmak1yaW80Ckt1QjdNMndPZlZLbDQzYjEwMlUxcHdLQmdIb00yV0dSdjhtR3NrZ0VsK3JXcUU1ZzJ6eWtpdGFkOUkwWlFkNDQKdHpCRGsxTzhmTnVtYlhLZ1BhblhmNllvOWhPQkNnNVhqOTFNOFJPdEJWVkhLOEhEdVVsODFxeVlxQzI4bXNZbQpWcFkwNTVua0VZbGhmampUa3FqUERHRnp4M09DclVPek5UTjh4aldGK09KUjVEWkpQQkhwc0tmcXVxTmxYRzRFCjNwWTlBb0dCQUlIREtiWm1jV2VOc0FVWjZSMUxmZG9wajRqV1U5ZlRpTmtEN2QxM2x3bWVObE9JdVNQaTlraHYKRnRjNkxVUnNTR3V4cFcyYUlVZm9Ubm9ucldKRTk4YUZBWHZEUVpybjk3aUg2NVA4ZmF0M1BGS2M4QWZUM1FFagpwMDJtTWp2THRRT1c1NWdUa0x1T3U4dnpVNjV3SElvbmQyUU02WWwzS252NHdBbm5yNmRnCi0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg").build();
		client = new DefaultKubernetesClient(config);
	}

	@After
	public void tearDown() throws Exception {
		client.close();
	}

}