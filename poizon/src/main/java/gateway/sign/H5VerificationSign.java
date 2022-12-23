package gateway.sign;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shizhuang.sign.SignEnum;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.alibaba.fastjson.parser.Feature.OrderedField;

public class H5VerificationSign {

    public static void main(String[] args) {
        Map<String, Object> body = new HashMap<>();
        JSONObject pageInfo = new JSONObject();
        pageInfo.put("pageIndex", 1);
        pageInfo.put("pageSize", 20);
        body.put("pageRequestInfo", pageInfo);

        JSONObject jsonObject = new JSONObject(body);
        System.out.println(sign(jsonObject, null, null, "POST"));
    }
    /**
     * app 签名
     * @param requestBody 请求体
     * @param requestParams url ？后的参数 和 表单内容 组成map 只要？后有参数就加进来
     * @param headers 所有header组成map
     * @param httpMethod 请求方式 eg: POST,GET
     * @return sign 签名字符串
     */
    public static String sign(Object requestBody, Map<String, String> requestParams, Map<String, String> headers, String httpMethod) {
        TreeMap<String, String> paramTreeMap = new TreeMap<String, String>();
        String formData = JSONObject.toJSONString(requestBody);
        if ("POST".equals(httpMethod) && Objects.nonNull(requestBody)) {
            // body转成TreeMap
            paramTreeMap = jsonToMap(formData);
        }
        //添加问号后的参数到map
        paramTreeMap = getReqParams(paramTreeMap, requestParams);
        return generateH5Sign(paramTreeMap);
    }

    private static String generateH5Sign(TreeMap<String, String> paramTreeMap) {
        String appId = paramTreeMap.get("appId");
        paramTreeMap.remove("newSign");
        paramTreeMap.remove("sign");
        String sortedKvStr = paramTreeMap.entrySet().stream().map(entry -> entry.getKey() + entry.getValue()).reduce("", String::concat);
        if (StringUtils.isEmpty(appId)) {
            sortedKvStr += SignEnum.H5.key;
        } else {
            sortedKvStr += SignEnum.getKeyWithAppId(appId);
        }
        sortedKvStr = new String(sortedKvStr.getBytes(StandardCharsets.UTF_8));
        //org.apache.commons.codec.digest.DigestUtils#md5Hex(java.lang.String)
        return DigestUtils.md5Hex(sortedKvStr);
    }

    public static TreeMap<String, String> jsonToMap(String formData){
        TreeMap<String, String> reqParams = new TreeMap<>();
        JSONObject jsonObject = JSONObject.parseObject(formData, OrderedField);
        jsonObject.forEach((key, value) -> {
            String collect = "";
            if (value instanceof JSONArray) {
                collect = ((JSONArray) value).stream().map(Objects::toString).collect(Collectors.joining(","));
            } else {
                if(value!=null) {
                    collect = value.toString();
                }
            }
            reqParams.putIfAbsent(key, value == null ? "" : collect);
        });
        return reqParams;
    }

    public static TreeMap<String, String> getReqParams(TreeMap<String, String> tm, Map<String, String> parameters) {
        if(parameters == null || parameters.isEmpty()){
            return tm;
        }
        parameters.forEach((key, value) -> {
            if ("sign".equals(key)) {
                tm.put("sign", value);
            }
            if (!"sign".equals(key) && !"newSign".equals(key)) {
                tm.putIfAbsent(key, value == null ? "" : String.join(",", value));
            }
        });
        return tm;
    }
}
