<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Category list with links</title>
</head>
<body>

<table>
    <tbody>
    <tr>
        <td>
            <p>
                <a href="/category/add">Add category</a>
            </p>
        </td>
        <td>
            <p>
                <a href="/category/">Category list</a>
            </p>
        </td>
    </tr>
    <tr>
        <td>
            <p>
                <a href="/author/add">Add author</a>
            </p>
        </td>
        <td>
            <p>
                <a href="/author/">Author list</a>
            </p>
        </td>
    </tr>
    <tr>
        <td>
            <p>
                <a href="/article/add">Add article</a>
            </p>
        </td>
        <td>
            <p>
                <a href="/article/">Article list</a>
            </p>
        </td>
    </tr>
    </tbody>
</table>


<table>
    <thead>
    <tr>
        <th>Name</th>
    </tr>
    </thead>
    <tbody>
    <form:form method="post" action="article">
    <c:forEach items="${categories}" var="category">
        <tr>

            <td>
                Click here:
                    <a href="/article/${category.id}">${category.name}</a>
                or here:
                    <button type="submit" name="categoryId" value="${category.id}">${category.name}</button>
            </td>

        </tr>

    </c:forEach>
    </form:form>
    </tbody>
</table>


</body>
</html>
