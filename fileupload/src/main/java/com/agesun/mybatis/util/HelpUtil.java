package com.agesun.mybatis.util;
import com.agesun.mybatis.util.constant.Config;
import net.coobird.thumbnailator.Thumbnails;
/*import org.apache.commons.codec.binary.Base64;*/
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HelpUtil {
    private static final Logger LOGGER= LoggerFactory.getLogger(HelpUtil.class);

    /**
     * 得到request对象
     *
     * @return request
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        return request;
    }

    /**
     * 数字String转换成Integer
     * @param str
     * @return
     */
    public static int strToInt(String str, Integer defaultValue){
        if(StringUtils.isEmpty(str)){
            return defaultValue;
        }
        return Integer.parseInt(str);
    }

    /**
     * 时间字符串转换成Date类型
     * @param date
     * @param dateFormat 转换的格式
     * @return
     */
    public static Date strToDate(String date, String dateFormat){
        if(StringUtils.isEmpty(date)){
            return null;
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            LOGGER.error(e.getStackTrace().toString());
            return null;
        }
    }

    /**
     * 时间格式化字符串
     * @param date
     * @param dateFormat 转换的格式
     * @return
     */
    public static String dateToStr(Date date, String dateFormat){
        if(date==null){
            return null;
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);
        return simpleDateFormat.format(date);
    }

    /**
     * 依照当前时间为准，计算前两个月
     * @param num
     * @param time
     * @return
     */
    public static String beforTime(int num, String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = "";
        Calendar cal = Calendar.getInstance();
        try {
            Date dateBefore = sdf.parse(time);
            cal.setTime(dateBefore);
            cal.add(Calendar.MONTH, -2);
            str = sdf.format(cal.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            LOGGER.error(e.getStackTrace().toString());
        }
        return str;

    }

    /**
     * 计算与当前时间的差并转换成字符串
     * @param time1
     * @return
     * @throws ParseException
     */
    public static String timedifference(String time1) throws ParseException {
        String time = null;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse(time1);
        long l = (new Date()).getTime() - date.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day > 0) {
            time = day + "天";
        } else if (day == 0 && hour > 0) {
            time = hour + "小时";
        }
        if (day > 0) {
            time += hour + "小时";
        } else if (hour == 0 && min > 0) {
            time = min + "分";
        }
        if (min > 0 && hour > 0) {
            time += min + "分";
        } else if (min == 0 && s > 0) {
            time = s + "秒";
        }
        if (s > 0 && min > 0) {
            time += s + "秒";
        }
        return time;
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param time 当前时间 HH:mm:ss
     * @param strDateBegin 开始时间
     * @param strDateEnd 结束时间
     * @return boolean
     */
    public static boolean isInDate(String time, String strDateBegin, String strDateEnd)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        time=time.split(" ")[1];
        Calendar calendar=Calendar.getInstance();
        Date date=sdf.parse(time);
        calendar.setTime(date);
        Calendar calendar1=Calendar.getInstance();
        Date begin=sdf.parse(strDateBegin);
        calendar1.setTime(begin);
        Calendar calendar2=Calendar.getInstance();;
        Date end=sdf.parse(strDateEnd);
        calendar2.setTime(end);
        if(calendar.after(calendar1) && calendar.before(calendar2) || calendar.equals(calendar2)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 判断时间是否在时间段内
     *
     * @param time 当前时间 HH:mm:ss
     * @param strDateBegin 开始时间
     * @param strDateEnd 结束时间
     * @return boolean
     */
    public static boolean isInDate2(String time, String strDateBegin, String strDateEnd)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        time=time.split(" ")[1];
        Calendar calendar=Calendar.getInstance();
        Date date=sdf.parse(time);
        calendar.setTime(date);
        Calendar calendar1=Calendar.getInstance();
        Date begin=sdf.parse(strDateBegin);
        calendar1.setTime(begin);
        Calendar calendar2=Calendar.getInstance();;
        Date end=sdf.parse(strDateEnd);
        calendar2.setTime(end);
        if(calendar.after(calendar1) && calendar.before(calendar2) || calendar.equals(calendar1)){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 判断时间是否在时间段内
     *
     * @param time 当前时间 HH:mm:ss
     * @param strDateBegin 开始时间
     * @param strDateEnd 结束时间
     * @return boolean
     */
    public static boolean isInDate3(String time, String strDateBegin, String strDateEnd)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        time=time.split(" ")[1];
        Calendar calendar=Calendar.getInstance();
        Date date=sdf.parse(time);
        calendar.setTime(date);
        Calendar calendar1=Calendar.getInstance();
        Date begin=sdf.parse(strDateBegin);
        calendar1.setTime(begin);
        Calendar calendar2=Calendar.getInstance();;
        Date end=sdf.parse(strDateEnd);
        calendar2.setTime(end);
        if(calendar.after(calendar1) && calendar.before(calendar2)){
            return true;
        }else{
            return false;
        }

    }
    public static boolean isInDate4(String time, String strDateBegin, String strDateEnd)throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        time=time.split(" ")[1];
        Calendar calendar=Calendar.getInstance();
        Date date=sdf.parse(time);
        calendar.setTime(date);
        Calendar calendar1=Calendar.getInstance();
        Date begin=sdf.parse(strDateBegin);
        calendar1.setTime(begin);
        Calendar calendar2=Calendar.getInstance();;
        Date end=sdf.parse(strDateEnd);
        calendar2.setTime(end);
        if(calendar.equals(calendar1) || calendar.equals(calendar2)){
            return true;
        }
        if(calendar.after(calendar1) && calendar.before(calendar2)){
            return true;
        }else{
            return false;
        }

    }
    /**
     * 比较两个时间的前后
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(String date1, String date2){
        DateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
        if(date1.split(" ").length>1){
            date1=date1.split(" ")[1];
        }
        if(date2.split(" ").length>1){
            date2=date2.split(" ")[1];
        }
        try
        {
            Date time1=dateFormat.parse(date1);
            Date time2=dateFormat.parse(date2);
            if(time1.before(time2)){
                //data1在data2之前
                return true;
            }else{
                //data1在data2之后
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }


    /**
     * 比较两个时间的前后，包含两个时间相等
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate1(String date1, String date2){
        DateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
        if(date1.split(" ").length>1){
            date1=date1.split(" ")[1];
        }
        if(date2.split(" ").length>1){
            date2=date2.split(" ")[1];
        }
        try
        {
            Date time1=dateFormat.parse(date1);
            Date time2=dateFormat.parse(date2);
            if(time1.before(time2)){
                //data1在data2之前
                return true;
            }else{
                //data1在data2之后
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }

    public static boolean isSameDate(String unclockday, String time) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date1=simpleDateFormat.parse(unclockday);
            Date date2=simpleDateFormat.parse(time);
            Calendar calendar1=Calendar.getInstance();
            calendar1.setTime(date1);
            Calendar calendar2=Calendar.getInstance();
            calendar2.setTime(date2);
            boolean isSameYear=calendar1.get(Calendar.YEAR)==calendar2.get(Calendar.YEAR);
            boolean isSameMonth=isSameYear && calendar1.get(Calendar.MONTH)==calendar2.get(Calendar.MONTH);
            boolean isSameDay=isSameMonth && calendar1.get(Calendar.DAY_OF_MONTH)==calendar2.get(Calendar.DAY_OF_MONTH);
            return isSameDay;
        }catch(Exception e){
            return false;
        }
    }

    /**
     * 获取当前时间之前或之后几分钟 minute
     */

    public static String getAfterTimeByMinute(String time, int minute) {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        try
        {
            Date date=simpleDateFormat.parse(time);
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE, minute);
        }catch(Exception e){
            LOGGER.error(e.toString());
        }
        return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());

    }

    /**
     * 时间转换
     * @param date
     * @param dateFormat
     * @return
     */
    public static String timeStrToStr(String date, String dateFormat){
        if(StringUtils.isEmpty(date)){
            return null;
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(dateFormat);
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date time=simpleDateFormat1.parse(date);
            return simpleDateFormat.format(time);
        }catch(Exception e){
            LOGGER.error(e.toString());
            return null;
        }
    }

    /**
     * 计算两个时间差以分钟为单位
     * @param time1
     * @param time2
     * @return
     * @throws Exception
     */
    public static long getDifferenceMin(String time1, String time2) throws Exception {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm:ss");
        if(time1.split(" ").length>1){
            time1=time1.split(" ")[1];
        }
        if(time2.split(" ").length>1){
            time2=time2.split(" ")[1];
        }
        Date date1=simpleDateFormat.parse(time1);
        Date date2=simpleDateFormat.parse(time2);
        long diff=date1.getTime()-date2.getTime();
        long min=Math.abs(diff)/(1000 * 60);
        return min;
    }


    /**
     * 获取IP地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     *  加密
     * @param plainText 明文
     * @param salt      盐
     * @return          加密后的结果
     */
    public static String sha256Hash(String plainText, String salt){
        return new SimpleHash("MD5", plainText,
                ByteSource.Util.bytes(salt), 1024)
                .toHex();
    }

    /**
     * 获得异常的详细信息
     * @param e
     * @return
     */
    public static String getExceptionAllinformation(final Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        try {
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            sw.close();
            pw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
            LOGGER.error(e1.getStackTrace().toString());
        }

        return sw.toString();
    }

    /**
     * 图片上传
     * @param request
     * @param identity
     * @return
     */
    public static Map<String,Object> ImageUpload(HttpServletRequest request, String identity) {
        Map<String,Object> map=new HashMap<String,Object>();
        //文件存储本地地址(绝对路径)
        String localPath=null;
        //文件显示地址(相对路径)
        String uploadPath=null;
        try
        {
            if("1".equals(identity)){
                String classId=request.getParameter("fclass");
                if(StringUtils.isNotEmpty(classId)){
                    localPath= Config.PATH+"1701\\"+classId+"\\";
                    uploadPath="/face/image/1701/"+classId+"/";
                }else{
                    localPath=Config.PATH+"1701\\temp\\";
                    uploadPath="/face/image/1701/temp";
                }
            }else if("2".equals(identity)){
                localPath=Config.PATH+"worker\\";
                uploadPath="face/image/worker/";
            }else if("3".equals(identity)){
                localPath=Config.PATH+"visitors\\";
                uploadPath="face/image/visitors/";
            }else{
                localPath=Config.PATH;
                uploadPath="/face/image/";
            }

            File directoryFile=new File(localPath);
            // 判断路径是否存在
            if(!directoryFile.exists() && !directoryFile.isDirectory()){
                //不存在则新建
                directoryFile.mkdirs();
            }
            // 创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver();
            multipartResolver.setServletContext(request.getSession().getServletContext());
            multipartResolver.setDefaultEncoding("UTF-8");
            // 判断 request 是否有文件上传,即多部分请求
            if(multipartResolver.isMultipart(request)){
                // 转换成多部分request
                MultipartHttpServletRequest multipartHttpServletRequest= (MultipartHttpServletRequest) request;
                /**
                 * 取得request中的所有文件名
                 */
                Iterator<String> iterator=multipartHttpServletRequest.getFileNames();
                while (iterator.hasNext()){
                    // 取得上传文件的key值
                    String key=iterator.next();
                    MultipartFile multipartFile=multipartHttpServletRequest.getFile(key);
                    if(multipartFile !=null){
                        // 取得当前上传文件的文件名称
                        String fileName=multipartFile.getOriginalFilename();
                        // 如果名称不为空,说明该文件存在，否则说明该文件不存在
                        if(StringUtils.isNotEmpty(fileName.trim())){
                            String newFileName=UUID.randomUUID().toString().replaceAll("-", "");
                            map.put("fileName",newFileName);
                            //上传文件路径
                            String loadPath=localPath+newFileName+fileName.substring(fileName.lastIndexOf("."));
                            //保存文件路径进数据库
                            String savePath=uploadPath+newFileName+fileName.substring(fileName.lastIndexOf("."));
                            map.put(key,savePath);
                            //读写
                            File file=new File(loadPath);
                            multipartFile.transferTo(file);
                            //判断文件大小
                            if(file.length()/1024>50){
                                Thumbnails.Builder<File> builder=Thumbnails.of(file);
                                builder.size(1200,1000);
                                builder.outputQuality(0.25f).toFile(file);
                            }
                        }
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            LOGGER.error(e.getStackTrace().toString());
        }
        return map;
    }


    /**
     * 获得网络图片地址，或者图片地址转换成base64字节数组
     * @param imageUrl
     * @return
     */
    /*public static String GetImageStr(String imageUrl) {
        InputStream inputStream=null;
        byte[] bytes=null;
        try
        {
            inputStream=new FileInputStream(imageUrl);
            bytes=new byte[inputStream.available()];
            inputStream.read(bytes);
            inputStream.close();
        }catch(Exception e){
            LOGGER.error(e.getStackTrace().toString());
        }
        return Base64.encodeBase64String(bytes);
    }*/

    /**
     * 转成base64数组
     * @param faceArray
     * @return
     */
   /* public static byte[] getBase64StringByteFun(String faceArray) {
        return Base64.decodeBase64(faceArray);
    }*/

    /**
     * 文件删除
     * @param imageUrl
     */
    public static void deleteImage(String imageUrl) {
        File file=new File(imageUrl);
        file.delete();
        if(file.isFile() && file.exists()){
            for(int i=0;i<20;i++){
                System.gc();
                file.delete();
            }
        }
    }

    /**
     * 文件上传
     */
    public static Map<String,String> FileUpload(HttpServletRequest request){
        Map<String,String> map=new HashMap<String,String>();
        String localPath=Config.ACCESSORY_PATH;
        String uploadPath="/face/upload/";
        try
        {
            File directoryFile=new File(localPath);
            if(!directoryFile.exists() && !directoryFile.isDirectory()){
                directoryFile.mkdirs();
            }
            // 创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
            multipartResolver.setServletContext(request.getSession().getServletContext());
            multipartResolver.setDefaultEncoding("UTF-8");
            if(multipartResolver.isMultipart(request)){
                // 转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()){
                    String key=iter.next();
                    MultipartFile file = multiRequest.getFile(key);
                    if(file!=null){
                        map.put("accessorySize", String.valueOf(file.getBytes().length));
                    }
                    String fileName=file.getOriginalFilename();
                    map.put("fileName",fileName);
                    if(StringUtils.isNotEmpty(fileName)){
                        String loadPath=localPath+fileName;
                        String savePath=uploadPath+fileName;
                        File localFile=new File(loadPath);
                        if(!localFile.exists()){
                            FileOutputStream fileOutputStream=new FileOutputStream(localFile);
                            localFile.createNewFile();
                            fileOutputStream.write(file.getBytes());
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            map.put(key,savePath);
                        }
                    }
                }
            }

        }catch(Exception e){
            LOGGER.error(e.getStackTrace().toString());
        }
        return map;
    }

    /**
     * 获取当前时间之前或之后几分钟 minute
    */

    public static String getTimeByMinute(int minute) {

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.MINUTE, minute);

        return new SimpleDateFormat("HH:mm:ss").format(calendar.getTime());

    }

    /**
     * 页码计算公式
     * @param overTimeCount
     * @param pagenum
     * @return
     */
    public static int getPageNum(int overTimeCount, int pagenum) {
        int totalPage=(overTimeCount+pagenum-1)/pagenum;
        return totalPage;
    }

    /**
     * 计算时间差
     * @param time1 上班打卡时间/休息开始时间
     * @param time2 下班打卡时间/休息结束时间
     * @return
     */
    public static double getTimeDifference(String time1, String time2){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DecimalFormat decimalFormat=new DecimalFormat("###.00");
        double hours=0.00;
        try
        {
            if(StringUtils.isNotEmpty(time1) && StringUtils.isNotEmpty(time2)){
                Calendar calendar1=Calendar.getInstance();
                calendar1.setTime(simpleDateFormat.parse(time1));
                long data1=calendar1.getTimeInMillis();
                Calendar calendar2=Calendar.getInstance();
                calendar2.setTime(simpleDateFormat.parse(time2));
                long data2=calendar2.getTimeInMillis();
                hours= Double.parseDouble(decimalFormat.format((data2-data1)/1000/60/60.00));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return hours;
    }

    /**
     * 将时间与星期匹配
     * @param time1
     * @param time2
     * @return
     * @throws ParseException
     */
    public static List<PageData> getTableTh(String time1, String time2) throws ParseException {
        long days=0;
        List<PageData> list=new ArrayList<PageData>();
        String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("dd");
        Date date1=simpleDateFormat.parse(time1);
        if(StringUtils.isNotEmpty(time2)){
            Date date2=simpleDateFormat.parse(time2);
            days=(date2.getTime()-date1.getTime())/(1000*60*60*24);
        }else{
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date1);
            calendar.set(Calendar.DATE,1);
            calendar.roll(Calendar.DATE,-1);
            days=calendar.get(Calendar.DATE);
        }
        if(days!=0){
            for(int i=0;i<=days;i++){
                PageData pageData=new PageData();
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(date1);
                calendar.add(Calendar.DATE,i);
                String timeStr=simpleDateFormat1.format(calendar.getTime());
                int week=calendar.get(Calendar.DAY_OF_WEEK)-1;
                if(week<0)
                    week=0;
                String weekStr=weekDays[week];
                String data=weekStr+"("+strToInt(timeStr,null)+")";
                pageData.put("timeStr",data);
                list.add(pageData);
            }
        }
        return list;
    }

    /**
     * 获取当月第一天
     * @param date 时间
     * @param flag 判断获取当前第一天(true)还是最后一天(false)
     */
    public static String MonthFirstOrLast(Date date, boolean flag){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        if(flag){
            calendar.add(Calendar.MONTH,0);
            calendar.set(Calendar.DAY_OF_MONTH,1);
        }else{
            calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        }
        return format.format(calendar.getTime());
    }

    /**
     * 将分钟转化成小时-分钟
     * @param data
     * @return
     */
    public static String minFormatHour_Minute(String data){
        int hour=0,minute = 0;
        if(StringUtils.isEmpty(data)){
            return "0分钟";
        }
        int time=Integer.parseInt(data);
        if(time>60){
            hour=time/60;
            minute=time-(hour*60);
            return (hour+"小时"+minute+"分钟").toString();
        }
        return (time+"分钟").toString();
    }

    /**
     * Double转Int,四舍五入
     * @param work_time
     * @return
     */
    public static Object doubleToInt(double work_time) {
        BigDecimal bd=new BigDecimal(work_time).setScale(0, BigDecimal.ROUND_HALF_UP);
        return Integer.parseInt(bd.toString());
    }



    /**
     * String转换成Int
     *
     * @param str
     * @return
     */
    public static int strToInt(String str)
    {
        if (str == null)
        {
            return 0;
        }
        if (str.equals(""))
        {
            return 0;
        }

        return Integer.parseInt(str);
    }

    /**
     * String转换成int,如果为空返回设置的默认值
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static int strToIntForValue(String str, int defaultValue)
    {
        if (str == null)
        {
            return defaultValue;
        }
        if (str.equals(""))
        {
            return defaultValue;
        }
        return Integer.parseInt(str);
    }

    /**
     * 随机生成字符串
     *
     * @param length
     * @return
     */
    public static String getRandomString(int length)
    { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++)
        {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 判断数组是否包含
     * @param arr
     * @param targetVal
     * @return
     */
    public static boolean getAccount(String[] arr, Object targetVal)
    {
        if(targetVal !=null && !"".equals(targetVal)){
            for(int i=0;i<arr.length;i++){
                if(targetVal.equals(arr[i])){
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    /**
     * 获取年
     * @param yearAndMonth
     * @return
     */
    public static String getYear(String yearAndMonth){
        String year = yearAndMonth.substring(0, 4);
        return year;
    }

    /**
     * 获取月
     * @param yearAndMonth
     * @return
     */
    public static String getMonth(String yearAndMonth){
        String month = yearAndMonth.substring(5, 7);
        return month;
    }

    /**
     * 数字月份转汉字
     * @return
     * @throws ParseException
     */
    /*public static String GetCH(String input) {
        String sd = "";
        switch (input) {
            case "01":
                sd = "一月";
                break;
            case "02":
                sd = "二月";
                break;
            case "03":
                sd = "三月";
                break;
            case "04":
                sd = "四月";
                break;
            case "05":
                sd = "五月";
                break;
            case "06":
                sd = "六月";
                break;
            case "07":
                sd = "七月";
                break;
            case "08":
                sd = "八月";
                break;
            case "09":
                sd = "九月";
                break;
            case "10":
                sd = "十月";
            case "11":
                sd = "十一月";
            case "12":
                sd = "十二";
                break;
        }
        return sd;
    }*/

    public static boolean isSubMenu(List<String> list, List<String> subMenuNo) {
        boolean flag=true;
        for(String menuNo:subMenuNo){
            if(!list.contains(menuNo)){
                flag=false;
            }else{
                flag=true;
                break;
            }
        }
        return flag;
    }

    /**
     * @Description:    当月天数
     * @Param           [date]
     * @Return          java.lang.String[]
     * @Author:         lh
     * @CreateDate:     2018/08/21 18:46
     * @UpdateUser:     lh
     * @UpdateDate:     2018/08/21 18:46
     * @UpdateRemark:
     * @Version:        1.0
     */
    public static String[] getNowDayCount(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String[] dayArray=new String[31];
        Date date1=getMonthStart(date);
        Date date2=getMonthEnd(date);
        int i=0;
        while (!date1.after(date2)){
            dayArray[i]=dateToStr(date1,"MM.d");
            date1=getNext(date1);
            i++;
        }
        return dayArray;
    }
    private static Date getMonthStart(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (1 - index));
        return calendar.getTime();
    }

    private static Date getMonthEnd(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);
        int index = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.add(Calendar.DATE, (-index));
        return calendar.getTime();
    }

    private static Date getNext(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static String[] getTimeArrays(){
        String [] timeArray=new String[24];
        for(int i=0;i<24;i++){
            timeArray[i]=i+":00";
        }
        return timeArray;
    }
}
