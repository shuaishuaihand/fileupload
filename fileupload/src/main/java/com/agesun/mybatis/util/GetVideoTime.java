package com.agesun.mybatis.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetVideoTime {

    /**
     * 获取视频总时间
     */
    public Map<String,Object> getVideoTime(String video_path, String ffmpeg_path) {
        Map<String,Object> map=new HashMap<String, Object>();

        List<String> commands = new ArrayList<String>();
        commands.add(ffmpeg_path);
        commands.add("-i");
        commands.add(video_path);
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commands);
            Process p = builder.start();

            //从输入流中读取视频信息
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line);
            }
            br.close();

            //从视频信息中解析时长
            String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
            Pattern pattern = Pattern.compile(regexDuration);
            Matcher m = pattern.matcher(stringBuilder.toString());
            if (m.find()) {
                int time = getTimelen(m.group(1));
                //视频时长
                map.put("adv_time",time);
            }

            String regexVideo = "Video: (.*?), (.*?), (.*?)[,\\s]";
            pattern = Pattern.compile(regexVideo);
            m = pattern.matcher(stringBuilder.toString());
            if (m.find()) {
                //视频的分辨率
                map.put("adv_resolving_power",m.group(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    // 格式:"00:00:10.68"
    private int getTimelen(String timelen) {
        int min = 0;
        String strs[] = timelen.split(":");
        if (strs[0].compareTo("0") > 0) {
            // 秒
            min += Integer.valueOf(strs[0]) * 60 * 60;
        }
        if (strs[1].compareTo("0") > 0) {
            min += Integer.valueOf(strs[1]) * 60;
        }
        if (strs[2].compareTo("0") > 0) {
            min += Math.round(Float.valueOf(strs[2]));
        }
        return min;
    }

}
