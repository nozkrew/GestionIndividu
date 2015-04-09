<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nozkrew
  Date: 09/04/2015
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

    <c:if test="${not empty erreur}">
        <p>${erreur}</p>
    </c:if>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Date de naissance</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="individu" items="${individus}">
            <tr>
                <td>${individu.id}</td>
                <td>${individu.nom}</td>
                <td><fmt:formatDate value="${individu.dateDeNaissance}" type="date" timeStyle="default" dateStyle="long" /></td>
                <td><a href="?delete=${individu.id}">Supprimer</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


</body>
</html>
