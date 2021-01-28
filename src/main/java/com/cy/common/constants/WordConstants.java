package com.cy.common.constants;

public class WordConstants {

    //1为确定
    public static final Integer YES = 1;

    //2为否定
    public static final Integer NO = 2;

    //状态（1：正常，2：早退，3：迟到，4：缺卡）
    public static final Integer NORMAL = 1;
    public static final Integer EARLY = 2;
    public static final Integer LATE = 3;
    public static final Integer LACK = 4;

    //TO表示上班
    public static final String TO_WORK = "TO";

    //OFF表示下班
    public static final String OFF_WORK = "OFF";

    //普通员工 P
    public static final String PERSONNEL = "P";
    //经理 B
    public static final String MANAGER = "B";

    //评分标识
    public static final String GRADE = "GRADE";

    //申述标识
    public static final String APPEAL = "APPEAL";

    //请假标识
    public static final String LEAVE = "LEAVE";

    //请假类型
    public static final String[] LEAVE_TYPE = {"C", "S", "G"};

    //考勤正常
    public static final  Integer ATTENDANCE_LEAVE = 0;
    //考勤正常
    public static final  Integer ATTENDANCE_NORMAL = 1;
    //考勤异常
    public static final  Integer ATTENDANCE_ABNORMAL = 2;
    //考勤假期
    public static final  Integer ATTENDANCE_HOLIDAY = 3;
    //考勤无记录
    public static final  Integer ATTENDANCE_NORECORD = 4;

    //审核状态-S 已经审核
    public static final String CHECKED = "S";

    //状态-作废
    public static final String CANCEL = "D";

    //固定
    public static final String PERMANENT = "1";

    //自由
    public static final String FREEDOM = "2";

    //前台审核
    public static final String CHECK_TYPE_F = "F";

    //后台审核
    public static final String CHECK_TYPE_B = "B";
}
