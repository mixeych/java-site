<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello</title>
    </head>
    <body>
        ${error}
        <h1>Hi!</h1>
        Name: ${user.name}
        Password ${user.password}
        <form method="POST" action="upload-file" enctype="multipart/form-data">
		Files to upload: <input type="file" name="upfile">
		Files to upload: <input type="file" name="upfile">


		<input type="submit" value="Upload"> Press here to upload the file!
	</form>
    </body>
</html>
