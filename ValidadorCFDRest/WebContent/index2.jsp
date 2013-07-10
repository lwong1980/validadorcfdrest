<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Ajax File Uploader</title>
    
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>
    <script type="text/javascript" src="http://malsup.github.com/jquery.form.js"></script>
    

</head>
<body>
    <div id="message"></div>
    <form name="upload" id="upload" action="/ValidadorCFDRest/services/validadorService" method="POST" >
        <table cellpadding="4" cellspacing="4" border="0">
            <tr>
                <td colspan="2"><h1>Upload File via Ajax</h1></td>
            </tr>
           <tr>
                <td class="fieldLabel" nowrap>File:</td>
                <td nowrap><input type="file" name="fileToUpload" id="fileToUpload" /></td>
            </tr>
            <tr>
                <td nowrap colspan="2"><input type="submit" id="uploadFile" value="Upload File" /></td>
            </tr>   
            
        </table>
    </form>
    <div id="uploader"></div>
</body>
</html>