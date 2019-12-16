<%-- 
    Document   : authorization
    Created on : 27/04/2018, 05:43:36 PM
    Author     : ulises
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="/nanduti/assets/image/favicon.ico"/>
        <title>PractiPago</title>
        <style>
            *{
                padding: 0px;
                margin: 0px;
                font-family: sans-serif;
            }
            header{
                width: 100%;
                height: 70px;
                background-color: #00C853 !important;
                text-align: center;
                margin-bottom: 20px;
            }
            #centro{
                width: 50%;
                padding-top: 30px;
                display: table;
                text-align: center;
                margin: 0px auto;
            }
            a{
                background-color: #3f51b5; 
                border: none;
                color: white;
                padding: 15px 32px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
            }
        </style>
    </head>
    <body>
        <header>
            <img src="http://cobra.documenta.com.py:8325/nanduti/compress/assets/image/logo-practipago-text.png" alt="Logo"/>
        </header>
       
        <%
            String status = request.getParameter("status");
            String msj = request.getParameter("msj");
            String old = request.getParameter("old");
            if(old != null || status != null || msj!= null){
        %>
        
            <div id="centro">
                <h3><% out.write(msj);  %></h3> <br/>
                <a href="<% out.write(old);  %>.nti">Regresar</a>
            </div>
        <%
            } else {
        %>
            <div id="centro">
                <h3>No se pasarón parámetros para la ejecucuón.</h3> 
            </div>
        <%
        }
        %>
        
        
        
    </body>
</html>
