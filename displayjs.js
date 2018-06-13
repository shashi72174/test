$(document).ready(function(e) {
	var gv_isbn;
	var gv_bookName;
	var gv_bookPrice;
	$('#searchId').keyup(function() {
		$('#bookTable').remove();
		var searchText = $('#searchId').val();
		$.ajax({
			type : 'GET',
			url : 'book/getBookDetailsBySearchText',
			data: {searchText : searchText},
			success : function(data) {
				successCallBack(data);
			},
		});
	});
	var tabcontent = $('.tabcontent');
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    document.getElementById("London").style.display = "block";
    $("#tab1").click();
    $("#tab1").focus();
    var tablinks = $('.tablinks');
	for(var i=0;i<tablinks.length;i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}
    
    $("#London").show('fast');
	$("#Paris").hide('fast');
	$("#Tokyo").hide('fast');
	$('#bookTable').remove();
	$.ajax({
		type : 'GET',
		url : 'book/getBookDetails',
		data: {action : "book/getBookDetails"},
		success : function(data) {
			successCallBack(data);
		},
	});
		var addDivDialog = $('#dialog');
		addDivDialog.dialog({
			modal : true,
			autoOpen : false,
			buttons : {
				'Save' : function() {
					var isbn = $('#isbn').val();
					var bookName = $('#bookName').val();
					var bookPrice = $('#bookPrice').val();
					var libraryId = $('#libraryId').val();
					var libraryTxt = $('#libraryId').text();
					var book = {
					"isbn" : isbn,
					"bookName" : bookName,
					"bookPrice" : bookPrice,
					"library" : {
						"libraryID" : libraryId,
						"libraryName" : libraryTxt
					}
					}
					var jsonString = JSON.stringify(book);
					$.ajax({
						type : 'POST',
						url : 'book/saveBookDetails',
						accept: 'application/json',
						contentType : 'application/json; charset=utf-8',
						data: jsonString,
						success : function(data) {
							var tablinks = $('.tablinks');
							for(var i=0;i<tablinks.length;i++) {
								tablinks[i].className = tablinks[i].className.replace(" active", "");
							}
							$("#London").show('fast');
							$("#Paris").hide('fast');
							$("#Tokyo").hide('fast');
							$('#bookTable').remove();
							$.ajax({
								type : 'GET',
								url : 'book/getBookDetails',
								data: {action : "book/getBookDetails"},
								success : function(data) {
									successCallBack(data);
								},
							});
						}
						
					});
					$('#isbn').val('');
					$('#bookPrice').val('');
					$('#bookName').val('');
					addDivDialog.dialog('close');
				},
				'Cancel' : function() {
					$('#isbn').val('');
					$('#bookPrice').val('');
					$('#bookName').val('');
					addDivDialog.dialog('close');
				}
				
			}
			
		});	
		$('#addId').click(function(){
			$.ajax({
				type : 'GET',
				url : 'getAllLibraries',
				success : function(data) {
					var libraryId = $('#libraryId');
					libraryId.find('option').remove();
		        	$('<option></option>').val("").text("Select").appendTo(libraryId);
		          	$('<option>').val(data.library.libraryID).text(data.library.libraryName).appendTo(libraryId);
				}
			});
			addDivDialog.dialog('open');
			
		});	
		
		var addStudentDivDialog = $('#studDialog');
		addStudentDivDialog.dialog({
			modal : true,
			autoOpen : false,
			buttons : {
				'Save' : function() {
					
				},
				'Cancel' : function() {
				
				}
				
			}
			
		});	
		
		$('#addStudId').click(function(){
			$.ajax({
				type : 'GET',
				url : 'getAllLibraries',
				success : function(data) {
					var libraryId = $('#libId');
					libraryId.find('option').remove();
		        	$('<option></option>').val("").text("Select").appendTo(libraryId);
		          	$('<option>').val(data.library.libraryID).text(data.library.libraryName).appendTo(libraryId);
				}
			});
			
			addStudentDivDialog.dialog('open');
			
		});	
		
		
		
		$('#tab1').click(function(e) {
			var tablinks = $('.tablinks');
			for(var i=0;i<tablinks.length;i++) {
				tablinks[i].className = tablinks[i].className.replace(" active", "");
			}
		    e.currentTarget.className += " active";
			$("#London").show('fast');
			$("#Paris").hide('fast');
			$("#Tokyo").hide('fast');
			$('#bookTable').remove();
			$.ajax({
				type : 'GET',
				url : 'book/getBookDetails',
				data: {action : "book/getBookDetails"},
				success : function(data) {
					successCallBack(data);
				},
			});
			
			
		});
    
	$('#tab2').click(function(e) {
		var tablinks = $('.tablinks');
		for(var i=0;i<tablinks.length;i++) {
			tablinks[i].className = tablinks[i].className.replace(" active", "");
		}
	    e.currentTarget.className += " active";
		$("#London").hide('fast');
		$("#Paris").show('fast');
		$("#Tokyo").hide('fast');
		$.ajax({
			type : 'GET',
			url : 'getStudentDetails',
			success : function(data) {
				successCallBack2(data);
			},
		});
	});

	$('#tab3').click(function(e) {
		var tablinks = $('.tablinks');
		for(var i=0;i<tablinks.length;i++) {
			tablinks[i].className = tablinks[i].className.replace(" active", "");
		}
	    e.currentTarget.className += " active";
		$("#London").hide('fast');
		$("#Paris").hide('fast');
		$("#Tokyo").show('fast');
	});
	
	$('#delId').click(function(){
		$('input[type="checkbox"][name="books"]').each(function() {
			if ($(this).attr("checked")) {
				alert($(this).val());
				//make ajax call and delete from backend
			}
		});
		
	});
});


function onLibrarySelect(){
	var libId = $('#libId').val();
	if(libId=="" || libId==null)
		libId=1;
	$.ajax({
		type : 'GET',
		url : 'getDepartmentDetailsByLibraryId',
		data : {libraryId : libId},
		success : function(data) {
			console.log(typeof(data));
			var obj = JSON.parse(data);
			var departmentId = $('#departmentId');
			departmentId.find('option').remove();
        	$('<option></option>').val("").text("Select").appendTo(departmentId);
        	$.each(obj.departments, function(index, ob) {
        		$('<option>').val(ob.DEPARTMENT_ID).text(ob.DEPARTMENT_CODE).appendTo(departmentId);
        	});
		}
	});
}	

function editBookDetails(id) {
	var editDivDialog = $('#editDialog').css('display', '');
	$('#editIsbn').val('');
	$('#editBookName').val('');
	$('#editBookPrice').val('');
	$.ajax({
		type : 'GET',
		url : 'book/getBookById',
		data: {bookId : id},
		success : function(data) {
			var data1 = JSON.parse(data);
			$('#editIsbn').val(data1.ISBN);
			$('#editBookName').val(data1.BOOKNAME);
			$('#editBookPrice').val(data1.BOOKPRICE);
			/*$(this).closest('tr').find('td').each(function() {
				alert('inside each');
				alert($(this).text());
			});*/
			//you can get old value for sending it to controller by global variables or by using 
			//jquery closest function - below is by global variables
			gv_isbn = data1.ISBN;
			gv_bookName = data1.BOOKNAME;
			gv_bookPrice = data1.BOOKPRICE;
			alert('fererer');
			
			//alert(row);
		}
		
	});
	editDivDialog.dialog({
		modal : true,
		autoOpen : false,
		buttons : {
			'Update' : function() {
				alert(gv_isbn+"   "+gv_bookName+"  "+gv_bookPrice);
				var updatedIsbn = $('#editIsbn').val();
				var updatedBookName = $('#editBookName').val();
				var updatedBookPrice = $('#editBookPrice').val();
				alert(updatedIsbn+"   "+updatedBookName+"  "+updatedBookPrice);
				var book = {
						"isbn" : updatedIsbn,
						"bookName" : updatedBookName,
						"bookPrice" : updatedBookPrice,
						"bookId" : id,
						"isbn_prev" : gv_isbn,
						"bookName_prev" : gv_bookName,
						"bookPrice_prev" : gv_bookPrice,
						}
				var jsonString = JSON.stringify(book);
				alert(jsonString);
				$.ajax({
					type : 'PUT',
					url : 'book/updateBookDetails',
					accept: 'application/json',
					contentType : 'application/json; charset=utf-8',
					data: jsonString,
					success : function(data) {
						successCallBack(data);
					}
					
				});
				editDivDialog.dialog('close');
			},
			'Cancel' : function() {
				editDivDialog.dialog('close');
			}
		}
	});
	editDivDialog.dialog('open');
}

function checkAll() {
	if($('#checkAll').attr("checked")) {
		$('input[type="checkbox"][name="books"]').each(function() {
			$(this).attr("checked",true);
		});
	}else {
		$('input[type="checkbox"][name="books"]').each(function() {
			$(this).attr("checked",false);
		});
	}
}

var successCallBack = function(data) {
	$('#bookTable').remove();
	var data1 = JSON.parse(data);
    var table = $("<table />");
    table[0].border = "1";
    table[0].id= "bookTable";
    var colCount = [];
	for(var key in data1.books[0]) {
		if(key!="BOOKID") {
			if(key == "ISBN" && colCount.indexOf(key)===-1)
				colCount.splice(0,0,key);
			else if(key == "BOOKNAME" && colCount.indexOf(key)===-1)
				colCount.splice(1,0,key);
			else if(key == "BOOKPRICE" && colCount.indexOf(key)===-1)
				colCount.splice(2,0,key);
		}
	}
	 var row = $(table[0].insertRow(-1));
        for(var i=0;i<colCount.length;i++) {
        	if(i==0) {
        		var headerCell = $("<th />");
        		var domEle = "<input type=\"checkbox\" id=\"checkAll\"></input>";
        		var inputTag = $(domEle).click(checkAll);
        		headerCell.html(inputTag);
        		row.append(headerCell);
        	}
        	var headerCell = $("<th />");
        	headerCell.html(colCount[i].toUpperCase());
        	row.append(headerCell);
        	if(i==(colCount.length-1)){
        		var editCell = $("<th />");
		        editCell.html("EDIT");
		        row.append(editCell);
        		
        	}
        }
    for(var i=0;i<data1.books.length;i++) {
    	var row = $(table[0].insertRow(-1));
    	if(i==0){
    		var data = $("<td />");
			var domEle = "<input type=\"checkbox\" name=\"books\" value="+data1.books[i].BOOKID+" checked=\"checked\"></input>";
    		var inputTag = $(domEle);
    		data.html(inputTag);
       		row.append(data);
    	}
    	for(var j=0;j<colCount.length;j++) {
    		if(j==0 && i!=0) {
    			var data = $("<td />");
    			var domEle = "<input type=\"checkbox\" name=\"books\" value="+data1.books[i].BOOKID+"></input>";
        		var inputTag = $(domEle);
        		data.html(inputTag);
	       		row.append(data);     	
    		}
    		if(i==0) {
				var data = $("<td />");
				console.log(data1.books[i][colCount[j]]);
	       		data.html(data1.books[i][colCount[j]]);
	       		row.append(data); 
    		}else {
    			var data = $("<td />");
	       		data.html(data1.books[i][colCount[j]]);
	       		row.append(data); 
    		}
    	}
    	var edit = $("<td/>")
    	var editId = data1.books[i].BOOKID;
    	var something = "<input type=\"button\" value=\"edit\" onclick=\"editBookDetails("+editId+")\"></input>";
    	var buttonTag = $(something);
    	edit.html(buttonTag);
    	row.append(edit);
    }
    var divId=$('#divId');
    divId.attr("align","center");
    divId.html();
    divId.append(table);
}

var successCallBack2 = function(data) {
	$('#studentTable').remove();
	var data1 = JSON.parse(data);
    var table = $("<table />");
    table[0].border = "1";
    table[0].id= "studentTable";
    var colCount = [];
	for(var key in data1.students[0]) {
		if(colCount.indexOf(key)===-1) {
			colCount.push(key);
		}	
	}
	 var row = $(table[0].insertRow(-1));
        for(var i=0;i<colCount.length;i++) {
        	if(i==0) {
        		var headerCell = $("<th />");
        		var domEle = "<input type=\"checkbox\" id=\"checkAll\"></input>";
        		var inputTag = $(domEle).click(checkAll);
        		headerCell.html(inputTag);
        		row.append(headerCell);
        	}
        	var headerCell = $("<th />");
        	headerCell.html(colCount[i].toUpperCase());
        	row.append(headerCell);
        	if(i==(colCount.length-1)){
        		var editCell = $("<th />");
		        editCell.html("EDIT");
		        row.append(editCell);
        		
        	}
        }
    for(var i=0;i<data1.students.length;i++) {
    	var row = $(table[0].insertRow(-1));
    	for(var j=0;j<colCount.length;j++) {
    		if(j==0) {
    			var data = $("<td />");
    			var domEle = "<input type=\"checkbox\" name=\"students\" value="+data1.students[i].STUDENTID+"></input>";
        		var inputTag = $(domEle);
        		data.html(inputTag);
	       		row.append(data);     	
    		}
				var data = $("<td />");
       		data.html(data1.students[i][colCount[j]]);
       		row.append(data);     	
    	}
    	var edit = $("<td/>")
    	//problem here - need to be addressed - should create another function for handling student view
    	var editId = data1.students[i].STUDENTID;
    	var something = "<input type=\"button\" value=\"edit\" onclick=\"editBookDetails("+editId+")\"></input>";
    	var buttonTag = $(something);
    	edit.html(buttonTag);
    	row.append(edit);
    }
    var divId=$('#studentDivId');
    divId.attr("align","center");
    divId.html();
    divId.append(table);
}

function upload() {
	alert('dfdsfdsf');
	document.uploadForm.submit();
}