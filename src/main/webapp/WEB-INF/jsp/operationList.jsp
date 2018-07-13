<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">--%>
    <link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.min.css">

    <script src="/webjars/jquery/3.1.1-1/jquery.js"></script>
    <script src="/webjars/datatables/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="/webjars/datatables/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <%--<link rel="stylesheet" href="/webjars/bootstrap/4.1.1/css/bootstrap.css">--%>
    <link rel="stylesheet" href="/webjars/datatables/1.10.19/css/dataTables.bootstrap4.min.css">
    <script>
        $(document).ready(function() {
            $('#operationTable').DataTable();
        } );
    </script>

    <style>
        /*h1{*/
            /*font-size: 25px;*/
            /*color: #000000;*/
            /*text-transform: uppercase;*/
            /*font-weight: 300;*/
            /*text-align: left;*/
            /*margin-bottom: 15px;*/
        /*}*/
        /*table{*/
            /*width:100%;*/
            /*table-layout: fixed;*/
        /*}*/
        /*.tbl-header{*/
            /*!*background-color: rgba(255,255,255,0.3);*!*/
        /*}*/
        /*.tbl-content{*/
            /*height: 500px;*/
            /*overflow-x:auto;*/
            /*margin-top: 0px;*/
            /*border: 1px solid #ddd;*/
        /*}*/
        /*th{*/
            /*padding: 20px 15px;*/
            /*text-align: left;*/
            /*font-weight: 500;*/
            /*font-size: 12px;*/
            /*!*color: #fff;*!*/
            /*text-transform: uppercase;*/
            /*font-weight: bold;*/
        /*}*/
        /*td{*/
            /*padding: 15px;*/
            /*text-align: left;*/
            /*vertical-align:middle;*/
            /*font-weight: 300;*/
            /*font-size: 12px;*/
            /*!*color: #fff;*/
            /*border-bottom: solid 1px rgba(255,255,255,0.1);*!*/
        /*}*/
        /*.noPadding{*/
            /*width: 6px;*/
            /*padding: 0;*/
        /*}*/
        /*tr:nth-child(even) {*/
            /*background-color: #f2f2f2*/
        /*}*/

        /*!* demo styles *!*/

        /*@import url(https://fonts.googleapis.com/css?family=Roboto:400,500,300,700);*/
        /*body{*/
            /*font-family: 'Roboto', sans-serif;*/
        /*}*/
        /*section{*/
            /*width: 900px;*/
            /*margin-left: 20px;*/
            /*margin-top: 20px;*/
        /*}*/

        /*!* for custom scrollbar for webkit browser*!*/

        /*::-webkit-scrollbar {*/
            /*width: 6px;*/
        /*}*/
        /*::-webkit-scrollbar-track {*/
            /*-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);*/
        /*}*/
        /*::-webkit-scrollbar-thumb {*/
            /*-webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);*/
        /*}*/

        /*.newOperation {*/
            /*text-align: right;*/
        /*}*/

    </style>

</head>
<body>
<div class="container">
    <div class="row mt-1 mb-1">
        <div class="col">
            <h3>Operation list</h3>
        </div>
        <div class="col">
            <a class="btn btn-outline-info font-weight-bold h3" href="/operations/new">+</a>
        </div>
    </div>
    <div class="row">
        <div class="col-12">
            <table class="table table-hover" id="operationTable">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Sequence</th>
                    <th width="60"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${allOperationList}" var="operation" varStatus="oStatus" >
                    <jsp:useBean id="operation" scope="page" type="com.gmail.osbornroad.model.jpa.Operation"/>
                    <tr class="align-middle">
                        <td><a href="/operations/${operation.id}">${operation.operationName}</a></td>
                        <td>${operation.operationSequence}</td>
                        <td><sf:form action="operations/${operation.id}" method="delete"><input type="submit" class="btn btn-outline-danger btn-sm font-weight-bold" value="X"/></sf:form></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>--%>
<%--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>--%>


<%--<section>--%>
<%--    <table>
        <tr>
            <td class="operationList"><h1>Operation list</h1></td>
            <td class="newOperation"><a href="/operations/new">New operation</a></td>
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
                <c:forEach items="${allOperationList}" var="operation" varStatus="oStatus" >
                    <jsp:useBean id="operation" scope="page" type="com.gmail.osbornroad.model.jpa.Operation"/>
                            <tr>
                                <td><a href="/operations/${operation.id}">${operation.operationName}</a></td>
                                <td>${operation.operationSequence}</td>
                                <td width="60"><sf:form action="operations/${operation.id}" method="get"><input type="submit" value="UPDATE"></sf:form> </td>
                                &lt;%&ndash;<td width="60"><a href="./${operation.id}" methods="DELETE">Delete</a></td>&ndash;%&gt;
                                <td width="60"><sf:form action="operations/${operation.id}" method="delete"><input type="submit" value="DELETE"/></sf:form></td>
                            </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>--%>
<%--</section>--%>
<%--<script>
    // '.tbl-content' consumed little space for vertical scrollbar, scrollbar width depend on browser/os/platfrom. Here calculate the scollbar width .
    $(window).on("load resize ", function() {
        var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
        $('.tbl-header').css({'padding-right':scrollWidth});
    }).resize();
</script>--%>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

</body>

</html>