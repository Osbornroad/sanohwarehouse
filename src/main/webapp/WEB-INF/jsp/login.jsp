<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page session="true"%>
<html lang="en">
<head>

    <%--<jsp:include page="fragments/headerTags.jsp"/>--%>

<%--    <meta name="viewport" content="width=device-width, initial-scale=1">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/resources/bootstrap4-glyphicons/css/bootstrap-glyphicons.css">
    <script src="/webjars/jquery/3.1.1-1/jquery.js"></script>
    <script src="/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/webjars/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="/resources/js/bootbox.min.js"></script>--%>

    <style>
        body {font-family: Arial, Helvetica, sans-serif; height: 100%}
        form {border: 3px solid #f1f1f1;}

        input[type=text], input[type=password] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            box-sizing: border-box;
        }

        label {
            margin-top: 10px;
            margin-bottom: 2px;
        }

        button {
            background-color: #0151af;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
        }

        button:hover {
            opacity: 0.8;
        }

        .cancelbtn {
            width: auto;
            padding: 10px 18px;
            background-color: #f44336;
        }

        .imgcontainer {
            text-align: center;
            margin: 24px 0 12px 0;
        }

        img.avatar {
            width: 40%;
            border-radius: 50%;
        }

        .container {
            padding: 16px;
        }

        span.password {
            float: right;
            padding-top: 16px;
        }

        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
            span.psw {
                display: block;
                float: none;
            }
            .cancelbtn {
                width: 100%;
            }
        }

        .login-box {
            width: 600px;
            margin: 100px auto;

        }

    </style>
</head>
<body onload='document.loginForm.username.focus();'>

<div class="container login-box">

    <form name='loginForm'
          action="<c:url value='/j_spring_security_check' />" method='POST'>

        <div class="container">
            <h2>Login Form</h2>

            <c:if test="${not empty error}">
                <div class="error">${error}</div>
            </c:if>
            <c:if test="${not empty msg}">
                <div class="msg">${msg}</div>
            </c:if>
        </div>
        <%--<div class="imgcontainer">
            <img src="img_avatar2.png" alt="Avatar" class="avatar">
        </div>--%>

            <div class="container">
                <label for="username"><b>Username</b></label>
                <input type="text" placeholder="Enter Username" name="username" required>

                <label for="password"><b>Password</b></label>
                <input type="password" placeholder="Enter Password" name="password" required>

                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />

                <button type="submit">Login</button>
                <%--<label>
                    <input type="checkbox" checked="checked" name="remember"> Remember me
                </label>--%>
            </div>
<%--
            <div class="container" style="background-color:#f1f1f1">
                <button type="button" class="cancelbtn">Cancel</button>
                <span class="psw">Forgot <a href="#">password?</a></span>
            </div>
--%>

    </form>



       <%-- <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit"
                                       value="submit" /></td>
            </tr>
        </table>

        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />

    </form>--%>
</div>

</body>
</html>