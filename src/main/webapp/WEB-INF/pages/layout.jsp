<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>WAFT</title>

<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<c:url value="/css/style.css" />" rel="stylesheet" />
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<table border="0" width="100%" cellpadding="2" cellspacing="2" align="center">
    <tr>
        <td height="0" colspan="2"><tiles:insertAttribute name="header" /><tiles:insertAttribute name="loginHeader" />      
    </tr>
    <tr>
        <td height="450" style="vertical-align: top;"><tiles:insertAttribute name="body" /></td>
    </tr>
    <tr>
        <td height="30" colspan="2"><tiles:insertAttribute name="footer" />
    </tr>
</table>
</body>
</html>