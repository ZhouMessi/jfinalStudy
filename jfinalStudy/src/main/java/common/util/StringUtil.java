package common.util;



//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {
    public static final String encoding = "UTF-8";

    public StringUtil() {
    }

    public static String cutNull(String str) {
        return null == str ? "" : str.trim();
    }

    public static String cutNullBlank(String str) {
        return cutNull(str).replaceAll("\\s+", "");
    }

    public static String cutNullBlankCase(String str) {
        return cutNullBlank(str).toUpperCase();
    }

    public static boolean isNull(String str) {
        return null == str || "".equals(str.trim()) || "null".equalsIgnoreCase(str);
    }

    public static boolean isNull(Collection c) {
        return null == c || 0 == c.size();
    }

    public static boolean isNull(Map m) {
        return null == m || 0 == m.size();
    }

    public static boolean isNull(Object o) {
        return null == o || "".equals(o.toString().trim()) || "null".equals(o.toString());
    }

    public static boolean notNull(Object o) {
        return !isNull(o);
    }

    public static boolean notNull(String str) {
        return !isNull(str);
    }

    public static boolean notNull(Collection c) {
        return !isNull(c);
    }

    public static boolean notNull(Map m) {
        return !isNull(m);
    }

    public static boolean notNull(Object[] c) {
        return !isNull(c);
    }

    public static boolean isNull(Object[] c) {
        if (null != c && c.length > 0) {
            Object[] var1 = c;
            int var2 = c.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                Object o = var1[var3];
                if (null == o) {
                    return true;
                }
            }

            return false;
        } else {
            return true;
        }
    }

    public static String getAutoIncrementNum(String currNum, int len) {
        Long newNum = new Long(currNum) + 1L;
        String newNumStr = newNum.toString();
        int moreZore = len - newNumStr.length();

        for(int i = 0; i < moreZore; ++i) {
            newNumStr = "0" + newNumStr;
        }

        return newNumStr;
    }

    public static String getAutoIncrementNum(String currNum) {
        return getAutoIncrementNum(currNum, currNum.length());
    }

    public static String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }

    public static Integer convertObj2Int(Object obj, Integer defaultInt) {
        if (null == obj) {
            return defaultInt;
        } else {
            Integer resultInt = null;

            try {
                resultInt = Integer.parseInt(obj.toString().trim());
            } catch (Exception var4) {
                resultInt = defaultInt;
            }

            return resultInt;
        }
    }

    public static Double convertObj2Double(Object obj, Double defaultDouble) {
        if (null == obj) {
            return defaultDouble;
        } else {
            Double resultDouble = null;

            try {
                resultDouble = Double.parseDouble(obj.toString().trim());
            } catch (Exception var4) {
                resultDouble = defaultDouble;
            }

            return resultDouble;
        }
    }

    public static String getRandomNum(int len) {
        String result = "";
        Random r = new Random();

        for(int i = 0; i < len; ++i) {
            result = result + r.nextInt(10);
        }

        return result;
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }




    public static String replacePicShortUrl2Full(String appUrlBase, String content) {
        return (String)replacePicShortUrl(appUrlBase, content).get(0);
    }

    public static String getFirstPicFullUrl(String appUrlBase, String content) {
        return (String)replacePicShortUrl(appUrlBase, content).get(1);
    }

    public static boolean equals(String str1, String str2) {
        boolean b = false;
        if (StringUtils.equals(str1, str2)) {
            b = true;
        }

        return b;
    }

    public static List<String> replacePicShortUrl(String appUrlBase, String content) {
        List<String> list = new ArrayList();
        String firstPic = null;
        String regEx = "src=\\\"[^\\\"]+\\\"";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(content);

        while(mat.find()) {
            String oneItem = mat.group();
            String toReplaceItem = oneItem.substring(5, oneItem.length() - 1);
            if (!toReplaceItem.startsWith("http")) {
                String realUrl = oneItem.substring(0, 5) + appUrlBase + toReplaceItem + oneItem.substring(oneItem.length() - 1, oneItem.length());
                content = content.replace(oneItem, realUrl);
            }

            if (null == firstPic) {
                firstPic = (toReplaceItem.startsWith("http") ? "" : appUrlBase) + oneItem.substring(5, oneItem.length() - 1);
            }
        }

        list.add(content);
        list.add(firstPic);
        return list;
    }

    public static boolean isCount(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[1-9]\\d*");
            return pattern.matcher(str.trim()).matches();
        }
    }

    public static boolean isNumber(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[0-9]\\d*");
            return pattern.matcher(str.trim()).matches();
        }
    }

    public static boolean isNumberAndAlphabet(String str) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        return pattern.matcher(str.trim()).matches();
    }

    public static boolean isChinese(String value) {
        if (isNull(value)) {
            return false;
        } else {
            boolean result = true;
            Pattern p = Pattern.compile("[一-龥]");
            if (value.trim().length() > 0) {
                char[] var3 = value.toCharArray();
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    char c = var3[var5];
                    if (!p.matcher(String.valueOf(c)).find()) {
                        result = false;
                        break;
                    }
                }
            }

            return result;
        }
    }

    public static boolean isInt(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("-?\\d*");
            return pattern.matcher(str.trim()).matches();
        }
    }

    public static boolean isMoney(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]{0,2}");
            return pattern.matcher(str.trim()).matches();
        }
    }

    public static boolean isMoneyOneDecimalPoint(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]{0,1}");
            return pattern.matcher(str.trim()).matches();
        }
    }

    public static boolean isEmail(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
            return pattern.matcher(str).matches();
        }
    }

    public static boolean isPhone(String str) {
        if (isNull(str)) {
            return false;
        } else {
            str = str.replace(".", "").replace("E10", "");
            Pattern pattern = Pattern.compile("^13\\d{9}||15[8,9]\\d{8}$");
            return pattern.matcher(str).matches();
        }
    }

    public static boolean isAccount(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]{4,15}$");
            return pattern.matcher(str).matches();
        }
    }

    public static boolean isMobile(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^1[3-9]\\d{9}$");
            return pattern.matcher(str).matches();
        }
    }

    public static boolean isTel(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("^(\\d{3}-?\\d{8})|(\\d{4}-?\\d{7})$");
            return pattern.matcher(str).matches();
        }
    }

    public static boolean isPostCode(String str) {
        if (isNull(str)) {
            return false;
        } else {
            Pattern pattern = Pattern.compile("[1-9]\\d{5}(?!\\d)");
            return pattern.matcher(str).matches();
        }
    }

    public static boolean isBlank(String str) {
        return StringUtils.isBlank(str) || "null".equals(str);
    }

    public static boolean notBlank(String str) {
        return !isBlank(str);
    }

    public static String getRandom() {
        String num = Math.round(Math.random() * new Double(8.9E9D) + 1.0E9D) + "";
        return num;
    }

    public static String getFileType(String fileName) {
        return null != fileName && fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
    }

    public static String getUnitCode(String codePrefix) {
        return codePrefix + System.currentTimeMillis() + RandomStringUtils.random(3, false, true);
    }

    public static String genUnitCode(String codePrefix) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateStr = sdf.format(date);
        return codePrefix + dateStr + RandomStringUtils.random(2, false, true);
    }

    public static String arrayToString(String split, Object... objectList) {
        String ids = "";
        if (notNull(objectList)) {
            Object[] var3 = objectList;
            int var4 = objectList.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Object id = var3[var5];
                ids = ids + id + split;
            }

            if (ids.indexOf(split) > 0) {
                ids = ids.substring(0, ids.length() - 1);
            }
        }

        return ids;
    }

    public static String arrayToStringForSql(String split, String[] arrStr) {
        String ids = "";
        if (notNull((Object[])arrStr)) {
            String[] var3 = arrStr;
            int var4 = arrStr.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Object id = var3[var5];
                ids = ids + "'" + id + "'" + split;
            }

            if (ids.indexOf(split) > 0) {
                ids = ids.substring(0, ids.length() - 1);
            }
        }

        return ids;
    }

    public static String listToString(String split, Collection objectList) {
        String ids = "";

        Object id;
        for(Iterator var3 = objectList.iterator(); var3.hasNext(); ids = ids + id + split) {
            id = var3.next();
        }

        if (ids.indexOf(split) > 0) {
            ids = ids.substring(0, ids.length() - 1);
        }

        return ids;
    }

    public static String listToStringForSql(String split, List<String> listStr) {
        String ids = "";
        if (notNull((Collection)listStr)) {
            Object id;
            for(Iterator var3 = listStr.iterator(); var3.hasNext(); ids = ids + "'" + id + "'" + split) {
                id = var3.next();
            }

            if (ids.indexOf(split) > 0) {
                ids = ids.substring(0, ids.length() - 1);
            }
        }

        return ids;
    }

    public static List<String> stringToList(String split, String str) {
        List<String> strList = new ArrayList();
        if (notNull(str) && str.length() > 0) {
            String[] strArray = str.split(split);
            String[] var4 = strArray;
            int var5 = strArray.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String s = var4[var6];
                strList.add(s);
            }
        }

        return strList;
    }

    public static String postRequest(String strURL, String strCharset) throws Exception {
        if (null != strURL && 0 != strURL.length()) {
            if (null == strCharset || 0 == strCharset.length()) {
                strCharset = "UTF-8";
            }

            String[] arrContent = (String[])null;
            if (strURL.indexOf("?") > -1) {
                arrContent = string2Array(strURL.substring(strURL.indexOf("?") + 1), '&', false);
                strURL = strURL.substring(0, strURL.indexOf("?"));
            }

            StringBuffer sb = new StringBuffer();
            HttpURLConnection con = null;

            String line;
            try {
                URL url = new URL(strURL);
                con = (HttpURLConnection)url.openConnection();
                con.setDoOutput(true);
                con.setDoInput(true);
                con.setInstanceFollowRedirects(true);
                con.setRequestMethod("POST");
                con.addRequestProperty("Content-type", "application/x-www-form-urlencoded");
                con.setUseCaches(false);
                con.connect();
                if (null != arrContent && arrContent.length > 0) {
                    StringBuffer sbContent = new StringBuffer();

                    for(int i = 0; i < arrContent.length; ++i) {
                        if (null != arrContent[i] && arrContent[i].indexOf("=") != -1) {
                            sbContent.append(arrContent[i].substring(0, arrContent[i].indexOf("="))).append('=');
                            sbContent.append(URLEncoder.encode(arrContent[i].substring(arrContent[i].indexOf("=") + 1), strCharset)).append('&');
                        }
                    }

                    DataOutputStream out = new DataOutputStream(con.getOutputStream());
                    out.writeBytes(sbContent.toString());
                    out.flush();
                    out.close();
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), strCharset));

                while(null != (line = reader.readLine())) {
                    sb.append(line);
                }

                con.disconnect();
                String var8 = sb.toString();
                return var8;
            } catch (Exception var12) {
                System.out.print(var12.getMessage());
                sb.append(var12.getMessage());
                String str1 = sb.toString();
                line = str1;
            } finally {
                if (null != con) {
                    con = null;
                }

            }

            return line;
        } else {
            return null;
        }
    }

    public static String[] string2Array(String s, char delim, boolean trim) {
        if (0 == s.length()) {
            return new String[0];
        } else {
            List<String> a = new ArrayList();
            int start = 0;
            int end = 0;

            String p;
            for(int len = s.length(); end < len; ++end) {
                char c = s.charAt(end);
                if (c == delim) {
                    p = s.substring(start, end);
                    a.add(trim ? p.trim() : p);
                    start = end + 1;
                }
            }

            p = s.substring(start, end);
            a.add(trim ? p.trim() : p);
            return (String[])((String[])a.toArray(new String[a.size()]));
        }
    }

    public static Integer[] stringToArray(String s, String split) {
        if (!notNull(s)) {
            return null;
        } else {
            String[] array = s.split(split);
            Integer[] num = new Integer[array.length];

            for(int i = 0; i < num.length; ++i) {
                num[i] = Integer.parseInt(array[i]);
            }

            return num;
        }
    }

    public static String toUnicode(String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";

        for(int byteIndex = 0; byteIndex < utfBytes.length; ++byteIndex) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }

            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }

        System.out.println("unicodeBytes is: " + unicodeBytes);
        return unicodeBytes;
    }

    public static boolean checkByRegex(String reg, Object check) {
        String checkStr = null;
        if (null == check) {
            return false;
        } else {
            checkStr = check.toString().trim();
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(checkStr);
            return matcher.matches();
        }
    }

    public static boolean arrayContain(String arrayStr, String key, String split) {
        if (notNull(arrayStr)) {
            String[] array = arrayStr.split(split);
            String[] var4 = array;
            int var5 = array.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                String s = var4[var6];
                if (s.trim().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkRepeat(String[] array) {
        Set<String> set = new HashSet();
        String[] var2 = array;
        int var3 = array.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String str = var2[var4];
            set.add(str);
        }

        return set.size() == array.length;
    }

    public static String removeHtml(String html, Integer len) {
        if (isNull(html)) {
            return "";
        } else {
            html = html.replaceAll("&nbsp;", " ").replaceAll("\\&[a-zA-Z]{0,9};", "").replaceAll("<!--[^>]*>", " ").replaceAll("<[^>]*>", "\n");
            String result = "";
            String[] var3 = html.split("\n");
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String s = var3[var5];
                if (notBlank(s)) {
                    result = result + s + "\t";
                }

                int tempLen = result.length();
                if (len != null && tempLen > len) {
                    return result.substring(0, len) + "...";
                }
            }

            return result;
        }
    }

    public static List<String> listUnique(List<String> list) {
        List<String> newList = new LinkedList();

        for(int i = 0; i < list.size(); ++i) {
            if (!newList.contains(list.get(i))) {
                newList.add(list.get(i));
            }
        }

        return newList;
    }

    public static String getMidPath(String imgName) {
        String fileType = imgName.substring(imgName.lastIndexOf("."));
        String midName = imgName.substring(0, imgName.lastIndexOf(".")) + "_mid" + fileType;
        return midName;
    }

    public static String getSmallPath(String imgName) {
        String fileType = imgName.substring(imgName.lastIndexOf("."));
        String midName = imgName.substring(0, imgName.lastIndexOf(".")) + "_small" + fileType;
        return midName;
    }

    public static int[] StringArrToIntArr(Object[] strArr) {
        if (strArr != null && strArr.length != 0) {
            int[] intArr = new int[strArr.length];

            for(int i = 0; i < intArr.length; ++i) {
                if (!isInt(strArr[i].toString())) {
                    return null;
                }

                intArr[i] = Integer.valueOf(strArr[i].toString());
            }

            return intArr;
        } else {
            return null;
        }
    }

    public static BigDecimal[] StringArrToBigDecimalArr(Object[] strArr) {
        if (strArr != null && strArr.length != 0) {
            BigDecimal[] intArr = new BigDecimal[strArr.length];

            for(int i = 0; i < intArr.length; ++i) {
                if (!isInt(strArr[i].toString())) {
                    return null;
                }

                intArr[i] = new BigDecimal(strArr[i].toString());
            }

            return intArr;
        } else {
            return null;
        }
    }

    public static String[] IntArrToStringArr(Integer[] intArr) {
        if (intArr != null && intArr.length != 0) {
            String[] strArr = new String[intArr.length];

            for(int i = 0; i < intArr.length; ++i) {
                if (!notNull((Object)intArr[i])) {
                    return null;
                }

                strArr[i] = intArr[i].toString();
            }

            return strArr;
        } else {
            return null;
        }
    }

    public static String hideUserName(String userName) {
        if (userName.startsWith("eq_") && userName.length() == 16) {
            return userName.substring(0, 6) + "******" + userName.substring(12, 16);
        } else if (userName.length() < 4) {
            return userName.substring(0, 1) + "******";
        } else {
            return userName.length() < 10 ? userName.substring(0, 3) + "******" + userName.substring(4, userName.length()) : userName.substring(0, 5) + "******" + userName.substring(8, userName.length());
        }
    }

    public static boolean hasSpecialString(String inputString) {
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(inputString).lookingAt();
    }

    public static boolean hasEmojiCharacter(String source) {
        if (isNull(source)) {
            return false;
        } else {
            Pattern emoji = Pattern.compile("^[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[☀-⟿]$");
            Matcher matcher = emoji.matcher(source);
            return matcher.find();
        }
    }

    public static String removeTailZero(BigDecimal val) {
        return isNull((Object)val) ? null : val.stripTrailingZeros().toPlainString();
    }

    public static void removeTailZeroByJSONObject(JSONObject jsonObject, String... keys) {
        if (jsonObject != null && keys != null && keys.length != 0) {
            String[] var3 = keys;
            int var4 = keys.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String k = var3[var5];
                BigDecimal temp = jsonObject.getBigDecimal(k);
                if (temp == null) {
                    jsonObject.put(k, removeTailZero(BigDecimal.ZERO));
                } else {
                    jsonObject.put(k, removeTailZero(temp));
                }
            }

        }
    }

    public static String replaceAllSpace(String val) {
        return isBlank(val) ? "" : val.replaceAll("\\s*", "");
    }

    public static String replaceAllSpaceAndEnter(String val) {
        String tmpVal = isNull(val) ? "" : Pattern.compile("\t|\r|\n|").matcher(val).replaceAll("");
        return tmpVal;
    }

    public static String mobileEncryption(String mobile) {
        String mobile1 = StringUtils.trim(mobile);
        return notBlank(mobile1) ? mobile1.substring(0, 3) + "*****" + mobile1.substring(8) : "";
    }

    public static String encryption(String value) {
        String value1 = StringUtils.trim(value);
        if (isBlank(value1)) {
            return "";
        } else {
            StringBuilder result = new StringBuilder(value1.substring(0, 2));
            int length = value1.length();

            for(int i = 0; i < length - 4; ++i) {
                result.append("*");
            }

            result.append(value1.substring(length - 2));
            return result.toString();
        }
    }

    public static List<String> matchs(String regex, String taget) {
        List<String> list = new ArrayList();
        if (!isBlank(regex) && !isBlank(taget)) {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(taget);

            while(m.find()) {
                list.add(m.group());
            }

            return list;
        } else {
            return list;
        }
    }

    public static boolean isZeroToOnehundredInteger(String valPara) {
        String val = StringUtils.trim(valPara);
        return StringUtils.isBlank(val) ? false : val.matches("([0-9])|([1-9][0-9])|([1][0][0])");
    }
}
