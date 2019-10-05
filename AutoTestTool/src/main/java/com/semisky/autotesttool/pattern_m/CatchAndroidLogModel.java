package com.semisky.autotesttool.pattern_m;

import com.semisky.autotesttool.app.AutoTestApp;
import com.semisky.autotesttool.bean.ProcessInfo;
import com.semisky.autotesttool.constant.Definition;
import com.semisky.autotesttool.utils.PreferenceUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CatchAndroidLogModel implements ICatchAndroidLogModel {
    private static final String TAG = CatchAndroidLogModel.class.getSimpleName();

    private SimpleDateFormat mDataFormat;
    private String mFilterLogPriority;
    private String mFilterLogTag;

    private Process mLogcatProcess;

    // 判断sdcard上的日志文件是否可以删除
    boolean canDeleteSDLog(String createDateStr) {
        boolean canDel = false;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1 * 7);// 删除7天之前日志
        Date expiredDate = calendar.getTime();
        try {
            Date createDate = mDataFormat.parse(createDateStr);
            canDel = createDate.before(expiredDate);
        } catch (ParseException e) {
            canDel = false;
        }
        return canDel;
    }

    /**
     * 去除文件的扩展类型（.log）
     *
     * @param fileName
     * @return
     */
    String getFileNameWithoutExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("_")+1, fileName.lastIndexOf("."));
    }

    /**
     * 删除SD卡下过期的日志
     */
    private void deleteSDcardExpiredLog(String logPath) {
        File file = new File(logPath);
        if (file.isDirectory()) {
            File[] allFiles = file.listFiles();
            if (allFiles != null) {
                for (File logFile : allFiles) {
                    String fileName = logFile.getName();
                    if(null ==  fileName || !fileName.startsWith(Definition.ANDROID_LOG_FILE_PREFIX)){
                        continue;
                    }
                    String createDateInfo = getFileNameWithoutExtension(fileName);
                    if (canDeleteSDLog(createDateInfo)) {
                        logFile.delete();
                    }
                }
            }
        }
    }

    /**
     * 制作记录日志命令
     * adb logcat  -f /sdcard/ly888/log4.txt -v time -s AlarmManager:D -s Watchdog:W
     *
     * @return
     */
    List<String> makeRecordLogCmd() {
        List<String> cmdList = new ArrayList<String>();
        cmdList.add(Definition.LOGCAT);
        cmdList.add(Definition.LogcatOptions.F);
        cmdList.add(makeAndroidLogPath());
        cmdList.add(Definition.LogcatOptions.V);
        cmdList.add(Definition.LogcatFormat.TIME);
        cmdList.add(Definition.LogcatOptions.S);
        cmdList.add(mFilterLogTag + mFilterLogPriority);
        return cmdList;
    }

    void sleep(long millis){
        try{
            Thread.sleep(millis);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 创建日志收集器
    void createLogCollector() {
        List<String> cmdList = makeRecordLogCmd();
        try {
            mLogcatProcess = Runtime.getRuntime().exec(cmdList.toArray(new String[cmdList.size()]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取本程序的用户名称
     *
     * @param packName
     * @param allProcList
     * @return
     */
    private String getAppUser(String packName, List<ProcessInfo> allProcList) {
        for (ProcessInfo processInfo : allProcList) {
            if (processInfo.getName().equals(packName)) {
                return processInfo.getUser();
            }
        }
        return null;
    }

    /*
     * 关闭由本程序开启的logcat进程： 根据用户名称杀死进程(如果是本程序进程开启的Logcat收集进程那么两者的USER一致)
     * 如果不关闭会有多个进程读取logcat日志缓存信息写入日志文件
     */
    private void killLogcatProc(List<ProcessInfo> allProcList) {
        if (mLogcatProcess != null) {
            mLogcatProcess.destroy();
        }
        String packName = AutoTestApp.getInstance().getCtx().getPackageName();
        String myUser = getAppUser(packName, allProcList);
        for (ProcessInfo processInfo : allProcList) {
            String name = processInfo.getName().toLowerCase(Locale.getDefault());
            if (Definition.LOGCAT.equals(name) && processInfo.getUser().equals(myUser)) {
                android.os.Process.killProcess(Integer
                        .parseInt(processInfo.getPid()));
            }
        }
    }

    // 根据ps命令得到的内容获取PID，User，name等信息
    private List<ProcessInfo> getProcessInfoList(List<String> orgProcessList) {
        List<ProcessInfo> procInfoList = new ArrayList<ProcessInfo>();
        for (int i = 1; i < orgProcessList.size(); i++) {
            String processInfo = orgProcessList.get(i);
            String[] proStr = processInfo.split(" ");
            // USER PID PPID VSIZE RSS WCHAN PC NAME
            // root 1 0 416 300 c00d4b28 0000cd5c S /init
            List<String> orgInfo = new ArrayList<String>();
            for (String str : proStr) {
                if (!"".equals(str)) {
                    orgInfo.add(str);
                }
            }
            if (orgInfo.size() == 9) {
                ProcessInfo pInfo = new ProcessInfo();
                pInfo.setUser(orgInfo.get(0));
                pInfo.setPid(orgInfo.get(1));
                pInfo.setPpid(orgInfo.get(2));
                pInfo.setName(orgInfo.get(8));
                procInfoList.add(pInfo);
            }
        }
        return procInfoList;
    }

    // 清除绑在日志
    void clearLogCache() {
        Process proc = null;
        List<String> cmdList = new ArrayList<String>();
        cmdList.add(Definition.LOGCAT);
        cmdList.add(Definition.LogcatOptions.C);
        try {
            proc = Runtime.getRuntime().exec(cmdList.toArray(new String[cmdList.size()]));
            proc.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                proc.destroy();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 运行PS命令得到进程信息(USER PID PPID VSIZE RSS WCHAN PC NAME root 1 0 416 300 c00d4b28)
    private List<String> getProcessList() {
        List<String> orgProcList = new ArrayList<String>();
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("ps");
            InputStreamReader isr = new InputStreamReader(proc.getInputStream());
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while (null != line) {
                if (null != orgProcList) {
                    orgProcList.add(line);
                }
                line = br.readLine();
            }
            proc.waitFor();
        } catch (Exception e) {
        } finally {
            try {
                proc.destroy();
            } catch (Exception e) {
            }
        }
        return orgProcList;
    }

    @Override
    public CatchAndroidLogModel reqRecordLogStart() {
        if (null == mCatchAndroidLogRunnable) {
            mCatchAndroidLogRunnable = new CatchAndroidLogRunnable();
        }
        new Thread(mCatchAndroidLogRunnable).start();
        return this;
    }

    private CatchAndroidLogRunnable mCatchAndroidLogRunnable;

    private class CatchAndroidLogRunnable implements Runnable {
        @Override
        public void run() {
            clearLogCache();// 清除缓存日志
            List<ProcessInfo> processInfoList = getProcessInfoList(getProcessList());// 获取系统所有进程信息集合
            killLogcatProc(processInfoList);// 关闭上个日志收集器
            createLogCollector();// 创建日志收集器
            sleep(1000);
            deleteSDcardExpiredLog(Definition.ANDROID_LOG_PATH);
        }
    }

    /**
     * 制作清除绑在日志命令
     *
     * @return
     */
    public List<String> makeCleanCacheLogCmd() {
        List<String> cmdList = new ArrayList<String>();
        cmdList.add(Definition.LOGCAT);
        cmdList.add(Definition.LogcatOptions.C);
        return cmdList;
    }


    @Override
    public CatchAndroidLogModel setmFilterLogPriority(String filterLogPriority) {
        this.mFilterLogPriority = filterLogPriority;
        return this;
    }

    @Override
    public CatchAndroidLogModel setmFilterLogTag(String filterLogTag) {
        this.mFilterLogTag = filterLogTag;
        return this;
    }

    void createAndroidLogDir() {
        File dirFile = new File(Definition.ANDROID_LOG_PATH);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            dirFile.mkdirs();
        }
    }

    /**
     * 制作Android日志存储绝对存储路径
     *
     * @return
     */
    String makeAndroidLogPath() {
        // 创建存储Android日志文件夹目录
        createAndroidLogDir();
        // 文件名（文件名组成：日志前缀 + 日期 + 日志后缀）
        String logFileName = (Definition.ANDROID_LOG_FILE_PREFIX
                + mDataFormat.format(new Date())
                + Definition.LOG_FILE_SUFFIX);
        return (Definition.ANDROID_LOG_PATH + File.separator + logFileName);
    }


    void init() {
        this.mDataFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss", Locale.getDefault());// 日志名称格式
        this.mFilterLogPriority = PreferenceUtil.getLastFilterLogPriority();
        this.mFilterLogTag = PreferenceUtil.getLastFilterLogTag();
    }

    public CatchAndroidLogModel() {
        init();
    }


}
