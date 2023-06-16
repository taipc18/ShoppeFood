app = angular.module("admin-app", ["ngRoute"]);

app.config(function($routeProvider){
	$routeProvider
	.when("/product", {
		templateUrl:"/assert/admin/product/index.html",
		controller:"product-ctrl"
	})
	.when("/authorize", {
		templateUrl:"/assert/admin/authority/index.html",
		controller:"authority-ctrl"
	})
	.when("/unauthorized", {
		templateUrl:"/assert/admin/authority/unauthorized.html",
		controller:"authority-ctrl"
	})
	.otherwise({
		templateUrl:"/assert/admin/product/trangquanli.html"
	});
})