<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <style>
        h1{
            font-size: 25px;
            color: #000000;
            text-transform: uppercase;
            font-weight: 300;
            text-align: left;
            margin-bottom: 15px;
        }
        table{
            width:100%;
            table-layout: fixed;
        }
        .tbl-header{
            /*background-color: rgba(255,255,255,0.3);*/
        }
        .tbl-content{
            height: 500px;
            overflow-x:auto;
            margin-top: 0px;
            border: 1px solid #ddd;
        }
        th{
            padding: 20px 15px;
            text-align: left;
            font-weight: 500;
            font-size: 12px;
            /*color: #fff;*/
            text-transform: uppercase;
            font-weight: bold;
        }
        td{
            padding: 15px;
            text-align: left;
            vertical-align:middle;
            font-weight: 300;
            font-size: 12px;
            /*color: #fff;
            border-bottom: solid 1px rgba(255,255,255,0.1);*/
        }
        .noPadding{
            width: 6px;
            padding: 0;
        }
        tr:nth-child(even) {
            background-color: #f2f2f2
        }

        /* demo styles */

        @import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);
        body{
            font-family: 'Roboto', sans-serif;
        }
        section{
            width: 900px;
            margin-left: 20px;
            margin-top: 20px;
        }

        /* for custom scrollbar for webkit browser*/

        ::-webkit-scrollbar {
            width: 6px;
        }
        ::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        }
        ::-webkit-scrollbar-thumb {
            -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
        }

        .newOperation {
            text-align: right;
        }

    </style>

</head>
<body>
<section>
    <table>
        <tr>
            <td class="operationList"><h1>Operation list</h1></td>
            <td class="newOperation"><a href="/operations/edit/new">New operation</a></td>
        </tr>
    </table>
    <div  class="tbl-header">
        <table cellpadding="0" cellspacing="0" border="0">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Sequence</th>
                    <th width="60">Update</th>
                    <th width="60">Delete</th>
                    <th class="noPadding"></th>
                </tr>
            </thead>
        </table>
    </div>
    <div class="tbl-content">
        <table cellpadding="0" cellspacing="0" border="0">
            <tbody>
                <c:forEach items="${allOperationList}" var="operation">
                    <jsp:useBean id="operation" scope="page" type="com.gmail.osbornroad.model.jpa.Operation"/>
                    <tr>
                        <td>${operation.operationName}</td>
                        <td>${operation.operationSequence}</td>
                        <td width="60"><a href="./edit/${operation.id}">Update</a></td>
                        <td width="60"><a href="./delete/${operation.id}">Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</section>
<script>
    // '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
    $(window).on("load resize ", function() {
        var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
        $('.tbl-header').css({'padding-right':scrollWidth});
    }).resize();
</script>
</html>