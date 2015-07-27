<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form method="post" modelAttribute="book" action="${pageContext.request.contextPath}/app/books">
    <div class="row">
        <div class="six columns">
            <label for="bookId">Book ID</label>
            <form:input class="u-full-width" type="id" placeholder="Book ID" id="bookID" path="id"/>
        </div>
        <div class="six columns">
            <label for="bookName">Book Name</label>
            <form:input class="u-full-width" type="name" placeholder="Book Name" id="bookName" path="name"/>
        </div>
    </div>
    <label for="bookDescription">Description</label>
    <form:textarea class="u-full-width" placeholder="Put some description here ..." id="bookDescription" path="description"/>
    <input class="button-primary" type="submit" value="Submit"/>
</form:form>