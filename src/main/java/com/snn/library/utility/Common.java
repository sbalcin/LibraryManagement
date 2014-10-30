package com.snn.library.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import com.snn.library.beans.Book;

public class Common {
	public static boolean checkCaptcha(Book book) {
		try {
			String privateKey = "6Ldb7PwSAAAAACQD-7Dddx7C1PD5S1C4G4V3-MND";
			String remoteIp = "127.0.0.1";
			String searchUri = "http://www.google.com/recaptcha/api/verify?privatekey=" + privateKey + "&remoteip=" + remoteIp + "&challenge="
					+ book.getCaptchaChallenge() + "&response=" + book.getCaptchaResponse().replace(" ", "%20");
			
			System.out.println("url => " + searchUri);
			HttpURLConnection connection = (HttpURLConnection) new URL(searchUri).openConnection();
			connection.connect();

			BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
			StringBuilder total = new StringBuilder();
			String line;
			while ((line = r.readLine()) != null) {
				total.append(line);
			}
			connection.disconnect();

			String response = total.toString();
			if (response.contains("success"))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
