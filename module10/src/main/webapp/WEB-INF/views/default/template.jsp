<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <!-- Basic Page Needs-->
    <meta charset="utf-8">
    <title>Module10</title>

    <!-- FONT -->
    <link href="//fonts.googleapis.com/css?family=Raleway:400,300,600" rel="stylesheet" type="text/css"/>

    <!-- CSS -->
    <link rel="stylesheet" href="resources/css/normalize.css"/>
    <link rel="stylesheet" href="resources/css/skeleton.css"/>

</head>
<body>

<!-- Primary Page Layout -->
<div class="container">
    <!-- Header -->
    <tiles:insertAttribute name="header"/>

    <!-- Body Page -->
    <tiles:insertAttribute name="body"/>

</div>

<!-- End Document -->
</body>
</html>