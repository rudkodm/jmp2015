<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<table class="u-full-width">
    <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
		<c:forEach var="book" items="${allBooks}">
			<tr>
				<td>${book.id}</td>
				<td>${book.name}</td>
				<td>${book.description}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>