package Main.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Main.entity.Product;
import Main.service.ProductService;

@Controller
public class ProductCtrl {
	@Autowired
	ProductService prSV;
	
	@RequestMapping("/product/list")
	public String list(Model md, @RequestParam("cid") Optional<String> cid) {
		if(cid.isPresent()) {
			List<Product> list = prSV.findByCategoryId(cid.get());
			md.addAttribute("items", list);
		}else {
			List<Product> list = prSV.findAll();
			md.addAttribute("items", list);
		}
		return "product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model md, @PathVariable("id") Integer id) {
		Product item = prSV.findById(id);
		md.addAttribute("item", item);
		return "product/detail";
	}
	
	@RequestMapping("/layout/index")
	public String layout() {
		return "layout/index";
	}
}
