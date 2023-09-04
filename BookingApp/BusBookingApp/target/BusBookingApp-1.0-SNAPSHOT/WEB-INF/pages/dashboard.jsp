<!-- Hi?n nút ??ng nh?p n?u ch?a và ?n các cái kia ?i-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h2 class="text-center">MẬT ĐỘ CHUYẾN XE THEO TUYẾN ĐI</h2>
<div class="row">
    <div class="col-md-6">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Route ID</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Count</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${statsRouteCount}" var="s" >
                    <tr>
                        <td>${s[0]}</td>
                        <td>${s[1]}</td>
                        <td>${s[2]}</td>
                        <td>${s[3]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-4">
        <canvas id="routeChart"></canvas>
    </div>
</div>


<h2 class="text-center">MẬT ĐỘ VÉ ĐẶT THEO TUYẾN ĐI</h2>
<div class="row">
    <div class="col-md-6">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Route ID</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Count</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${statsTicketCount}" var="s" >
                    <tr>
                        <td>${s[0]}</td>
                        <td>${s[1]}</td>
                        <td>${s[2]}</td>
                        <td>${s[3]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-md-4">
        <canvas id="ticketChart"></canvas>
    </div>
</div>

<h2 class="text-center mt-5">DOANH THU THEO TUYẾN ĐI</h2>
<div class="row ">
    <div class="col-md-6">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>Route ID</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Price</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${statsRevenue}" var="s" >
                    <tr>
                        <td>${s[0]}</td>
                        <td>${s[1]}</td>
                        <td>${s[2]}</td>
                        <td>${s[3]} VND</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form>
            <div class="row"> 
                <div class="col-md-5">
                    <label>From: </label>
                    <input type="date" name="fromDate"/>
                </div>
                <div class="col-md-5">
                    <label>To: </label>
                    <input type="date" name="toDate"/>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary">Search</button>    
                </div>
            </div>
        </form>
        <br/>
        <form>
            <div style="justify-content: space-around;width: 100%; display: inline-flex; align-items: end; flex-direction: col"> 
                <div class="col-md-4">
                    <label>Year: </label>
                    <input type="number" name="year"/>
                </div>
                <div class="col-md-4">
                    <label>Quater: </label>
                    <input type="number" name="quater"/>
                </div>
                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary">Search</button>    
                </div>
            </div>
        </form>
    </div>
    <div class="col-md-6">
        <canvas id="revenueChart"></canvas>
    </div>
</div>

<h2 class="text-center mt-5">TỔNG DOANH THU THEO THÁNG/QUÝ/NĂM</h2>
<div class="row ">
    <div class="col-md-6">
        <table class="table table-hover">
            <thead>
                <tr>
                    <th>${type}</th>
                    <th>Revenue</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${statsRevenueTotal}" var="s" >
                    <tr>
                        <td>${s[0]}</td>
                        <td>${s[1]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <form>
            <div class="row">
            <div class="col-md-3">
                <input type="radio"  name="type" value="year">Year
                <label  for="radio1"></label>
            </div>
            <div class="col-md-3">
                <input type="radio"  name="type" value="quarter">quarter
                <label  for="radio2"></label>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">Search</button>    
            </div>
                </div>
        </form>
    </div>
    <div class="col-md-6">
        <canvas id="revenueTotalChart"></canvas>
        
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    let lablesCount = [], infoCount = [];
    <c:forEach items="${statsRouteCount}" var="s">
        lablesCount.push('${s[1]} to ${s[2]}');
        infoCount.push(${s[3]});
    </c:forEach>

    let lablesRevenue = [], infoRevenue = [];
    <c:forEach items="${statsRevenue}" var="s">
        lablesRevenue.push('${s[1]} to ${s[2]}');
        infoRevenue.push(${s[3]});
    </c:forEach>

    let lablesTotalRevenue = [], infoTotalRevenue = [];
    <c:forEach items="${statsRevenueTotal}" var="s">
            lablesTotalRevenue.push('${type} ${s[0]}');
            infoTotalRevenue.push(${s[1]});
    </c:forEach>
        
    let lablesTicketCount = [], infoTicketCount = [];
    <c:forEach items="${statsTicketCount}" var="s">
            lablesTicketCount.push('${s[1]} to ${s[2]}');
            infoTicketCount.push(${s[3]});
    </c:forEach>

            window.onload = function () {
                const ctxTripCount = document.getElementById("routeChart").getContext('2d');
                new Chart(ctxTripCount, {
                    type: 'pie',
                    data: {
                        labels: lablesCount,
                        datasets: [{
                                label: 'Mật độ Chuyến đi theo tuyến',
                                data: infoCount,
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
                
                const ctxTicketCount = document.getElementById("ticketChart").getContext('2d');
                new Chart(ctxTicketCount, {
                    type: 'doughnut',
                    data: {
                        labels: lablesTicketCount,
                        datasets: [{
                                label: 'Mật độ Chuyến đi theo tuyến',
                                data: infoTicketCount,
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });

                const ctxRevenue = document.getElementById("revenueChart").getContext('2d');
                new Chart(ctxRevenue, {
                    type: 'bar',
                    data: {
                        labels: lablesRevenue,
                        datasets: [{
                                label: 'Doanh thu theo tuyến',
                                data: infoRevenue,
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });

                const ctxTotalRevenue = document.getElementById("revenueTotalChart").getContext('2d');
                new Chart(ctxTotalRevenue, {
                    type: 'line',
                    data: {
                        labels: lablesTotalRevenue,
                        datasets: [{
                                label: 'Doanh thu theo ${type}',
                                data: infoTotalRevenue,
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }
</script>