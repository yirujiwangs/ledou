package utils.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import model.Device;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtil {

	public static String excelGet(JSONArray arr,String path,String urlweb) throws IOException {
		int columnNum = 12;
		String[] names = new String[columnNum];
		names[0]="设备状态";
		names[1]="商家帐号";
		names[2]="商家名称";
		names[3]="设备数";
		names[4]="序列号";       //serialNum
		names[5]="微信ibeaconId";
		names[6]="UUID";
		names[7]="major";
		names[8]="minor";
		names[9]="设备型号";
		names[10]="添加时间";
		names[11]="设备备注";
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("设备记录表");
		HSSFRow firstrow = sheet.createRow(0);      //下标为0
		for(int i=0;i<columnNum;i++){
			firstrow.createCell(i).setCellValue(names[i]);
		}
		
		for(int i=0;i<arr.size();i++){
			JSONObject record = arr.getJSONObject(i);
			HSSFRow row = sheet.createRow(i+1);		
			row.createCell(0).setCellValue(record.getString("status"));
			row.createCell(1).setCellValue(record.getString("account"));
			row.createCell(2).setCellValue(record.getString("storeName"));
			row.createCell(3).setCellValue(record.getString("deviceNum"));
			row.createCell(4).setCellValue(record.getString("serialNum"));
			row.createCell(5).setCellValue(record.getString("ibeaconId"));
			row.createCell(6).setCellValue(record.getString("uuid"));
			row.createCell(7).setCellValue(record.getString("major"));
			row.createCell(8).setCellValue(record.getString("minor"));
			row.createCell(9).setCellValue(record.getString("type"));
			row.createCell(10).setCellValue(record.getString("createTime"));
			row.createCell(11).setCellValue(record.getString("remarkDevice"));
		}
		String ffname = "device_record.xls";
		File file = new File(path+ffname);
		if(file.exists()){
			file.delete();
		}
		OutputStream out = new FileOutputStream(path+ffname) ;
		wb.write(out) ;
		out.close() ;
		String urlString = urlweb+"/"+ffname;
		return urlString;
			
	}

	public static List<Device> GetExcelData(String path) throws FileNotFoundException,IOException{

//	public static void GetExcelData(String path) throws FileNotFoundException,IOException{
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("d://testExcel.xls"));

		HSSFWorkbook wb = new HSSFWorkbook(fs);

		HSSFSheet sheet = wb.getSheetAt(0);

		//总行数
		int trLength = sheet.getLastRowNum();

		HSSFRow row = sheet.getRow(0);

		//总列数
		int tdLength = row.getLastCellNum();

		List<Device> devices = new ArrayList<Device>();
		Device device = new Device();
		for(int i=1;i<=trLength;i++){
			HSSFRow row1 = sheet.getRow(i);

			for(int j=0;j<tdLength;j++){
				HSSFCell cell = row1.getCell(j);
				switch (j){
					case 0:
						device.setSerialNum(cell.getStringCellValue());
						break;
					case 1:
						device.setIbeaconId(cell.getStringCellValue());
						break;
					case 2:
						device.setCreateTime(new Date(Long.parseLong(cell.getStringCellValue())));
						break;
					case 3:
						device.setStatus(cell.getStringCellValue());
						break;
					case 4:
						device.setDescr(cell.getStringCellValue());
						break;
					case 5:
						device.setUuid(cell.getStringCellValue());
						break;
					case 6:
						device.setMajor((int) cell.getNumericCellValue());
						//device.setMajor(Integer.parseInt(cell.getStringCellValue()));
						break;
					case 7:
						device.setMinor((int) cell.getNumericCellValue());
						//device.setMinor(Integer.parseInt(cell.getStringCellValue()));
						break;
					default:break;
				}
			}
			devices.add(device);
		}
		return devices;
	}
	
}
