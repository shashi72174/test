<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-1.6.2.js"></script>
<script type="text/javascript">
$(document).ready(function(){		
	$('#stateId').change(function() {
			var state = $('#stateId').val();
			alert("value entered ========= " + state);
			$.ajax({
				type : 'POST',
				url : 'SampleServlet',
				data: {stateId : state,
				action : "getCityDetails"},
				success : function(data) {
			   var city = $('#cityId');
        	city.find('option').remove();
        	$('<option></option>').val("").text("Select").appendTo(city);
          $.each(data, function(index, value) {
          			$('<option>').val(value).text(value).appendTo(city);
			    });
			  //  document.getElementById("result").innerHTML('User Selected '+state);
			},
			});
			});
			});
			
			/*$(document).ready(function(){		
			$('#stateId').change(function() {
			var state = $('#stateId').val();
			alert("value entered ========= " + state);
			$.ajax({
				type : 'POST',
				url : 'SampleServlet',
				data: {stateId : state},
				success : function(data) {
			   var select = $( "input[name='citiez']" );
			   alert(select);
          	   $.each(data, function(index, value) {
         	   $('<input type="checkbox" checked="checked">').val(value).text(value).appendTo(select);
			    });
			},
			});
			});
			});*/
</script>
<script type="text/javascript">
	function selectedCity() {
	alert('selectedCity called');
		alert($("#cityId").val());
		$('#cityId').change(function() {
			var city = $("#cityId").val();
			alert("value entered ========= " + city);
			$.ajax({
				type : 'POST',
				url : 'SampleServlet',
				data: {cityId : city,
				action : "test"},
				success : function(data) {
			   /* var city = $('#cityId');
        	city.find('option').remove();
        	$('<option></option>').val("").text("Select").appendTo(city);
          $.each(data, function(index, value) {
          			$('<option>').val(value).text(value).appendTo(city);
			    }); */
			    alert(data);
			    var resultantDiv = $('#resultDiv');
			    alert("data.city "+data);
			    resultantDiv.html(data);
			    alert('done');
			  //  document.getElementById("result").innerHTML('User Selected '+state);
			},
			});
			});
	}
</script>

<script type="text/javascript">
	$(document).ready(function(){
	alert('started');
        $.ajax({
				type : 'GET',
				url : 'DemoServlet',
				data: {},
				success : function(data) {
				var data1 = JSON.parse(data);
		        var table = $("<table />");
		        table[0].border = "1";
		        var colCount = [];
	        	for(var key in data1.people[0]) {
	        		if(colCount.indexOf(key)===-1) {
	        			colCount.push(key);
	        		}	
	        	}
		        var row = $(table[0].insertRow(-1));
		        for(var i=0;i<colCount.length;i++) {
		        	var headerCell = $("<th />");
		        	headerCell.html(colCount[i]);
		        	row.append(headerCell);
		        }
		        for(var i=0;i<data1.people.length;i++) {
		        	var row = $(table[0].insertRow(-1));
		        	for(var j=0;j<colCount.length;j++) {
		   				var data = $("<td />");
			       		data.html(data1.people[i][colCount[j]]);
			       		row.append(data);     	
		        	}
		        	
		        }
		        var divId=$('#divId');
		        divId.html();
		        divId.append(table);
		        
        
	}
	})
	alert('ended');
	});
	
</script>
<!-- 

data : {
					stateId : state
				},
dataType : 'json',

<script type="text/javascript">
	$(document).ready(function() {
$('#stateId').change(function(event) {
        var state = $('#stateId').val();
        $.post('SampleServlet', {
                stateId : state
        }, function(response) {
		
        var select = $('#cityId');
        select.find('option').remove();
          $.each(response, function(index, value) {
          $('<option>').val(value).text(value).appendTo(select);
      });
        });
        });
});  
	
</script> -->
</head>
<body>
	<form id="simpleForm">
		<table border="0">
			<tr>
				<td><label>State</label></td>
				<td><select id="stateId" name="stateId">
						<option value="Select">Select</option>
						<option value="Karnataka">Karnataka</option>
						<option value="Andhra Pradesh">Andhra Pradesh</option>
						<option value="Tamil Nadu">Tamil Nadu</option>
				</select></td>
			</tr>
			<tr>
				<td><label>City</label></td>
				 <td><select id="cityId" onchange="selectedCity()">
						<option value="Select">Select</option>
				</select></td>
				
				
				
		</table>
		
		
		<div id="resultDiv">
			
		</div>

	</form>
	<p id="result"></p>
	<div id="divId"></div>
	
</body>
</html>