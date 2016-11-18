<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>

.fileDrop {
	width: 100%;
	height: 300px;
	border: 3px dotted black;
}
</style>

<body>

	<div class='uploadedList'></div>
	
	<form action="registerAction" method='post' enctype="multipart/form-data">
		<p><input type="text" name="pname"></p>
		<p><input type="text" name="pmaker"></p>
		<p><input type="hidden" name="pimg" id="pimg"></p>
		<input type="submit">
	</form>
	
	<div class='fileDrop'>Drop Here</div>

	<script src="http://code.jquery.com/jquery-latest.min.js"></script>

	<script>
		$(document).ready(function() {

			var uploadedList = $(".uploadedList");

			$(".fileDrop").on("dragenter dragover", function(event) {
				event.preventDefault();
			});
			$(".fileDrop").on("drop", function(event) {
				event.preventDefault();

				//드랍이 다 되면 실제로 데이터가 들어오도록 (여러개 드랍 가능)
				var files = event.originalEvent.dataTransfer.files;

				var file = files[0];

				console.log(file);

				var formData = new FormData();
				formData.append("file", file);

				console.log(formData);

				$.ajax({

					url : "uploadFile",
					data : formData,
					dataType : 'text',
					type : "post",
					contentType : false,
					processData : false,
					success : function(data) {
						console.log(data);
						uploadedList.html("<img src=show?name=" + data + ">");
						$("#pimg").val(data);
					}
				});

			});
		});
	</script>

</body>
</html>