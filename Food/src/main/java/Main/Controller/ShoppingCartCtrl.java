package Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ShoppingCartCtrl {
	
	
	@RequestMapping("/cart/view")
	public String view() {
		return "cart/view";
	}
}
