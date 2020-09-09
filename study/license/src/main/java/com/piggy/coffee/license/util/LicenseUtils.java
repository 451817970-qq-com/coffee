package com.piggy.coffee.license.util;

import java.io.File;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.piggy.coffee.license.model.BridgeLicense;

public class LicenseUtils {

	public static BridgeLicense readLicense(String path) {
		// 许可全部内容
		File file = new File(path);
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("读取许可失败", e);
		}

		BridgeLicense lic = new BridgeLicense();

		// 1.解析 BridgeGrantInfo
		// 第一行解析为BridgeGrantInfo，暂时无需求，不需要解析

		// 2.第二行以后解析为证书。目前没有BridgeGrantInfo，从第一行开始，解析为证书
		// 2.1 构造证书数据
		StringBuilder sb = new StringBuilder().append("-----BEGIN CERTIFICATE-----").append(CertUtils.SIGN);
		for (String line : lines) {
			sb.append(line).append(CertUtils.SIGN);
		}
		sb.append("-----END CERTIFICATE-----").toString();

		// 2.2解析证书
		X509Certificate cert = CertUtils.parseCert(sb.toString());
		lic.setCert(cert);

		return lic;
	}

	// 当前未加入自定义信息，无需要私钥
	// 证书为 pem格式
	public static void createLicense(String certPath) {
		// BridgeGrantInfo to json 并且加密，暂无

		//
		File file = new File(certPath);
		List<String> lines = null;
		try {
			lines = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException("读取许可失败", e);
		}
		lines.remove(0); // 去掉 begin
		lines.remove(lines.size() - 1); // 去掉 end

		String licPath = file.getParentFile().getAbsolutePath();
		licPath = licPath + File.separator + "bridge.lic";
		
		File licFile = new File(licPath);
		try {
			FileUtils.writeLines(licFile, lines);
		} catch (IOException e) {
			throw new RuntimeException("写许可文件失败", e);
		}
	}
}
