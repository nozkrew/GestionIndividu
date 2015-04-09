<%--
  Created by IntelliJ IDEA.
  User: Nozkrew
  Date: 09/04/2015
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="individu" method="POST">
        <label for="Nom">Nom</label>
        <input type="text" name="Nom" id="Nom" required="true"/>
        <label for="dateNaissance">Date de naissance</label>
        <input type="date" name="dateNaissance" id="dateNaissance" required="true">

        <input type="submit" value="Valider">
    </form>
</body>
</html>
