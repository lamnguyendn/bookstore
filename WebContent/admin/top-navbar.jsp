<div class="top_nav">
	<div class="nav_menu">
		<nav>
			<div class="nav toggle">
				<a id="menu_toggle" style="color: #fff;" class="menu-toggle"> <i
					class="fa fa-bars"></i>
				</a>
			</div>
			<ul
				class="nav navbar-nav navbar-right visible-sm-block visible-md-inline visible-lg-inline">
				<li class="" id="wrapper">
					<a href="javascript:;"
						id="cityclick" class="user-profile dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false"> <span
							style="font-size: 20px; display: inline-block; font-family: FontAwesome; font-style: normal; font-weight: normal;">
							Xin chào Admin</span>
					</a>
					<ul class="dropdown-menu dropdown-usermenu pull-right"
						id="citydrop">
						<li><a href="javascript:;"> Profile</a></li>
						<li><a href="javascript:;"> <span
								class="badge bg-red pull-right">50%</span> <span>Settings</span>
						</a></li>
						<li><a href="javascript:;">Help</a></li>
						<li><a href="login.html"><i
								class="fa fa-sign-out pull-right"></i> Log Out</a></li>
					</ul>
				</li>
				<li class="logo"><span>BookStore <span> Admin
							Management</span>
				</span></li>
			</ul>
		</nav>
	</div>
</div>
<script>
	$(document).ready(function() {
		$("#cityclick").mouseover(function() {
			$("#citydrop").slideDown('fast');
		});

		$("#wrapper").mouseleave(function() {
			$("#citydrop").slideUp('fast');
		});
	});
</script>