<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
    <title>Ajax File Uploader</title>
    
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>
    <script type="text/javascript" src="http://malsup.github.com/jquery.form.js"></script>
    
    
    <script type="text/javascript">
    <!--
        $(document).ready(function() {
            var options = {
            target: '#message', //Div tag where content info will be loaded in
            url:'services/validadorService', //The php file that handles the file that is uploaded
            beforeSubmit: function() {
                $('#uploader').html('<img src="images/ajax-loader.gif" border="0" />'); //Including a preloader, it loads into the div tag with id uploader
            },
            success:  function() {
                //Here code can be included that needs to be performed if Ajax request was successful
                $('#uploader').html('');
                
            }
            };
            
            $('#upload').submit(function() {
                $(this).ajaxSubmit(options);
                return false;
            });

        }); 
     //-->
    </script>
</head>
<body>
    <div id="message"></div>
    <form name="upload" id="upload" action="#" method="POST" enctype="multipart/form-data">
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