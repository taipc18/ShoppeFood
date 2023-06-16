const app = angular.module("shopping-cart-app",[]);

app.controller("shopping-cart-ctrl", function($scope, $http){
	$scope.cart = {
		items:[],
		
		add(id){
			var item = this.items.find(item => item.id == id);
			if(item){
				item.qty++;
				this.saveToLocalStorage();
			}else{
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = 1;
					this.items.push(resp.data);
					this.saveToLocalStorage();
				})
			}
		},
		
		remove(id){
			var index = this.items.findIndex(item => item.id == id);
			this.items.splice(index, 1);
			this.saveToLocalStorage();
		},
		
		clear(){
			this.items = [];
			this.saveToLocalStorage();
		}, 
		
		amt_of(item){
			
		},
		
		get count(){
			return this.items
			.map(item => item.qty)
			.reduce((total, qty) => total += qty, 0);
		},
		
		get amount(){
			return this.items
			.map(item => item.qty * item.price)
			.reduce((total, qty) => total += qty, 0);
		},
		
		saveToLocalStorage(){
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},
		
		loadFromLocalStorage(){
			var json = localStorage.getItem("cart");
			this.items = json?JSON.parse(json):[];
		}
	}
	
	$scope.cart.loadFromLocalStorage();
	
	$scope.order = {
		createDate:new Date(),
		address:"",
		account:{username:$("#username").text()},
		get orderDetails(){
			return $scope.cart.items.map(item => {
				return{
					product:{id:item.id},
					price:item.price,
					quantity:item.qty
				}
			});
		},
		purchase(){
			var order = angular.copy(this);
			$http.post("/rest/orders", order).then(resp => {
				alert("Ok nha");
				$scope.cart.clear();
				location.href = "/order/detail/" + resp.data.id;
			}).catch(error => {
				alert("error");
				console.log(error)
			})
		}
	}
	
	$scope.pager = {
		page:0,
		size:10,
		get items(){
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		}, 
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size)
		},
		first(){
			this.page = 0;
		},
		prev(){
			this.page--;
			if(this.page < 0){
				this.last();
			}
		},
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		last(){
			this.page = this.count - 1;
		}
	}
})