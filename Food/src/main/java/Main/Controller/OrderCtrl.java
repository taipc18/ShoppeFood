package Main.Controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import Main.service.OrderService;

@Controller
public class OrderCtrl {
	
	@Autowired
	OrderService oSV;
	
	@RequestMapping("/order/checkout")
	public String checkout() {
		return "order/checkout";
	}
	
	@RequestMapping("/order/list")
	public String list(Model md, HttpServletRequest rq) {
		String username = rq.getRemoteUser();
		md.addAttribute("listOrders", oSV.findByUsername(username));
		return "order/list";
	}
	
	@RequestMapping("/order/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model md) {
		md.addAttribute("order", oSV.findById(id));
		return "order/detail";
	}
}
