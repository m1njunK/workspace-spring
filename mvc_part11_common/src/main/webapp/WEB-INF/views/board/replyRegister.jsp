<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../common/header.jsp" %>
	<h1>replyRegister.jsp</h1>
	<form action="replyRegister" method="POST">
		<table>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" required />
				</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>
					<input type="text" name="writer" value="${userInfo.uname}" readonly />
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea cols="50" rows="20" id="content" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" id="saveBtn" value="답변 등록"/>
				</th>
			</tr>
		</table>
		<input type="hidden" name="uno" value="${userInfo.uno}"/>
		<input type="hidden" name="origin" value="${board.origin}">
		<input type="hidden" name="depth" value="${board.depth}">
		<input type="hidden" name="seq" value="${board.seq}">
	</form>
	
	<script src="https://cdn.tiny.cloud/1/txisexjqogc2o5hq1mpsl5obcf0exfbgxj0f053sshq2xiu1/tinymce/6/tinymce.min.js"></script>
	<script>
	let plugins = ["link" ,"image"];
	let edit_toolbar = "blocks fontfamily fontsize | link image forecolor backcolor"
	
	tinymce.init({
		language : "ko_KR",
		selector: '#content',
		width : 600,
		height: 500,
		menubar : false,
		plugins: plugins,
		  toolbar: edit_toolbar,
		  /* enable title field in the Image dialog*/
		  image_title: true,
		  /* enable automatic uploads of images represented by blob or data URIs*/
		  automatic_uploads: true,
		  /*
		    URL of our upload handler (for more details check: https://www.tiny.cloud/docs/configure/file-image-upload/#images_upload_url)
		    images_upload_url: 'postAcceptor.php',
		    here we add custom filepicker only to Image dialog
		  */
		  file_picker_types: 'image',
		  /* and here's our custom image picker*/
		  file_picker_callback: (cb, value, meta) => {
		    const input = document.createElement('input');
		    input.setAttribute('type', 'file');
		    input.setAttribute('accept', 'image/*');

		    input.addEventListener('change', (e) => {
		      const file = e.target.files[0];

		      const reader = new FileReader();
		      reader.addEventListener('load', () => {
		        /*
		          Note: Now we need to register the blob in TinyMCEs image blob
		          registry. In the next release this part hopefully won't be
		          necessary, as we are looking to handle it internally.
		        */
		        const id = 'blobid' + (new Date()).getTime();
		        const blobCache =  tinymce.activeEditor.editorUpload.blobCache;
		        const base64 = reader.result.split(',')[1];
		        const blobInfo = blobCache.create(id, file, base64);
		        blobCache.add(blobInfo);

		        /* call the callback and populate the Title field with the file name */
		        cb(blobInfo.blobUri(), { title: file.name });
		      });
		      reader.readAsDataURL(file);
		    });

		    input.click();
		  },
		  content_style: 'body { font-family:Helvetica,Arial,sans-serif; font-size:16px }'
		});
  </script>
</body>
</html>
