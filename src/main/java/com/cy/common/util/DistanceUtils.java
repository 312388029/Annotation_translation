package com.cy.common.util;


/**
 * @Description: 距离相关工具类
 * @Author: cy
 * @Date: 2021/1/15
 */
public class DistanceUtils {

    //地球圆周
    private static double EarthRadius = 6378.137;

    /**
     * 计算两个坐标点之间的距离
     *
     * @param firstLatitude   第一个坐标的纬度
     * @param firstLongitude  第一个坐标的经度
     * @param secondLatitude  第二个坐标的纬度
     * @param secondLongitude 第二个坐标的经度
     * @return 返回两点之间的距离，单位：公里/千米
     */
    public static double getDistance(double firstLatitude, double firstLongitude,
                                     double secondLatitude, double secondLongitude) {
        double firstRadLat = rad(firstLatitude);
        double firstRadLng = rad(firstLongitude);
        double secondRadLat = rad(secondLatitude);
        double secondRadLng = rad(secondLongitude);

        double a = firstRadLat - secondRadLat;
        double b = firstRadLng - secondRadLng;
        double cal = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(firstRadLat)
                * Math.cos(secondRadLat) * Math.pow(Math.sin(b / 2), 2))) * EarthRadius;
        double result = Math.round(cal * 10000d) / 10000d;

        return result;
    }

    /**
     * 经纬度转化成弧度
     * @param d 经度/纬度
     * @return
     */
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

}
