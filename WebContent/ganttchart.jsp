<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.IDP.Models.Rect"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE HTML>
<html>
  <head>
    <style>
      body {
        margin: 0px;
        padding: 0px;
      }
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gantt Chart</title>
</head>
<body>
     <div style="position: relative;">
    <canvas id="myCanvas" width="3500" height="1000"
        style="position: absolute; left: 0; top: 0; z-index: 0;"></canvas>
    <canvas id="hoverCanvas" width="3500" height="1000"
        style="position: absolute; left: 0; top: 0; z-index: 1;"></canvas>
    </div>
    <script>
      var canvas = document.getElementById('myCanvas');
      var context = canvas.getContext('2d');

      var canvas2 = document.getElementById('hoverCanvas');
      var hover_context = canvas2.getContext('2d');
      
      //Defining the axis scales
      var axis_xmin = 100;
      var axis_ymin = 590;
     
     //Drawing the axis lines
	  context.beginPath();
      context.moveTo(axis_xmin, 50);
      context.lineTo(axis_xmin, 610);
	  context.moveTo(80, axis_ymin);
      context.lineTo(3500, axis_ymin);
	  context.stroke();
	  context.closePath();
	  //End of axis lines

    //Drawing the axis marks
    context.beginPath();
    
    var Day = ["Monday", "Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"];    
    var j = 0;
    var k = 0;

    for(var i=0; i< 3380; i=i+20)
    {

      if(i!=0)
      {
        context.moveTo(i + 100, 590);
        if(j == 0)
          context.lineTo(i + 100, 605);
        else
          context.lineTo(i + 100, 595);
      }

      if(j%2==0)
        context.fillText(j, i + 100 , 620);

           
     if(j == 0)
      {
        context.fillText(Day[k], i + 100 , 650);
        k++;
      }
      k = k % 7;

      j = j + 1;
      j = j % 24;
    

      
    }

    context.stroke();
    context.closePath();
   
	  //Drawing the top yellow flow line
	  context.beginPath();
	  context.rect(100, 40, 1100, 5);
      context.fillStyle = 'yellow';
      context.fill();
      context.strokeStyle = 'black';
	  context.closePath();
	  //End of yellow flow line
	  
	  
	  //Fetch rectangle coordinates from server
	  <%
	  	List<Rect> rectList = new ArrayList<Rect>();
		rectList = (List<Rect>) request.getAttribute("rectList");
		System.out.println("Inside JSP");
		%>
	  
    //Storing the rectangle coordinates
    var rect_xmin = [];
    var rect_xmax = []; //not used yet
    var rect_ymin = [];
    var rect_ymax = []; //not used yet
    var rect_width = [];
    var rect_height = [];
    var rect_color = [];
    var rect_name = [];

	//Defaults
	var rect_default_height = 20;
	var rect_default_color = 'black';
    
	//Initializing the rectangles
    var no_of_rect = <%= rectList.size() %>;

    <% for(int i=0 ;i <rectList.size();i++){ %>
    rect_xmin[<%=i%>] = <%= rectList.get(i).getXmin() %> + axis_xmin;
    rect_ymin[<%=i%>] = axis_ymin - <%= rectList.get(i).getYmin() %> - 200;
    rect_width[<%=i%>] = <%= rectList.get(i).getWidth() %>;
    rect_height[<%=i%>] = rect_default_height;
    rect_color[<%=i%>] = rect_default_color;
    rect_name[<%=i%>] = '<%= rectList.get(i).getDetails() %>';
   <% }%>

    //End of initialization


    //Moving rect logic - not needed as of now
    var move_rect_xdiff;
    var move_rect_ydiff;
    var move_rect_index;
    var move_selected = false;

    //Drawing the rectangles
    for (var i = 0; i < no_of_rect; i++) {

	  context.beginPath();
	  context.rect(rect_xmin[i], rect_ymin[i], rect_width[i], rect_height[i]);
      context.fillStyle = rect_color[i];
      context.fill();
	  context.closePath();
    };
    //End of rectangles

      
	  function writeMessage(canvas, message) {
        
        context.clearRect(0, 0, 500, 25);
        context.font = '18pt Calibri';
        context.fillStyle = 'black';
        context.fillText(message, 0, 20);
      }
      function getMousePos(canvas, evt) {
        var rect = canvas.getBoundingClientRect();
        return {
          x: evt.clientX - rect.left,
          y: evt.clientY - rect.top
        };
      }
      
      //hover code
      canvas2.addEventListener('mousemove', function(evt) {
        var mousePos = getMousePos(canvas2, evt);
        var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y;
        
        hover_context.clearRect(0,0,1200,1000);
        hover_context.font="20px Georgia";

        hover_context.beginPath();
        hover_context.fillStyle = "rgba(0,0,0,1)";


        for (var i = 0; i <= no_of_rect; i++) {
            if(mousePos.x >= rect_xmin[i] && mousePos.x <= (rect_xmin[i] + rect_width[i]) 
                && mousePos.y >= rect_ymin[i] && mousePos.y <= (rect_ymin[i] + rect_height[i]))
            {
              if(rect_color[i]=='black')
                hover_context.fillStyle = 'brown';

              hover_context.fillText(rect_name[i], (mousePos.x + 10) , (mousePos.y + 20));
              hover_context.closePath();

              hover_context.beginPath();
              hover_context.fillStyle = "rgba(93,179,199,0.30)";
              hover_context.fillRect (mousePos.x, mousePos.y, 120, 60);
              hover_context.closePath();
              break;
            }
        };
        writeMessage(canvas2, message);
      }, false);
	  //End of hover

    canvas2.addEventListener('mousedown', function(evt) {
      var mousePos = getMousePos(canvas2, evt);
      var message = 'Mouse position: ' + mousePos.x + ',' + mousePos.y;

           hover_context.beginPath();
      hover_context.fillText("Selected now", 500 , 500);
           hover_context.closePath();

      if(move_selected)
      {
          
			
        
      }
      else
      {
        for (var i = 0; i <= no_of_rect; i++) {
              if(mousePos.x >= rect_xmin[i] && mousePos.x <= (rect_xmin[i] + rect_width[i]) 
                  && mousePos.y >= rect_ymin[i] && mousePos.y <= (rect_ymin[i] + rect_height[i]))
              {
                move_rect_xdiff = mousePos.x - rect_xmin[i];
                move_rect_ydiff = mousePos.y - rect_ymin[i];
                move_rect_index = i;
                move_selected = true;
                break;
              }
          }
        }
        ;

    }, false);

    canvas2.addEventListener('mouseup', function(evt) {

        if(move_selected)
        {
          context.beginPath();
          context.rect((mousePos.x - move_rect_xdiff), (mousePos.y - move_rect_ydiff), rect_width[move_rect_index], rect_height[move_rect_index]);
          context.fillStyle = rect_color[move_rect_index];
          context.fill();
          context.closePath();

          move_selected = false;            
        };
    }, false);


    </script>
  </body>
</html>