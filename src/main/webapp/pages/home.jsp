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
                            <th>Country</th>
                            <th>Province</th>
                            <th>District</th>
                            <th>Tehsil</th>
                            <th>City</th>
                            <th>CNIC</th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach var="u" items="${users}">
                            <tr>
                                <td>${u.userId}</td>
                                <td>${u.name}</td>
                                <td>${u.dob}</td>
                                <td>${u.district.province.country.country}</td>
								<td>${u.district.province.name}</td>
								<td>${u.district.name}</td>
                                <td>${u.tehsilName}</td>
                                <td>${u.city}</td>
                                <td>${u.cnic}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>



<%@ include file="footer.jsp"%>