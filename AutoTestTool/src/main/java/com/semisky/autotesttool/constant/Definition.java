package com.semisky.autotesttool.constant;

public class Definition {

    /**
     * 日志格式化输出<br>
     *
     * 说明：日志消息包含一个元数据字段，除了标签和优先级，
     * 您可以修改输出显示一个特定的元数据字段格式的消息。
     * 为此，您使用-v选项来指定一个支持的输出格式<br>
     * brief: 显示优先级/标记和过程的PID发出的消息（默认格式）
     * process: 只显示PID
     * tag: 只显示优先级/标记
     * raw: 显示原始的日志消息，没有其他元数据字段
     * time: 调用显示日期、时间、优先级/标签和过程的PID发出消息
     * threadtime: 调用显示日期、时间、优先级、标签遗迹PID TID线程发出的消息
     * long: 显示所有元数据字段与空白行和单独的消息
     */
    public class LogcatFormat {
        public static final String BRIEF = "brief";
        public static final String PROCESS = "process";
        public static final String TAG = "tag";
        public static final String RAW = "raw";
        public static final String TIME = "time";
        public static final String THREADTIME = "threadtimess";
        public static final String LONG = "long";
    }

    /**
     * 日志缓冲区<br>
     *
     * Radio：输出通信系统的log
     * System：输出系统组件的log
     * Event：输出event模块的log
     * Main：所有java层的log，遗迹不属于上面3层的log
     * e.g.: logcat –b radio
     */
    public class LocatBufferAear {
        public static final String RADIO = "radio";
        public static final String SYSTEM = "system";
        public static final String EVENT = "event";
        public static final String MAIN = "main";
    }

    /**
     * 日志选项命令<br>
     *
     * -b <buffer>: 加载一个可使用的日志缓冲区供查看，比如event和radio。默认值是main
     * -c: 清除缓冲区中的全部日志并退出（清除完后可以使用-g查看缓冲区）
     * -d: 将缓冲区的log转存到屏幕中然后退出
     * -f  <filename>:将log输出到指定的文件中<文件名>.默认为标准输出（stdout）
     * -g: 打印日志缓冲区的大小并退出
     * -n: <count>:设置日志的最大数目<count>，默认值是4，需要和-r选项一起使用
     * -r: <kbytes>:没<kbytes>时输出日志，默认值是16，需要和-f选项一起使用
     * -s: 设置过滤器
     * -v <format>: 设置输出格式的日志消息。默认是短暂的格式。支持的格式列表
     */
    public class LocatOptions {
        public static final String B = "-b";
        public static final String C = "-c";
        public static final String D = "-d";
        public static final String F = "-f";
        public static final String G = "-g";
        public static final String N = "-n";
        public static final String R = "-r";
        public static final String S = "-s";
        public static final String V = "-v";
    }

    /**
     * 日志等级<br>
     *
     * 过滤项格式 : <tag>[:priority] , 标签:日志等级, 默认的日志过滤项是 " *:I " ;
     * 优先级使用字符标识，一下优先级从低到高
     * V –Verbose(最低优先级)
     * D – Debug
     * I – Info
     * W – Warning
     * E – Error
     * F – Fatal
     * S – Silent
     * e.g.: logcat -v time aaa:I
     */
    public class LogcatPriority {
        public static final String V = "V";
        public static final String D = "D";
        public static final String I = "I";
        public static final String E = "E";
        public static final String F = "F";
        public static final String S = "S";
    }
}
