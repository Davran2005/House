package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.model.Agency;
import peaksoft.model.Customer;
import peaksoft.service.CustomerSer;

@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerApi {
    private final CustomerSer customerSer;
    @GetMapping
    public String getAllHouse(@RequestParam(value = "word",required = false) String word, Model model) {
        model.addAttribute("customers", customerSer.getAllCustomers(word));
        model.addAttribute("word", word);
        return "customer/allCustomer";
    }
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/newCustomer";
    }
    @PostMapping("/customers")
    public String saveAgency(@ModelAttribute("customer") Customer customer) {
        customerSer.saveCustomer(customer);
        return "redirect:/customers";
    }
}
