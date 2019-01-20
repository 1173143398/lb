package com.wechat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class NetWorkUtil {
    /**
     * 发起HTTPS请求
     * @param reqUrl
     * @param requestMethod
     * @return 相应字符串
     */
    public static String getHttpsResponse(String reqUrl, String requestMethod,String content) {
        URL url;
        InputStream is;
        String result ="";

        try {
            url = new URL(reqUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

            TrustManager[] tm = {xtm};
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });

            con.setDoInput(true); //允许输入流，即允许下载

            con.setDoOutput(true); //允许输出流，即允许上传
            con.setUseCaches(false); //不使用缓冲
            if (null != requestMethod && !requestMethod.equals("")) {
                con.setRequestMethod(requestMethod); //使用指定的方式
                OutputStream outputStream = con.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
                bw.write(content);
                bw.flush();
                bw.close();
                outputStream.close();
            } else {
                con.setRequestMethod("GET"); //使用get请求
            }
            
            is = con.getInputStream();   //获取输入流，此时才真正建立链接
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferReader = new BufferedReader(isr);
            String inputLine;
            while ((inputLine = bufferReader.readLine()) != null) {
                result += inputLine + "\n";
            }
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

  static X509TrustManager xtm = new X509TrustManager() {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        @Override
        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }
    };
}
