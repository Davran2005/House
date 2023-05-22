package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import peaksoft.exception.MyException;
import peaksoft.model.Agency;
import peaksoft.service.AgencySer;
@Controller
@RequestMapping("/agencies")
@RequiredArgsConstructor
public class AgencyApi {
    private final AgencySer agencySer;
    @GetMapping
    public String getAllAgency(@RequestParam(value = "word",required = false) String word,Model model) {
        model.addAttribute("agencies", agencySer.getAllAgencies(word));
        model.addAttribute("word", word);
        return "agency/allAgencies";
    }
    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("agency", new Agency());
        return "agency/newAgency";
    }

    @PostMapping("/saveAgency")
    public String saveAgency(@ModelAttribute("agency") Agency agency) {
        agencySer.saveAgency(agency);
        return "redirect:/agencies";
    }

    @GetMapping("/{id}")
    public String getAgencyById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("agencies", agencySer.getAgencyById(id));
        return "agency/getByIdAgency";
    }

    @GetMapping("/{id}/edit")
    public String update(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("edit", agencySer.getAgencyById(id));
        return "agency/updateAgency";
    }

    @PostMapping("/update/{id}")
    public String saveUpdate(@ModelAttribute("edit") Agency agency,
                             @PathVariable("id") Long id) {
        agencySer.updateAgency(id, agency);
        return "redirect:/agencies";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        agencySer.deleteAgencyById(id);
        return "redirect:/agencies";
    }
}
