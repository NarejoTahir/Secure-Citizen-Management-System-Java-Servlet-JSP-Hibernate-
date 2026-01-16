<%@ include file="header.jsp"%>


<div class="container mt-5">

    <!-- Card for Users Table -->
    <div class="card shadow-sm">
        <div class="card-body">

            <h3 class="card-title mb-4">Users List</h3>

            <!-- Table -->
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle" id="usersTable">
                    <thead class="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Date of Birth</th>
                            <th>CNIC</th>
                            <th>Added By</th>
                        </tr>
                    </thead>
                    <tbody>
                      <c:forEach var="row" items="${rows}">
       						<c:set var="u" value="${row[0]}" />
        					<c:set var="rev" value="${row[1]}" />
                            <tr>
                                <td>${u.userId}</td>
                                <td>${u.name}</td>
                                <td>${u.dob}</td>
                                <td>${u.cnic}</td>
                                <td><a href="${path }user-added-by.jsp?userId=${rev.userId}" class="btn btn-primary">Added By</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>



<%@ include file="footer.jsp"%>	