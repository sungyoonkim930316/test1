<%@ page pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>SB Admin 2 - Dashboard</title>

<!-- Custom fonts for this template-->
<link
	href="/spring-board-a/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="/spring-board-a/resources/css/sb-admin-2.min.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Page Wrapper -->
	<div id="wrapper">

		<jsp:include page="/WEB-INF/views/modules/sidebar.jsp" />

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<jsp:include page="/WEB-INF/views/modules/topbar.jsp" />

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="h3 mb-2 text-gray-800">자유 게시판</h1>
					<br>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<span class="m-0 font-weight-bold text-primary">글 목록</span> <a
								href="write.action" class="btn btn-success btn-sm"
								style="float: right"> <span class="text">글 쓰기</span>
							</a>
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>글번호</th>
											<th>제목</th>
											<th>작성자</th>
											<th>작성일자</th>
											<th>수정일자</th>
											<th>조회수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${ boards }" var="board">
											<tr>
												<td>${ board.bno }</td>
												<td>${ board.title }</td>
												<td>${ board.writer }</td>
												<td>${ board.regDate }</td>
												<td>${ board.updateDate }</td>
												<td>${ board.readCount }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

		</div>
		<!-- End of Content Wrapper -->

		<!-- Board Write Message Modal-->
		<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
			aria-labelledby="modalTitle" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="modalTitle">알림</h5>
						<button class="close" type="button" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
					<div class="modal-body">게시판 글쓰기 성공^^~</div>
					<div class="modal-footer">
						<button class="btn btn-secondary" type="button"
							data-dismiss="modal">Confirm</button>
					</div>
				</div>
			</div>
		</div>

	<!-- End of Page Wrapper -->

	<!-- JSP의 include 기능은 하나의 jsp에서 다른 jsp를 호출하여 여러 jsp 파일을 혼합하여 하나의 jsp로 만들 수 있다. 여기서는 정적인 방식의 include를 쓰고 있다.-->
	<%@include file="/WEB-INF/views/modules/common-js.jsp"%>

	<script type="text/javascript">
		$(function() {
			//var newBno = ${ param.newBno };
			var newBno = '${ newBno }';
			if (newBno) {
				$('#messageModal .modal-body').text("작성하신 글이 " + newBno + "번 게시글로 등록됐습니다.")
				$('#messageModal').modal('show');
			}
		});
	</script>

</body>

</html>

