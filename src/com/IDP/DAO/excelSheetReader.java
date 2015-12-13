/**
 * 
 */
package com.IDP.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.IDP.Models.AllData;
import com.IDP.Models.TF;
import com.IDP.Models.TS;
import com.IDP.Models.WS;
import com.IDP.Models.YS;

/**
 * @author JOY
 *
 */
public class excelSheetReader {
	
	@SuppressWarnings("resource")
	public AllData returnAllData(String filePath){
		System.out.println("inside the dao class::"+filePath);
		AllData allData = new AllData();
		List<WS> wsList = new ArrayList<WS>();
		List<YS> ysList = new ArrayList<YS>();
		List<TS> tsList = new ArrayList<TS>();
		List<TF> tfList = new ArrayList<TF>();
		try {
			File excelFilePath = new File(filePath);
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			//XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet1 = workbook.getSheet("Results");
			for(int i = 5; i <= 12; i++){
				WS ws = new WS();
				List<Integer> timePoints = new ArrayList<Integer>();
				for(int j = 1; j <= 6; j++){
					if(j==1){
						ws.setTasks(sheet1.getRow(i).getCell(j).getStringCellValue());
					}else{
						timePoints.add(Integer.parseInt(String.valueOf(sheet1.getRow(i).getCell(j).getNumericCellValue()).substring(0, 1)));
					}
				}
				ws.setTimePoints(timePoints);
				wsList.add(ws);
			}
			//String data0 = sheet1.getRow(2).getCell(0).getStringCellValue();
			System.out.println(wsList.size());
			for(int a = 0; a < wsList.size(); a++){
				System.out.print(wsList.get(a).getTasks()+"\t");
				for(int b = 0; b < 5; b++){
					System.out.print(wsList.get(a).getTimePoints().get(b)+"\t");
				}
				System.out.println();
			}
			
			
			for(int i = 5; i <= 8; i++){
				YS ys = new YS();
				List<Integer> timePoints = new ArrayList<Integer>();
				for(int j = 9; j <= 15; j++){
					if(j==9){
						ys.setUnits(sheet1.getRow(i).getCell(j).getStringCellValue());
					}else{
						timePoints.add(Integer.parseInt(String.valueOf(sheet1.getRow(i).getCell(j).getNumericCellValue()).substring(0, 1)));
					}
				}
				ys.setTimePoints(timePoints);
				ysList.add(ys);
			}
			
			System.out.println(ysList.size());
			for(int a = 0; a < ysList.size(); a++){
				System.out.print(ysList.get(a).getUnits()+"\t");
				for(int b = 0; b < 5; b++){
					System.out.print(ysList.get(a).getTimePoints().get(b)+"\t");
				}
				System.out.println();
			}
			
			for(int i = 51; i <= 82; i++){
				TS ts = new TS();
				List<String> unitLists = new ArrayList<String>();
				List<Double> timeLists = new ArrayList<Double>();
				for(int j = 9; j <= 15; j++){
					if(j==9){
						ts.setTask(sheet1.getRow(i).getCell(j).getStringCellValue());
					}else if(j==10){
						unitLists.add(sheet1.getRow(i).getCell(j).getStringCellValue());
					}else{
						timeLists.add(sheet1.getRow(i).getCell(j).getNumericCellValue());
					}
				}
				ts.setUnits(unitLists);
				ts.setTimePoints(timeLists);
				tsList.add(ts);
			}
			
			System.out.println("list size::"+tsList.size());
			for(int i = 0; i < tsList.size();i++){
				System.out.println(tsList.get(i).getTask()+"\t");
				for(int x = 0; x < tsList.get(i).getUnits().size();x++){
					System.out.print(tsList.get(i).getUnits().get(x)+"\t");
					for(int j = 0; j < tsList.get(i).getTimePoints().size();j++){
						System.out.print(tsList.get(i).getTimePoints().get(j)+"\t");
					}
					System.out.println();
				}
			}
			
			System.out.println("into TF");
			for(int i = 51; i <= 82; i++){
				TF tf = new TF();
				List<String> unitLists = new ArrayList<String>();
				List<Double> timeLists = new ArrayList<Double>();
				for(int j = 17; j <= 23; j++){
					if(j==17){
						tf.setTask(sheet1.getRow(i).getCell(j).getStringCellValue());
					}else if(j==18){
						unitLists.add(sheet1.getRow(i).getCell(j).getStringCellValue());
					}else{
						timeLists.add(sheet1.getRow(i).getCell(j).getNumericCellValue());
					}
				}
				tf.setUnits(unitLists);
				tf.setTimePoints(timeLists);
				tfList.add(tf);
			}
			
			System.out.println("list size::"+tfList.size());
			for(int i = 0; i < tfList.size();i++){
				System.out.println(tfList.get(i).getTask()+"\t");
				for(int x = 0; x < tfList.get(i).getUnits().size();x++){
					System.out.print(tfList.get(i).getUnits().get(x)+"\t");
					for(int j = 0; j < tfList.get(i).getTimePoints().size();j++){
						System.out.print(tfList.get(i).getTimePoints().get(j)+"\t");
					}
					System.out.println();
				}
			}
			allData.setTfList(tfList);
			allData.setTsList(tsList);
			allData.setWsList(wsList);
			allData.setYsList(ysList);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allData;
	}
}