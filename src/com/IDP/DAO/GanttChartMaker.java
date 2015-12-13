/**
 * 
 */
package com.IDP.DAO;

import java.util.ArrayList;
import java.util.List;

import com.IDP.Models.*;

/**
 * @author JOY
 *
 */
public class GanttChartMaker {

	public List<Rect> createGanttChart(AllData allData) {
		
		List<Rect> rectList = new ArrayList<Rect>();
		
		List<WS> wsList = allData.getWsList();
		List<YS> ysList = allData.getYsList();
		List<TS> tsList = allData.getTsList();
		List<TF> tfList = allData.getTfList();
		
		int numberOfTasks = wsList.size();
		int numberOfUnits = ysList.size();
		double timediff;
		double xmin;
		double width;
		double ymin;
		double xScaleFactor = 20;
		double yScaleFactor = 50;
		
		for(int w=0; w<numberOfTasks;w++){
			for(int tp=1;tp < wsList.get(w).getTimePoints().size();tp++){
				if(wsList.get(w).getTimePoints().get(tp)==0)
					continue;
					
				for(int y=0;y<numberOfUnits;y++){
					if(ysList.get(y).getTimePoints().get(tp)==0)
						continue;
					
					timediff = (tfList.get(w*numberOfUnits+y).getTimePoints().get(tp))-(tsList.get(w*numberOfUnits+y).getTimePoints().get(tp));
					
					if(timediff == 0)
						continue;
					
					xmin = tsList.get(w*numberOfUnits+y).getTimePoints().get(tp) * xScaleFactor;
					width = timediff * xScaleFactor;
					ymin = y + y * yScaleFactor;
					
					Rect rect = new Rect();
					rect.setXmin(xmin);
					rect.setWidth(width);
					rect.setYmin(ymin);
					rect.setDetails(tsList.get(w*numberOfUnits).getTask()+" "+tsList.get(w*numberOfUnits+y).getUnits());
					
					rectList.add(rect);
					
					}
				}
			}
		
		return rectList;
	}
}