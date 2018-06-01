$(document).ready(function(e) {
		$('#bookTable').remove();
		$.ajax({
			type : 'GET',
			url : '/SampleWebApp/Book',
			data: {},
			cache : false,
			success : function(data) {
				successCallBack(data);
			},
		});
		$('#modifyBtn').click(function() {
			$('#showDetId input[type="text"]:disabled').each(function(){
				$(this).prop( "disabled", false);
			});
			$('input[name = "books"]:checked').closest('tr').find('td').each(function(index) {
				if(index==1) {
					$('#modIsbnId').val($(this).text());
				}if(index==2){
					$('#modBookNameId').val($(this).text());
				}if(index==3){
					$('#modBookPriceId').val($(this).text());
				}
			});
		});
		$('#pocBtnId').click(function() {
			$.ajax({
				type : 'GET',
				url : 'call.jsp',
				data: {},
				success : function(data) {
					alert('response received from server====> '+data);
				},
			});
		});
		
});
	
	var successCallBack = function(data) {
	$('#bookTable').remove();
	alert("data response =====>  "+data);
	alert("data response after trim  =====>  "+data.trim());
	var index = data.lastIndexOf("}");
	alert("index =====> "+index);
	var str = data.substring(0,(index+1));
	alert("str =====> "+str);
	var data1 = JSON.parse(str);
	console.log(data1);
    var table = $("<table />");
    table[0].border = "1";
    table[0].width = "100%";
    table[0].id= "bookTable";
    var colCount = [];
    for(var key in data1.books[0]) {
		if(key!="bookId") {
			if(key == "isbn" && colCount.indexOf(key)===-1)
				colCount.splice(0,0,key);
			else if(key == "bookName" && colCount.indexOf(key)===-1)
				colCount.splice(1,0,key);
			else if(key == "bookPrice" && colCount.indexOf(key)===-1)
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
        	/*if(i==(colCount.length-1)){
        		var editCell = $("<th />");
		        editCell.html("EDIT");
		        row.append(editCell);
        		
        	}*/
        }
    for(var i=0;i<data1.books.length;i++) {
    	var row = $(table[0].insertRow(-1));
    	if(i==0){
    		var data = $("<td />");
			var domEle = "<input type=\"checkbox\" name=\"books\" value="+data1.books[i].bookId+" checked=\"checked\"></input>";
    		var inputTag = $(domEle);
    		data.html(inputTag);
       		row.append(data);  
    	}
    	for(var j=0;j<colCount.length;j++) {
    		if(j==0 && i!=0) {
    			var data = $("<td />");
    			var domEle = "<input type=\"checkbox\" name=\"books\" value="+data1.books[i].bookId+"></input>";
        		var inputTag = $(domEle);
        		data.html(inputTag);
	       		row.append(data);     	
    		}
    		if(i==0) {
				var data = $("<td />");
				if(j==0)
					$('#modIsbnId').val(data1.books[i][colCount[j]]);
				else if(j==1)
					$('#modBookNameId').val(data1.books[i][colCount[j]]);
				else if(j==2)
					$('#modBookPriceId').val(data1.books[i][colCount[j]]);
	       		data.html(data1.books[i][colCount[j]]);
	       		row.append(data); 
    		}else {
    			var data = $("<td />");
	       		data.html(data1.books[i][colCount[j]]);
	       		row.append(data); 
    		}    	
    	}
    	/*var edit = $("<td/>")
    	var editId = data1.books[i].bookId;
    	var something = "<input type=\"button\" value=\"edit\" onclick=\"editBookDetails("+editId+")\"></input>";
    	var buttonTag = $(something);
    	edit.html(buttonTag);
    	row.append(edit);*/
    }
    var paginationRow = $(table[0].insertRow(-1));
    var td1 = $("<td/>")
    var td2 = $("<td/>")
    var prevBtn = "<a href=\"#\" onclick=\"handlePrev();\"></a>";
    td2.html($(prevBtn).text("Previous"));
    var td3 = $("<td/>")
    var td4 = $("<td/>")
    var nextBtn = "<a href=\"#\" onclick=\"handleNext();\"></a>";
    td4.html($(nextBtn).text("Next"));
    //var td5 = $("<td/>")
    paginationRow.append(td1);
    paginationRow.append(td2);
    paginationRow.append(td3);
    paginationRow.append(td4);
    //paginationRow.append(td5);
    var divId=$('#listId');
    divId.attr("align","center");
    divId.html();
    divId.append(table);
    $.ajax({
		type : 'GET',
		url : 'display.jsp',
		data: {
			action : "respNull"
		},
		success : function(data) {
			
		},
	});
}

function editBookDetails(id) {
	var editDivDialog = $('#editDialog').css('display', '');
	$('#editIsbn').val('');
	$('#editBookName').val('');
	$('#editBookPrice').val('');
	$.ajax({
		type : 'GET',
		url : '/SampleWebApp/Book',
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
			//alert(row);
		}
		
	});
	editDivDialog.dialog({
		modal : true,
		autoOpen : false,
		buttons : {
			'Update' : function() {
				var updatedIsbn = $('#editIsbn').val();
				var updatedBookName = $('#editBookName').val();
				var updatedBookPrice = $('#editBookPrice').val();
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

function handleNext() {
	$('#bookTable').remove();
		$.ajax({
			type : 'GET',
			url : 'display.jsp',
			data: {
			action : "next"
			},
			success : function(data) {
				successCallBack(data);
			},
		});

}

function handlePrev() {
	$('#bookTable').remove();
		$.ajax({
			type : 'GET',
			url : 'display.jsp',
			data: {
			action : "prev"
			},
			success : function(data) {
				successCallBack(data);
			},
		});

}

function search() {
	var isbn = $("#isbnId").val();
	var bookName = $("#bookNameId").val();
	$.ajax({
		type : 'GET',
		url : '/SampleWebApp/Book',
		data: {
			isbn : isbn,
			bookName :bookName,
			action : "search"
		},
		success : function(data) {
			successCallBack(data);
		},
	});
}