<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<link rel="stylesheet"
	href="<c:url value="/resources/css/display.css" />">
	<link rel="stylesheet"
	href="<c:url value="/resources/css/jquery-ui.css" />">
<script src="<c:url value="/resources/js/jquery-1.6.2.js" />"></script>
<script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
</head>
<body>
	<h1 align="center">Library Management System</h1>


	<!-- <label>NAME:</label><input type="text" name="name" id="name" />
	<br/>
	<input type="submit" value="SayHello" /> -->
	
	<div id="multiTabId" class="tabs" align="center">
		<button id="tab1" value="Tab1" autofocus="autofocus" class="tablinks">Books</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button id="tab2" value="Tab2" class="tablinks">Student</button>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<button id="tab3" value="Tab3" class="tablinks">Borrow/Issued Details</button>
	</div>

	<div id="London" class="tabcontent" style="display:none">
		<div align="right"><input type="button" id="addId" value="Add Book"></input></div>
		<h3>London</h3>
		<p>London is the capital city of England.</p><br/>
		<div id="searchDiv" align="right">
			<label>Search:</label><input type="text" id="searchId"/>
		</div>	
		<div id="divId">
			<div align="right">
				<button id="delId" value="Delete">Delete</button>
			</div>	
		</div>
	</div>

	<div id="Paris" class="tabcontent" style="display:none">
		<div align="right"><input type="button" id="addStudId" value="Add Student"></input></div>
		<h3>Paris</h3>
		<p>Paris is the capital of France.</p>
		<div id="studentDivId">
				
		</div>
	</div>

	<div id="Tokyo" class="tabcontent" style="display:none">
		<h3>Tokyo</h3>
		<p>Tokyo is the capital of Japan.</p>
	</div>
	
	<div id="dialog" align="center">
		<table>
			<tr>
				<td><label><b>ISBN</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="isbn"/></td>
			</tr>
			<tr>
				<td><label><b>Book Name</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="bookName"/></td>
			</tr>
			<tr>
				<td><label><b>Book Price</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="bookPrice"/></td>
			</tr>
			<tr>
				<td><label><b>Library</b></label></td>
				<td><b>:</b></td>
				<td><select id="libraryId" name="libraryId">
					<option value="Select">Select</option>
				</select></td>
			</tr>
		</table>
	</div>
	
	<div id="editDialog" align="center" style="display:none">
		<table>
			<tr>
				<td><label><b>ISBN:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="editIsbn" readonly="readonly"/></td>
			</tr>
			<tr>
				<td><label><b>Book Name:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="editBookName"/></td>
			</tr>
			<tr>
				<td><label><b>Book Price:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="editBookPrice"/></td>
			</tr>
		</table>
	</div>
	
	<div id="studDialog" align="center">
		<table>
			<tr>
				<td><label><b>First Name</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="firstName"/></td>
			</tr>
			<tr>
				<td><label><b>Last Name</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="lastName"/></td>
			</tr>
			<tr>
				<td><label><b>Phone:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="phoneNo"/></td>
			</tr>
			<tr>
				<td><label><b>Street Name:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="streetName"/></td>
			</tr>
			<tr>
				<td><label><b>City:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="city"/></td>
			</tr>
			<tr>
				<td><label><b>State:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="state"/></td>
			</tr>
			<tr>
				<td><label><b>Country:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="country"/></td>
			</tr>
			<tr>
				<td><label><b>PinCode:</b></label></td>
				<td><b>:</b></td>
				<td><input type="text" id="pincode"/></td>
			</tr>
			<tr>
				<td><label><b>Library</b></label></td>
				<td><b>:</b></td>
				<td><select id="libId" name="libId" onchange="onLibrarySelect()">
					<option value="Select">Select</option>
				</select></td>
			</tr>
			<tr>
				<td><label><b>Department</b></label></td>
				<td><b>:</b></td>
				<td><select id="departmentId" name="departmentId">
					<option value="Select">Select</option>
				</select></td>
			</tr>
		</table>
	</div>
	<form action="file/upload" method="post" name="uploadForm" id="uploadForm" enctype="multipart/form-data">
		<input type="file" name="file" multiple/>
		<select multiple="multiple" name="countries">
			<option value="INDIA">INDIA</option>
			<option value="AUSTRALIA">AUSTRALIA</option>
			<option value="UK">UK</option>
		</select>
		<input id="UpldbtnId" type="button" value="Upload" onclick="upload()"/>
	</form>
<script src="<c:url value="/resources/js/displayjs.js" />"></script>
</body>
</html>
