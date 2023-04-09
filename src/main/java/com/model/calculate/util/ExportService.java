package com.model.calculate.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author xiachao
 * @date 2021/2/6 4:26 下午
 */
public class ExportService {

    public static void main(String[] args) throws IOException {
        //第一步，创建一个workbook对应一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //第二部，在workbook中创建一个sheet对应excel中的sheet
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        //第三部，在sheet表中添加表头第0行，老版本的poi对sheet的行列有限制
        HSSFRow row = sheet.createRow(0);
        //第四步，创建单元格，设置表头
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("业务线");
        cell = row.createCell(1);
        cell.setCellValue("Uid");
        cell = row.createCell(2);
        cell.setCellValue("货主名称");
        cell = row.createCell(3);
        cell.setCellValue("计划发货日期格式：YYYY.MM.DD");
        cell = row.createCell(4);
        cell.setCellValue("客户订单id");
        cell = row.createCell(5);
        cell.setCellValue("联系人");
        cell = row.createCell(6);
        cell.setCellValue("手机号码");
        cell = row.createCell(7);
        cell.setCellValue("省");
        cell = row.createCell(8);
        cell.setCellValue("城市");
        cell = row.createCell(9);
        cell.setCellValue("区域");
        cell = row.createCell(10);
        cell.setCellValue("收货地址");
        cell = row.createCell(11);
        cell.setCellValue("商品条形码");
        cell = row.createCell(12);
        cell.setCellValue("商品名称");
        cell = row.createCell(13);
        cell.setCellValue("规格");
        cell = row.createCell(14);
        cell.setCellValue("发货数量");
        cell = row.createCell(15);
        cell.setCellValue("是否校验去重");


        //第五步，写入实体数据，实际应用中这些数据从数据库得到,对象封装数据，集合包对象。对象的属性值对应表的每行的值
        for (int i = 0; i <= 20000; i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            //创建单元格设值
            row1.createCell(0).setCellValue("EX");
            row1.createCell(1).setCellValue(2226594 + i);
            row1.createCell(2).setCellValue("EX辅教");
            row1.createCell(3).setCellValue("2020.12.23");
            row1.createCell(4).setCellValue(690867860224659L + 1);
            row1.createCell(5).setCellValue("张三");
            row1.createCell(6).setCellValue("13912345678");
            row1.createCell(7).setCellValue("上海");
            row1.createCell(8).setCellValue("上海");
            row1.createCell(9).setCellValue("普陀");
            row1.createCell(10).setCellValue("xxxxxxxx");
            row1.createCell(11).setCellValue("987654321");
            row1.createCell(12).setCellValue("自然拼读日历本");
            row1.createCell(13).setCellValue("UI-U3");
            row1.createCell(14).setCellValue("1");
            row1.createCell(15).setCellValue("是");
        }
        //将文件保存到指定的位置
        try {
            FileOutputStream fos = new FileOutputStream("/Users/xiachao/Desktop/发货单.xls");
            workbook.write(fos);
            System.out.println("写入成功");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
