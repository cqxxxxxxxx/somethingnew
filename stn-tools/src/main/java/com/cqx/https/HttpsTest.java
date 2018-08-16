package com.cqx.https;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by BG307435 on 2018/2/24.
 */
public class HttpsTest {

    private static TrustManager tm = new TrustManagerM();

    private static HostnameVerifier hv = new HostnameVerifierM();

    /**
     * javax.net.ssl.SSLHandshakeException:
     * sun.security.validator.ValidatorException:
     * PKIX path building failed:
     * sun.security.provider.certpath.SunCertPathBuilderException:
     * unable to find valid certification path to requested target
     *
     * @throws IOException
     */
    @Test
    public void withoutCer() throws IOException {
//        URL url = new URL("https://www.v2ex.com/?tab=all");
        URL url = new URL("https://testopen.95155.com/apis/login/EF4EA02B7366009D14E40A9AA0BAF0219820E1DB223D49F500B24E2EB8ED520295798E843484ECA22C9590F18CC23532DFF1BFD78C6E9899653D68CCAF76957A14F7854F4C1F109A0A04F56D12EDE31D8A27BB8929A59CB6CAB4E47ED7E2F756?client_id=296b886e-01df-4d46-b6c0-856672dbf31a");

        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setDoOutput(true);
        httpsConn.setRequestMethod("POST");
        httpsConn.setDoInput(true);
        httpsConn.connect();
        OutputStream out = httpsConn.getOutputStream();
        byte[] a = new byte[2048];
        byte[] b = new byte[2048];

        InputStream in = httpsConn.getInputStream();
        out.write(a);
        in.read(b);
        System.out.println("a::::" + new String(a, "UTF-8"));
        System.out.println("b::::" + new String(b, "UTF-8"));
        httpsConn.disconnect();
    }


    @Test
    public void withCer() throws IOException, NoSuchAlgorithmException, KeyManagementException {
        SSLContext ctx = SSLContext.getInstance("TLS");
        SSLSocketFactory ssf = ctx.getSocketFactory();

        URL url = new URL("https://testopen.95155.com/apis/login/EF4EA02B7366009D14E40A9AA0BAF0219820E1DB223D49F500B24E2EB8ED520295798E843484ECA22C9590F18CC23532DFF1BFD78C6E9899653D68CCAF76957A14F7854F4C1F109A0A04F56D12EDE31D8A27BB8929A59CB6CAB4E47ED7E2F756?client_id=296b886e-01df-4d46-b6c0-856672dbf31a");
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        httpsConn.setHostnameVerifier(hv);
        httpsConn.setDoOutput(true);
        httpsConn.setRequestMethod("POST");
        httpsConn.connect();
        OutputStream out = httpsConn.getOutputStream();
        byte[] a = new byte[2048];
        out.write(a);
        System.out.println(a.toString());
        httpsConn.disconnect();
//        List<String> a = new ArrayList<>();
//        a.retainAll()
    }

    @Test
    public void apacheHttpClient() throws NoSuchAlgorithmException, KeyManagementException, IOException {
        SSLContext ctx = SSLContext.getInstance("TLS");

        ctx.init(null, new TrustManager[]{tm}, null);
        HttpClient httpClient = HttpClientBuilder.create().setSSLContext(ctx).setSSLHostnameVerifier(hv).build();
        HttpGet get = new HttpGet("https://abcdefg.com/");
        HttpResponse response = httpClient.execute(get);
        response.getEntity().writeTo(System.out);
    }

    @Test
    public void encodeAndDecode() throws UnsupportedEncodingException {
        String str = "C9730A8D90D5A0B856E2622EBC9051CB11603C6739F76EB5E2828C3DE72B20E0";
        String result = URLDecoder.decode(str, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void test() {
//        String a = "{\"result\":{\"adr\":\"湖南省长沙市长沙县张家围子，向北方向，306米\",\"city\":\"长沙市\",\"country\":\"长沙县\",\"drc\":\"199\",\"lat\":\"16967640\",\"lon\":\"67842889\",\"province\":\"湖南省\",\"spd\":\"57.0\",\"utc\":\"1520233472000\"},\"status\":1001}";
//        JSONObject jsonObject = (JSONObject) JSON.toJSON(a);
        Map o = JSON.parseObject(null, Map.class);
//        Object b  =  JSON.parse(a, Feature.DisableCircularReferenceDetect);
//        System.out.println(a);
        Integer c = null;
        HttpsTest httpsTest = JSON.parseObject(JSON.toJSONString(null), HttpsTest.class);
//        System.out.println(c.equals(11));
        Map a = new HashMap();
        Object b = a.get("adfasf");
        System.out.println(b);

        JSONObject jsonObject = JSON.parseObject("{}");
        jsonObject.put("success", true);
        System.out.println(jsonObject);
        boolean d = (boolean) jsonObject.get("success");
        Integer status = jsonObject.getInteger("status");
        System.out.println(status);
        System.out.println(d);
        System.out.println(jsonObject.getIntValue("asdfas"));
    }


    private static class HostnameVerifierM implements HostnameVerifier {

        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

    private static class TrustManagerM implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
